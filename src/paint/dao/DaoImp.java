package paint.dao;

import java.sql.*;

public abstract class DaoImp implements Dao {
	protected Connection connection;
	protected PreparedStatement ps;
	private String connectURL = "jdbc:mysql://127.0.0.1/paint?user=root";

	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectURL);
		} catch (Exception e) {

		}
	}

	@Override
	protected void finalize() {
		try {
			ps.close();
			connection.close();
		} catch (Exception e) {

		}
	}
}
