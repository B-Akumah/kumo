package edu.csis;

import edu.csis.dao.UserDao;
import edu.csis.model.User;
import edu.csis.services.UserDatabaseService;
import edu.csis.view.DashboardPage;
import org.springframework.stereotype.Controller;

/**
 * @author bakumah
 */
public class DashboardController {

    private final UserDatabaseService userDatabaseService;

    private User user;
    public DashboardController(final UserDao userDao, int loginId) {
        userDatabaseService = new UserDatabaseService(userDao);
        user = userDatabaseService.getUserById(loginId);
        DashboardPage dashboardPage = new DashboardPage(user);
    }

}
