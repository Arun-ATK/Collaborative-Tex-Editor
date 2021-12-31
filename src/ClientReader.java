import java.io.*;
import java.util.*;
import java.net.*;

/*
Receives the content changed by the other clients from the server
and updates the current client's editor
*/

public class ClientReader extends Thread {
    Client client;
    Socket socket;

    public ClientReader(Client client, Socket socket){
        this.client = client;
        this.socket = socket;
    }

    public void run(){
        try{
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            
            while(true) {
                String content = (String)dis.readUTF();
                System.out.println(content);
                this.client.getClientEditor().setContent(content);
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }
}
