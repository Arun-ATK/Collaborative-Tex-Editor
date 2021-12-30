public class Client {
    private String username;
    private String password;

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

    public boolean registerClient(){
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
        
    }
}
