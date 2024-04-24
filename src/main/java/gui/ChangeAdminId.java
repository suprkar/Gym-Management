import java.swing.*;

public class ChangeAdminId extends JFrame{
    private JTextField currentIdField;
    private JTextField newIdField;
    private JButton changeButton;
    private Handler chain;

    public ChangeAdminId(AdminOperations adminOps){
        setupChain(adminOps);
        initComponents();
    }
    private void setupChain(AdminOperation adminOps){
        Handler validationHandler=new ValidationHandler();
        Handler loggingHandler=new LoggingHandler();
        Handler executionHandler=new ExecutionHandler(adminOps);

        validationHandler.setSuccessor(loggingHandler);
        loggingHandler.setSuccessor(executionHandler);

        this.chain=validationHandler;
    }
     private void initComponents() {
        // GUI setup code
        changeButton.addActionListener(e -> {
            Request request = new Request(currentIdField.getText(), newIdField.getText());
            chain.handleRequest(request);
        });
    }
}