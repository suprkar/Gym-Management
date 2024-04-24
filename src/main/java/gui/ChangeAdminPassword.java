import javax.swing.*;
import java.awt.event.*;

public class ChangeAdminPassword extends JFrame {
    private JTextField newPasswordField;
    private JButton changeButton;
    private PasswordService passwordService;

    public ChangeAdminPassword(PasswordService passwordService) {
        this.passwordService = passwordService;
        initComponents();
    }

    private void initComponents() {
        newPasswordField = new JTextField(20);
        changeButton = new JButton("Change Password");

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = newPasswordField.getText();
                boolean success = passwordService.changePassword("admin", newPassword);  // Assuming 'admin' is the user context
                if (success) {
                    JOptionPane.showMessageDialog(null, "Password changed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to change password.");
                }
            }
        });

        this.setLayout(new FlowLayout());
        this.add(newPasswordField);
        this.add(changeButton);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
