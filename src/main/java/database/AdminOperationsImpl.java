import java.sql.*;


public interface AdminOperations {
    boolean updateAdminId(String currentId, String newId);
}

public class AdminOperationsImpl implements AdminOperations {
    private static AdminOperationsImpl instance;
    private Connection connection;

    public AdminOperationsImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized AdminOperationsImpl getInstance(){
        if(instance==NULL){
            instance=new AdminOperationsImpl();
        }
        return instance;
    }

    @Override
    public boolean updateAdminId(String currentId, String newId) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE admins SET id = ? WHERE id = ?")) {
            statement.setString(1, newId);
            statement.setString(2, currentId);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}