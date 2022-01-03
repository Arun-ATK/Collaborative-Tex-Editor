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
            String prev1 = "",prev2 = "";

            while(true) {
                String content = (String)dis.readUTF();
                if(prev2.equals(content))
                    continue;

                //System.out.println("{" + content + "}");

                while(mutex) {}
                mutex = true;
                
                //use write to send the content to all the other clients

                for(HashMap.Entry<Socket,Client> m : thisSession.entrySet()){
                    if(m.getKey() != this.thisClient){
                        DataOutputStream dos = new DataOutputStream(m.getKey().getOutputStream());
                        dos.writeUTF(content);
                    }
                }
                mutex = false;
                prev2 = prev1;
                prev1 = content;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
