import java.io.*;
import java.net.*;

public class Client {
    private String username;
    private String password;
    Editor clientEditor;

    public Client(String username,String password){
        this.username = username;
        this.password = password;
        System.out.println(username);
        System.out.println(password);
    }

    public String getUserName(){
        return username;
    }

    public String getPassoword(){
        return password;
    }

    public int registerClient(){
        try{
            //TO DO register in Database
        }
        catch(Exception e){
            return 0;
        }
        return 1;
    }

    public int validClient(){
        //TO DO query DB
        
        //valid username and password
        return 1;

        //username exists but password incorrect -> return 2

        //invalid username and password -> return 3
    }

    public void connectServer(){
        try {
            Socket s = new Socket("localhost",3068);
            DataInputStream dis = new DataInputStream(s.getInputStream());

            String response = (String)dis.readUTF();

            if(response.equals("connect")){
                this.clientEditor = new Editor();
            }
            else {
                System.out.println("Server denied Access!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}