package utils;

import pages.CommonPages.LaunchSite;
import pages.CommonPages.Login;
import pages.CommonPages.PortalLoginPage;


import java.lang.reflect.Method;
import java.util.Map;


public class LoginHelper {

    public static void performLogin(ThreadLocal<LaunchSite> launchSite,
                                    ThreadLocal<PortalLoginPage> loginPage,
                                    Method m, ThreadLocal<Login> login) throws Exception {

        // Retrieve login details
        Map<String, String> details = login.get().Login(m);
        String url = details.get("url");
        String propertyID = details.get("propertyID");
        String username = details.get("username");
        String password = details.get("password");

        // Launch the portal and perform login
        launchSite.get().LaunchPortal(url);
        loginPage.get().LogInToPortal(propertyID, username, password);
    }
}






