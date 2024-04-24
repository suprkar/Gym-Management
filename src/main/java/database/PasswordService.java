public class PasswordService {
    private DataAccess dataAccess;

    public PasswordService(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public boolean changePassword(String userId, String newPassword) {
        return dataAccess.updatePassword(userId, newPassword);
    }
}
