import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame {
    private DataAccess dataAccess;

    public AdminPage(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
        initComponents();
    }

    private void initComponents() {
        // GUI setup code, like setting background color, layout, etc.
        getContentPane().setBackground(new Color(255, 204, 51));
        updateTable();
    }

    private void updateTable() {
        ResultSet rs = dataAccess.fetchData();
        DefaultTableModel model = (DefaultTableModel) someJTable.getModel();
        // Populate model using ResultSet
    }
}