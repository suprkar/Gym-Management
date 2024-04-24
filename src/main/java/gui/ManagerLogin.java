import javax.swing.*;
import java.awt.*;
import java.awt.event.*;   
    
public class ManagerLogin extends JFrame {
private JTextField usernameField;
private JPasswordField passwordField;
private JButton loginButton;
private LoginService loginService;

public ManagerLogin(LoginService loginService) {
        this.loginService = loginService;
        setTitle("Manager Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        addComponents();
        setVisible(true);
    }

private void addComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = loginService.login(usernameField.getText(), new String(passwordField.getPassword()));
                JOptionPane.showMessageDialog(null, "Login " + (success ? "successful" : "failed"));
            }
        });

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
    }

public static void main(String[] args) {
        LoginService service = new ManagerLoginService();
        new ManagerLogin(service);
    }
}