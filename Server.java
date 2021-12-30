import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    static ArrayList<Client> session = new ArrayList<Client>();

    public static void main(String[] args){
        try {
            
        ServerSocket ss = new ServerSocket(3068);
        System.out.println("Server listenting on 3068!\n--------------");
        
        while(true) {
                Socket s = ss.accept();
                System.out.println("Client connected!");
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                
                if(session.size() < 100)
                    dos.writeUTF("connect");
                else
                    dos.writeUTF("");

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
