import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class Login extends JFrame {
    String username;
    String password;

    public Login() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(50, 25));
        JPanel userPanel = new JPanel();
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(50, 25));
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            try {
                username = usernameField.getText();
                password = new String(passwordField.getPassword());
                
                Client c = new Client(username,password);
                
                //opening the socket connection here
                Socket socket = new Socket("localhost",3068);

                if(c.connectServer(socket)){
                    //throw a editor
                    System.out.println("Server accepted request!");
                    c.throwEditor(socket);
                }
                else{
                    System.out.println("Server refused!");
                }
            }
            catch(Exception exp) {
                exp.printStackTrace();
            }
        });
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(userPanel, BorderLayout.NORTH);
        mainPanel.add(passwordPanel, BorderLayout.CENTER);
        mainPanel.add(loginButton, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(260, 150);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Login();
    }
}