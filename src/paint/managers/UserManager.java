package paint.managers;

import java.sql.SQLException;

import paint.dao.UserDao;
import paint.entities.Thing;
import paint.entities.User;

public class UserManager implements Manager {
	private static UserManager instance;
	private UserDao userDao = UserDao.getInstance();

	private UserManager() {

	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	@Override
	public String save(Thing thing) {
		try {
			userDao.save(thing);
			return "Saved successfully!";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	public User load(String username){
		try {
			return userDao.load(username);
		} catch (SQLException e) {
			return null;
		}
	}

}
