import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 


public class UserPage extends JFrame {
    private UserDataService userDataService;
    private UserSettingService userSettingService;

    public UserPage(UserDataService userDataService, UserSettingService userSettingService) {
        this.userDataService = userDataService;
        this.userSettingService = userSettingService;
        initializeComponents();
        setupUI();
    }

    private void setupUI() {
        setTitle("User Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        
    }

    public static void main(String[] args) {
        UserDataService userDataService = new UserDataServiceImpl();
        UserSettingService userSettingService = new UserSettingServiceImpl();
        new UserPage(userDataService, userSettingService);
    }
}
