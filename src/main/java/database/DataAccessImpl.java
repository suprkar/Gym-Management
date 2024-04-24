import java.sql.*;

public class DataAccessImpl implements DataAccess {
    private Connection connection;

    public DataAccessImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "password");
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
}