public interface DataAccess {
    ResultSet fetchData();
    boolean updatePassword(String userId, String newPassword);
}