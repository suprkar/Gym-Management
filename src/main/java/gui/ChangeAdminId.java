import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeAdminId extends JFrame {
    private JTextField currentIdField;
    private JTextField newIdField;
    private JButton changeButton;
    private JLabel statusLabel;
    private AdminOperations adminOps;

    public ChangeAdminId(AdminOperations adminOps) {
        this.adminOps = adminOps;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        currentIdField = new JTextField(10);
        newIdField = new JTextField(10);
        changeButton = new JButton("Change ID");
        statusLabel = new JLabel();

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentId = currentIdField.getText();
                String newId = newIdField.getText();
                if (adminOps.updateAdminId(currentId, newId)) {
                    statusLabel.setText("ID updated successfully.");
                } else {
                    statusLabel.setText("Failed to update ID.");
                }
            }
        });
    }

    private void setupLayout() {
        setTitle("Change Admin ID");
        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("Current ID:"));
        add(currentIdField);
        add(new JLabel("New ID:"));
        add(newIdField);
        add(changeButton);
        add(statusLabel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        
        AdminOperations adminOps = new AdminOperationsImpl(); 
        new ChangeAdminId(adminOps);
    }
}
