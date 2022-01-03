import java.net.*;
import java.io.*;
import java.lang.Thread;
import java.util.*;

/*
Detects the change in content of the current client's editor
and notifies change to the server
*/

public class ClientWriter extends Thread{
    Client client;
    Socket socket;

    public ClientWriter(Client client, Socket socket){
        this.client = client;
        this.socket = socket;
    }

    public void run(){
        String previousText = "";
        
        try {
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

            while(true){

                String currentText = this.client.getClientEditor().getContent();
                if(currentText == null){
                    currentText = "";
                }
                //System.out.println("-->" + currentText);

                if(currentText.equals(previousText) == false){
                    this.client.Lock();
                    //notify server about change
                    //System.out.println(currentText);
                    dos.writeUTF(currentText);
                    previousText = currentText;
                    continue;
                }
                
                previousText = currentText;

                Thread.sleep(200);
                this.client.Unlock();
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }

}
