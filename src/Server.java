import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static HashMap<Socket,Client> session = new HashMap<>();

    public static void main(String[] args){
        try {   
            ServerSocket ss = new ServerSocket(3068);
            System.out.println("Server listenting on 3068!\n--------------");
            
            while(true) {
                    Socket s = ss.accept();
                    System.out.println("Client requested!");

                    ObjectInputStream ios = new ObjectInputStream(s.getInputStream());
                    ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
                    
                    Client c = (Client)ios.readObject();

                    if(session.size() < 100) {
                        //validate credentials
                        System.out.println("Valid Client!");
                        dos.writeObject(c);
                        session.put(s,c);
                        new ServerListener(session,s).start();
                    }
                    else{
                        dos.writeObject(null);
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}