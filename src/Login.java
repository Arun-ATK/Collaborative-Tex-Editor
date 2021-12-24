import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
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
            System.out.println(usernameField.getText());
            System.out.println(passwordField.getPassword());
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
