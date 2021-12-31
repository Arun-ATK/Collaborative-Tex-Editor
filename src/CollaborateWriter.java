import java.net.*;
import java.io.*;

/*
Detects the change in content of the current client's editor
and notifies change to the server
*/

public class CollaborateWriter extends Thread{
    Client client;
    Socket socket;

    public CollaborateWriter(Client client, Socket socket){
        this.client = client;
        this.socket = socket;
    }

    public void run(){
        String previousText = "";
        
        try {
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

            while(true){
                String currentText = this.client.getClientEditor().getContent();

                //System.out.println("-->" + currentText);

                if(currentText.equals(previousText) == false){
                    //notify server about change
                    System.out.println(currentText);
                    dos.writeUTF(currentText);
                }
                
                previousText = currentText;
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
    }

}
