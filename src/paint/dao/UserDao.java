package paint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import paint.entities.Thing;
import paint.entities.User;

public class UserDao extends DaoImp {
	private static UserDao instance;

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	@Override
	public void save(Thing thing) throws SQLException {
		User u = null;
		if (thing instanceof User) {
			u = (User) thing;
		}
		String query = "INSERT INTO users(username,password) VALUES(?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.executeUpdate();
	}

	public User load(String username) throws SQLException {
		String query = "SELECT * FROM users WHERE username = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		rs.next();
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));

		return user;
	}
}
