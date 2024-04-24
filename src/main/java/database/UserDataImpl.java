import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataServiceImpl implements UserDataService {
    @Override
    public User fetchUserData(String userId) {
        User user = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "pes2ug21cs556")) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();  // Assuming User is a class you have defined
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                // Set other fields as necessary
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch user data: " + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean updateUserData(User user) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "pes2ug21cs556")) {
            String sql = "UPDATE users SET name = ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUserId());
            // Set other fields as necessary
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update user data: " + e.getMessage());
            return false;
        }
    }
}

public class UserSettingServiceImpl implements UserSettingService {
    @Override
    public Settings fetchSettings(String userId) {
        Settings settings = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password")) {
            String sql = "SELECT * FROM settings WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                settings = new Settings();  // Assuming Settings is a class you have defined
                settings.setTheme(rs.getString("theme"));
                // Set other settings as necessary
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch settings: " + e.getMessage());
        }
        return settings;
    }

    @Override
    public boolean updateSettings(String userId, Settings settings) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password")) {
            String sql = "UPDATE settings SET theme = ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, settings.getTheme());
            stmt.setString(2, userId);
            // Update other settings as necessary
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update settings: " + e.getMessage());
            return false;
        }
    }
}
