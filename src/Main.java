/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
//import javax.swing.JOptionPane;

/**
 *
 * @author Cavad
 */
public class Main extends Thread{
    boolean cont=true;
    private int portNumber=2222;
    private String errorMessage="";
    private String status="";

    public void stopProxy(){
        cont=false;
    }

    @Override
    public void run() {
        try{
            ServerSocket welcomeSocket = new ServerSocket(portNumber);
            int i=0;
            status="Running";
            while (cont) {
                System.out.println("____________________________________________");
                System.out.println("Waiting");
                Socket connection = welcomeSocket.accept();
                connection.setSoTimeout(30000);
                MyThread thread = new MyThread(connection,i++);
                thread.start();
            }
        }catch (Exception e){
            this.errorMessage=e.toString();
            status="Stopped";
        }
    }

    public Main(int portNumber) {
        this.portNumber=portNumber;
    }

    public String getStatus() {
        return status;
    }
}
