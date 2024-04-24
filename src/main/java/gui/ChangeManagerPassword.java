import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeManagerPassword extends JFrame {
    private JTextField loginIdField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changePasswordButton;
    private PasswordChangeService passwordChangeService;

    public ChangeManagerPassword(PasswordChangeService passwordChangeService) {
        this.passwordChangeService = passwordChangeService;
        initializeComponents();
        setupUI();
    }

    private void setupUI() {
        setTitle("Change Manager Password");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        loginIdField = new JTextField(20);
        oldPasswordField = new JPasswordField(20);
        newPasswordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        changePasswordButton = new JButton("Change Password");

        changePasswordButton.addActionListener(e -> {
            boolean success = passwordChangeService.changePassword(
                loginIdField.getText(),
                new String(oldPasswordField.getPassword()),
                new String(newPasswordField.getPassword()),
                new String(confirmPasswordField.getPassword())
            );
            JOptionPane.showMessageDialog(null, "Password Change " + (success ? "Successful" : "Failed"));
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Login ID:"));
        add(loginIdField);
        add(new JLabel("Old Password:"));
        add(oldPasswordField);
        add(new JLabel("New Password:"));
        add(newPasswordField);
        add(new JLabel("Confirm New Password:"));
        add(confirmPasswordField);
        add(changePasswordButton);
    }

    public static void main(String[] args) {
        PasswordChangeService service = new ManagerPasswordChangeService();
        SwingUtilities.invokeLater(() -> {
            new ChangeManagerPassword(service).setVisible(true);
        });
    }
}