import java.util.*;
import java.io.*;
import java.net.*;

public class ServerListener extends Thread {
    HashMap<Socket,Client> thisSession;
    Socket thisClient;
    public static boolean mutex = false; //to preseve mutual exclusion 

    public ServerListener(HashMap<Socket,Client> thisSession,Socket thisClient){
        this.thisSession = thisSession;
        this.thisClient = thisClient;
    }

    public void run(){
        try{
            DataInputStream dis = new DataInputStream(this.thisClient.getInputStream());

            while(true) {
                String content = (String)dis.readUTF();
                System.out.println("{" + content + "}");

                while(mutex) {}
                mutex = true;

                //use write to send the content to all the other clients

                for(HashMap.Entry<Socket,Client> m : thisSession.entrySet()){
                    if(m.getKey().equals(this.thisClient) == false){
                        DataOutputStream dos = new DataOutputStream(m.getKey().getOutputStream());
                        dos.writeUTF(content);
                    }
                }

                mutex = false;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
