public class ManagerIdChangeService implements IdChangeService {
    @Override
    public boolean changeLoginId(String currentId, String password, String newId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym_management", "user", "password")) {
            // First, verify the current ID and password
            String verifySql = "SELECT * FROM managers WHERE login_id = ? AND password = ?";
            PreparedStatement verifyStmt = conn.prepareStatement(verifySql);
            verifyStmt.setString(1, currentId);
            verifyStmt.setString(2, password);
            ResultSet rs = verifyStmt.executeQuery();
            if (!rs.next()) {
                return false; // No matching user found, or password is incorrect
            }

            // Update login ID
            String updateSql = "UPDATE managers SET login_id = ? WHERE login_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, newId);
            updateStmt.setString(2, currentId);
            int affectedRows = updateStmt.executeUpdate();
            return affectedRows > 0; // Return true if the ID was successfully updated
        } catch (SQLException e) {
            System.err.println("ID change failed: " + e.getMessage());
            return false;
        }
    }
}
