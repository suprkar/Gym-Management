public interface UserDataService {
    User fetchUserData(String userId);
    boolean updateUserData(User user);
}

public interface UserSettingService {
    Settings fetchSettings(String userId);
    boolean updateSettings(String userId, Settings settings);
}
