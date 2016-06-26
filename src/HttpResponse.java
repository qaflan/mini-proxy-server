/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Cavad
 */
class HttpResponse {
    private byte[] bytes;

    public byte[] toByteArray(){
        return bytes;
    }

    private String strResponse = "";
    private final String header = "HTTP/1.1 200 OK\r\n"
            + "Connextion: close\r\n"
            + "Date: %date%\r\n"
            + "Server: Java(d) Proxy Server\r\n"
            + "Last-Modified: Thu, 3 May 2010\r\n"
            + "Content-Length: %length%\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    public HttpResponse() {
    }

    public HttpResponse(String fileName, boolean addHeader) throws IOException {
        String mHeader="";
        if (addHeader) {
            mHeader = this.header;
            mHeader = mHeader.replace("%date%", new Date().toString());
            mHeader = mHeader.replace("%length%", "68210");
        }
        ArrayList<Byte> cont=new ArrayList<Byte>();

        String content = "";
        FileInputStream inputStream=new FileInputStream(new File(fileName));
        int len=0;
        byte[] buffer=new byte[2000];
        while (-1<(len = inputStream.read(buffer))) {
            for (int i = 0; i < len; i++) {
                cont.add(buffer[i]);
            }
        }
        Byte[] med;
        int start=0;
        if (addHeader) {
            med=new Byte[cont.size()+mHeader.length()];
            for (int i = 0; i < mHeader.length(); i++) {
                med[i]=(byte)mHeader.charAt(i);
            }
            start=mHeader.length();
        }else{
            med=new Byte[cont.size()];
        }
        for(int i=start ; i<med.length ; i++){
            med[i]=cont.get(i-start);
        }
        bytes=new byte[med.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]=med[i];
        }
    }
    @Override
    public String toString() {
        return strResponse;
    }
}
