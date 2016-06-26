/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Cavad
 */
public class MyThread extends Thread {
    
    public static Hashtable<String, String> blackList = new Hashtable<String, String>(100);
    public static Hashtable<String,String> bannedWords= new Hashtable<String,String>(100);
    public static Hashtable<String, HashtableNode> cached = new Hashtable<String, HashtableNode>(100);
    private static String CACHE_FOLDER_NAME = "cache/";
    private static final int DEFAULT_HTTP_PORT = 80;
    private static int RETR_BUFFER_SIZE = 1000;
    Socket connection;

    int n=0;
    private static final String ENDLINE = "\r\n";
    private static final int HTTP_NOT_MODIFIED = 304;


    public MyThread(Socket connectionSocket,int n) {
        this.connection = connectionSocket;
        this.n=n;
    }

    @Override
    public void run() {
        try {
            System.out.println("Accepted #"+n);
            String request = readRequest(connection);
            System.out.println("Read #"+n);
            HttpRequest httpRequest = new HttpRequest(request);
            System.out.println(httpRequest.getObjectAddress());
            if (httpRequest.getMethod() == HttpRequest.Methods.CONNECT) {
                return;
            }
//            System.out.println("Method is get. Continue");
            HttpResponse httpResponse = processRequest(httpRequest);
            connection.setKeepAlive(httpRequest.getKeepAlive());
//            System.out.println("Response:");
//            System.out.println(httpResponse);
            System.out.println("Request Processed. #"+n);
            sendResponse(connection, httpResponse);
            System.out.println("Response Sent #"+n);
        } catch (IOException e) {
        }
    }

    private static String readRequest(Socket connection) throws IOException {
        String result = "";
        Scanner scanner = new Scanner(connection.getInputStream());

        while (true) {
            String next = "";
            try {
                next = scanner.nextLine();
                if (next.startsWith("CONNECT")) {
                    return next;
                }
            } catch (NoSuchElementException e) {
                break;
            }
            result += next + ENDLINE;
            if (next.equals("")) {
                break;
            }
        }
        return result;
    }

    private  HttpResponse processRequest(HttpRequest httpRequest) throws FileNotFoundException, UnknownHostException, IOException {
        HttpResponse result = new HttpResponse();
        if (isBanned(httpRequest)) {
            result = new HttpResponse("filtered.html", true);
            System.out.println("Sorry. The site is filtered. #"+n);
        } else {
            if (cached.containsKey(httpRequest.getObjectAddress())) {
                System.out.println("Found in cache. #"+n);
                //Send If Modified Since
                String fileName=retrieveAndSave(httpRequest,cached.get(httpRequest.getObjectAddress()).savedDate);
                if("".equals(fileName)){
                    result = new HttpResponse(CACHE_FOLDER_NAME + cached.get(httpRequest.getObjectAddress()).fileName, false);
                    if(containsBannedWord(result.toByteArray())){
                        result=new HttpResponse("banned.html",true);
                    }
                }else if("BW".equals(fileName)){
                    result = new HttpResponse("banned.html", true);                    
                }else{
                    cached.remove(httpRequest.getObjectAddress());
                    HashtableNode htn = new HashtableNode();
                    htn.fileName = fileName;
                    htn.savedDate = new Date();
                    cached.put(httpRequest.getObjectAddress(), htn);
                    result = new HttpResponse(CACHE_FOLDER_NAME + cached.get(httpRequest.getObjectAddress()).fileName, false);
                }
            } else {//retrieve
                System.out.println("Not in cache. Retrieving. #"+n);
                System.out.println("Not in cache. Retrieving. #"+n);
                try {
                    String fileName = retrieveAndSave(httpRequest,null);
                    if(fileName.equals("BW")){
                        result=new HttpResponse("banned.html",true);
                    }else{
                        HashtableNode htn = new HashtableNode();
                        htn.fileName = fileName;
                        htn.savedDate = new Date();
                        cached.put(httpRequest.getObjectAddress(), htn);
                        result = new HttpResponse(CACHE_FOLDER_NAME + fileName, false);
                    }
                } catch (UnknownHostException e) {
                    result = new HttpResponse();
                }
            }
        }
        return result;
    }

    private boolean isBanned(HttpRequest httpRequest) {
        return blackList.containsKey(httpRequest.getHost().toLowerCase());
    }

    private void sendResponse(Socket connection, HttpResponse httpResponse) throws IOException {
        String response = httpResponse.toString();
        OutputStream os=connection.getOutputStream();
        byte[] ans=httpResponse.toByteArray();
        os.write(ans);
        os.flush();
        connection.close();
    }

    private String retrieveAndSave(HttpRequest httpRequest,Date cachedDate) throws UnknownHostException, IOException {
        String fileName = "";
        Socket retrSocket = new Socket(httpRequest.getHost(), DEFAULT_HTTP_PORT);
        String myReq=httpRequest.getRequestString();
        if(cachedDate!=null){
            myReq=addIfModified(httpRequest,cachedDate);
        }
        PrintStream printStream = new PrintStream(retrSocket.getOutputStream());
        printStream.print(myReq);
        BufferedInputStream inputStream = new BufferedInputStream(retrSocket.getInputStream());
        Scanner scanner=new Scanner(inputStream);
        byte[] buffer = new byte[RETR_BUFFER_SIZE];
        Vector<Byte> object = new Vector<Byte>(1000);
        int len = 0;
        System.out.println("Started retrieving #"+n);
        int bytesRead=0;
        while (-1<(len = inputStream.read(buffer))) {
            bytesRead+=len;
            System.out.println(bytesRead);
            for (int i = 0; i < len; i++) {
                object.add(buffer[i]);
            }
        }
        System.out.println("Bytes Read: "+bytesRead);
        System.out.println("Read all. ");
        Byte[] retrievedObjectTmp = new Byte[object.size()];
        object.toArray(retrievedObjectTmp);
        byte[] retrievedObject = new byte[object.size()];
        for (int i = 0; i < retrievedObjectTmp.length; i++) {
            retrievedObject[i] = (byte) retrievedObjectTmp[i];
        }
        object.clear();
        if(!isModified(retrievedObject,cachedDate)){
            System.out.println("Not Modified");
            return "";
        }
        if(containsBannedWord(retrievedObject)){
            return "BW";
        }
        fileName = generateRandomFileName(CACHE_FOLDER_NAME);
        FileOutputStream writer = new FileOutputStream(new File(CACHE_FOLDER_NAME + fileName));
        writer.write(retrievedObject);
        writer.flush();
        writer.close();
        System.out.println("Saved in "+fileName);
        return fileName;
    }
    private boolean isText(String req){

        StringTokenizer st=new StringTokenizer(req,"\r\n");
        while(st.hasMoreTokens()){
            String line=st.nextToken();
            if(line.startsWith("Content-Type:")){
                int x=line.indexOf(";");
                String typ;
                if(x<0){
                    typ=line.substring("Content-Type: ".length());
                }else{
                    typ=line.substring("Content-Type: ".length(),line.indexOf(";"));
                }
                if(typ.startsWith("text")){
                    return true;
                }else{
                    return false;
                }
            }
        }

        return false;
    }

    private boolean containsBannedWord(byte[] retrievedObject) {
        String os=new String(retrievedObject);
        if(!isText(os)){
            return false;
        }
        StringTokenizer st=new StringTokenizer(os,"\t\f\r\n\" ");
        while(st.hasMoreTokens()){
            String s=st.nextToken();
            System.out.println(s);
            if(bannedWords.containsKey(s.toLowerCase())){
//                JOptionPane.showMessageDialog(null,s);
                return true;
            }
        }
        return false;
    }

    private String addIfModified(HttpRequest httpRequest, Date date) {
        String req=httpRequest.getRequestString();
        String newString="";
        int index=req.indexOf("If-Modified-Since: ");
        if(index<req.indexOf("\r\n\r\n") && index>-1 ){
            int ind=req.indexOf("If-Modified-Since: ");
            int endd=req.indexOf("\r\n",ind);
            newString=req.substring(0,ind+"If-Modified-Since: ".length());
            newString+=getRFCDate(date);
            newString+=req.substring(endd);
        }else{
            int ind=req.indexOf("\r\n\r\n");
            newString=req.substring(0,ind+2);
            newString+="If-Modified-Since: "+getRFCDate(date);
            newString+=req.substring(ind);
        }
        System.out.println(newString);
        return newString;
    }

    private String getRFCDate(Date date) {

//        String s="";
        SimpleDateFormat sdf=new SimpleDateFormat("E, dd-MM-yy HH:mm:ss z");
        return sdf.format(date);
    }

    private boolean isModified(byte[] httpObject, Date cachedDate) {
        if(cachedDate==null)
            return true;
        String s=new String(httpObject);
        StringTokenizer st=new StringTokenizer(s);
        st.nextToken();
        String retCodeS=st.nextToken();
        int retCode=Integer.parseInt(retCodeS);
        return retCode!=HTTP_NOT_MODIFIED;
    }

    private static String generateRandomFileName(String cacheFolder) {
        Random random = new Random();
        long n = random.nextLong();
        boolean cont = false;
        do {
            cont = new File(cacheFolder + n).exists();
        } while (cont);
        return "" + n;
    }
}
