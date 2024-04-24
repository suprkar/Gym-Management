public interface PasswordChangeService {
    boolean changePassword(String loginId, String oldPassword, String newPassword, String confirmPassword);
}
