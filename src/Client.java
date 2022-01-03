import java.io.*;
import java.net.*;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Editor clientEditor;
    private boolean lock = false;

    public Client(String username,String password){
        this.username = username;
        this.password = password;
        System.out.println("[+] " + username);
        System.out.println("[+] " + password);
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

    public Editor getClientEditor(){
        return this.clientEditor;
    }

    public void Lock(){
        this.lock = true;
    }

    public void Unlock(){
        this.lock = false;
    }

    public boolean getLock(){
        return this.lock;
    }

    public boolean connectServer(Socket socket){
        try {
            ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());

            dos.writeObject(this);

            if(ios.readObject() != null){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void throwEditor(Socket socket){
        //throw new editor
        this.clientEditor = new Editor();
        ClientWriter cw =new ClientWriter(this, socket);
        cw.start();
        ClientReader cr = new ClientReader(this, socket);
        cr.start();
    }
}