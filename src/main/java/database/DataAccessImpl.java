import java.sql.*;

public class DataAccessImpl implements DataAccess {
    private Connection connection;

    public DataAccessImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "pes2ug21cs556");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet fetchData() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tableName");
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePassword(String userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPassword);  // Directly using the new password without hashing
            stmt.setString(2, userId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
