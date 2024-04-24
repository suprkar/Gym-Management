import javax.swing.*;
import java.awt.event.*;

public class AdminLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private DatabaseManager dbManager;

    public AdminLogin(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        initComponents();
    }

    private void initComponents() {
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isValid = dbManager.validateLogin(usernameField.getText(), passwordField.getPassword());
                if (isValid) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout and visual setup code here
        // JFrame settings like setSize, setDefaultCloseOperation, etc.
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManagerImpl();  // In real scenario, consider using Dependency Injection frameworks
        AdminLogin frame = new AdminLogin(dbManager);
        frame.setVisible(true);
    }
}
