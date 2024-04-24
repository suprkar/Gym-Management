import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChangeManagerId extends JFrame {
    private JTextField currentIdField;
    private JPasswordField passwordField;
    private JTextField newIdField;
    private JButton submitButton;
    private IdChangeService idChangeService;

    public ChangeManagerId(IdChangeService idChangeService) {
        this.idChangeService = idChangeService;
        initializeComponents();
        setupUI();
    }

    private void setupUI() {
        setTitle("Change Manager ID");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        currentIdField = new JTextField(20);
        passwordField = new JPasswordField(20);
        newIdField = new JTextField(20);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            boolean success = idChangeService.changeLoginId(
                currentIdField.getText(),
                new String(passwordField.getPassword()),
                newIdField.getText()
            );
            JOptionPane.showMessageDialog(null, "ID Change " + (success ? "Successful" : "Failed"));
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Current Login ID:"));
        add(currentIdField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("New Login ID:"));
        add(newIdField);
        add(submitButton);
    }

    public static void main(String[] args) {
        IdChangeService service = new ManagerIdChangeService();
        new ChangeManagerId(service);
    }
}
