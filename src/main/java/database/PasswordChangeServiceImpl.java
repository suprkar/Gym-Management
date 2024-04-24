import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerPasswordChangeService implements PasswordChangeService {
    @Override
    public boolean changePassword(String loginId, String oldPassword, String newPassword, String confirmPassword) {
       
        if (!newPassword.equals(confirmPassword)) {
            return false; 
        }
        // Connect to the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym_management", "root", "pes2ug21cs556")) {
            // Check if the old password is correct
            String checkPasswordSql = "SELECT password FROM managers WHERE login_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkPasswordSql);
            checkStmt.setString(1, loginId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (!dbPassword.equals(oldPassword)) {
                    return false; 
                }
            } else {
                return false;
            }

            
            String updateSql = "UPDATE managers SET password = ? WHERE login_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, loginId);
            int affectedRows = updateStmt.executeUpdate();
            return affectedRows > 0; /
        } catch (SQLException e) {
            System.err.println("Password change failed: " + e.getMessage());
            return false;
        }
    }
}
