package paint.managers;

import java.sql.SQLException;

import paint.dao.PaintedDao;
import paint.entities.Painted;
import paint.entities.Thing;

public class PaintedManager implements Manager {
	private static PaintedManager instance;
	private PaintedDao paintedDao = PaintedDao.getInstance();

	private PaintedManager() {

	}

	public static PaintedManager getInstance() {
		if (instance == null) {
			instance = new PaintedManager();
		}
		return instance;
	}

	@Override
	public String save(Thing thing) {
		try {
			paintedDao.save(thing);
			return "Saved successfully!";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public Painted[] loadAll(Integer userId) {
		try {
			return paintedDao.loadAll(userId);
		} catch (SQLException e) {
			return null;
		}
	}

}
