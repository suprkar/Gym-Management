import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    private DatabaseManager dbManager;

    public AdminLogin(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        statusLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                if(username.isEmpty() || password.length == 0) {
                    statusLabel.setText("Username and Password cannot be empty.");
                } else {
                    boolean isValid = dbManager.validateLogin(username, new String(password));
                    if (isValid) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        new AdminPage(new DataAccessImpl()).setVisible(true); // Assuming DataAccessImpl is correct
                        dispose(); // Close login window
                    } else {
                        statusLabel.setText("Invalid Credentials");
                    }
                }
            }
        });
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("Username:"));
        northPanel.add(usernameField);
        northPanel.add(new JLabel("Password:"));
        northPanel.add(passwordField);
        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(loginButton);
        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(statusLabel);
        add(southPanel, BorderLayout.SOUTH);

        setTitle("Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManagerImpl(); // Assume DatabaseManagerImpl is correctly implemented
        AdminLogin frame = new AdminLogin(dbManager);
        frame.setVisible(true);
    }
}
