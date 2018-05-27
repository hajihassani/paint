package paint.dao;

import java.sql.SQLException;

import paint.entities.Thing;

public interface Dao {
	void save(Thing thing) throws SQLException;
}
