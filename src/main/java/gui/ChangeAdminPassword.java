import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeAdminPassword extends JFrame {
    private JTextField newPasswordField;
    private JButton changeButton;
    private JLabel statusLabel;
    private PasswordService passwordService;

    public ChangeAdminPassword(PasswordService passwordService) {
        this.passwordService = passwordService;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        newPasswordField = new JTextField(10);
        changeButton = new JButton("Change Password");
        statusLabel = new JLabel();

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = newPasswordField.getText();
                if (passwordService.changePassword("admin", newPassword)) { // Assume "admin" is the user context
                    statusLabel.setText("Password changed successfully.");
                } else {
                    statusLabel.setText("Failed to change password.");
                }
            }
        });
    }

    private void setupLayout() {
        setTitle("Change Admin Password");
        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("New Password:"));
        add(newPasswordField);
        add(changeButton);
        add(statusLabel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Placeholder for PasswordService implementation
        PasswordService passwordService = new PasswordServiceImpl(); // Ensure this class is implemented
        new ChangeAdminPassword(passwordService);
    }
}
