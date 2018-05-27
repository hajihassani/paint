package paint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import paint.PColor;
import paint.PShape;
import paint.entities.Painted;
import paint.entities.Thing;

public class PaintedDao extends DaoImp {
	private static PaintedDao instance;

	private PaintedDao() {

	}

	public static PaintedDao getInstance() {
		if (instance == null) {
			instance = new PaintedDao();
		}
		return instance;
	}

	@Override
	public void save(Thing thing) throws SQLException {
		Painted p = null;
		if (thing instanceof Painted) {
			p = (Painted) thing;
		}
		String query = "INSERT INTO shapes(shape,color,start_x,start_y,end_x,end_y,user_id) VALUES(?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, p.getShape().toString());
		ps.setString(2, p.getColor().toString());
		ps.setInt(3, p.getStartX());
		ps.setInt(4, p.getStartY());
		ps.setInt(5, p.getEndX());
		ps.setInt(6, p.getEndY());
		ps.setInt(7, p.getUserId());
		ps.executeUpdate();
	}

	public Painted[] loadAll(Integer userId) throws SQLException {
		String query = "SELECT * FROM shapes WHERE user_id = ?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();

		rs.last();
		int l = rs.getRow();
		rs.beforeFirst();
		Painted[] painteds = new Painted[l];

		int i = 0;
		while (rs.next()) {
			painteds[i] = new Painted();
			painteds[i].setId(rs.getInt("id"));
			painteds[i].setShape(PShape.valueOf(rs.getString("shape")));
			painteds[i].setColor(PColor.valueOf(rs.getString("color")));
			painteds[i].setStartX(rs.getInt("start_x"));
			painteds[i].setStartY(rs.getInt("start_y"));
			painteds[i].setEndX(rs.getInt("end_x"));
			painteds[i].setEndY(rs.getInt("end_y"));
			painteds[i].setUserId(rs.getInt("user_id"));
			i++;
		}

		return painteds;
	}

}
