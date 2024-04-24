import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private AuthenticationService authService;

    public UserLogin(AuthenticationService authService) {
        this.authService = authService;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        userIdField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        statusLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());
                if (authService.authenticateUser(userId, password)) {
                    statusLabel.setText("Login Successful");
                    // Additional actions post-login could be added here, like opening another frame
                } else {
                    statusLabel.setText("Invalid Login ID or Password");
                }
            }
        });
    }

    private void setupLayout() {
        setTitle("User Login");
        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("User ID:"));
        add(userIdField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(statusLabel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Placeholder for AuthenticationService implementation
        AuthenticationService authService = new AuthenticationServiceImpl(); // Ensure this class is implemented
        new UserLogin(authService);
    }
}
