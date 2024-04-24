import java.sql.*;

public class DatabaseManagerImpl implements DatabaseManager {
    private Connection connection;

    public DatabaseManagerImpl() {
        this.connection = DriverManager.getConnection("jdbc:yourdriver:db", "root", "pes2ug21cs556");
    }

    @Override
    public boolean validateLogin(String username, char[] password) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, new String(password));  // Ideally, use hashing and not direct password comparison
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}