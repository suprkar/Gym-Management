import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame {
    private DataAccess dataAccess;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public AdminPage(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        dataTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Column1", "Column2"}, 0);
        dataTable.setModel(tableModel);
        fetchData();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(new JScrollPane(dataTable), BorderLayout.CENTER);

        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
    }

    private void fetchData() {
        try {
            ResultSet rs = dataAccess.fetchData();
            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getString("Column1"), rs.getString("Column2")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataAccess dataAccess = new DataAccessImpl(); 
        AdminPage frame = new AdminPage(dataAccess);
        frame.setVisible(true);
    }
}
