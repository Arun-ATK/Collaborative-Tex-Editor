import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    ArrayList<Client> session = new ArrayList<Client>();

    public static void main(String[] args){
        ServerSocket ss = new ServerSocket(3068);
        System.out.println("Server listenting on 3068!\n--------------");

        while(true) {
            try {
                Socket s = ss.accept();
                System.out.println("Client connected!");
            }
            catch(Exception exp){
                exp.printStackTrace();
            }
        }
    }
}
