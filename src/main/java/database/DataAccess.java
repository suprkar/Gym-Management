import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DataAccess {
    ResultSet fetchData();
    boolean updatePassword(String userId, String newPassword);
}
public class DatabaseAccess implements DataAccess {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/demodb";
    private static final String USER = "root";
    private static final String PASS = "password";


     * 
     * @param userId the ID of the user whose password is to be updated.
     * @param newPassword the new password to set.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updatePassword(String userId, String newPassword) {

        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, userId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}