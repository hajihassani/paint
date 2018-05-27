package paint.entities;

import paint.PColor;
import paint.PShape;

public class Painted extends Thing {
	private PColor color;
	private PShape shape;
	private Integer startX, startY;
	private Integer endX, endY;
	private Integer userId;

	public Painted(Integer id, PColor color, PShape shape, Integer startX, Integer startY, Integer endX, Integer endY,
			Integer userId) {
		super(id);
		this.color = color;
		this.shape = shape;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.userId = userId;
	}

	public Painted(PColor color, PShape shape, Integer startX, Integer startY, Integer endX, Integer endY,
			Integer userId) {
		super();
		this.color = color;
		this.shape = shape;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.userId = userId;
	}

	public Painted() {
		super();
	}

	public PColor getColor() {
		return color;
	}

	public void setColor(PColor color) {
		this.color = color;
	}

	public PShape getShape() {
		return shape;
	}

	public void setShape(PShape shape) {
		this.shape = shape;
	}

	public Integer getStartX() {
		return startX;
	}

	public void setStartX(Integer startX) {
		this.startX = startX;
	}

	public Integer getStartY() {
		return startY;
	}

	public void setStartY(Integer startY) {
		this.startY = startY;
	}

	public Integer getEndX() {
		return endX;
	}

	public void setEndX(Integer endX) {
		this.endX = endX;
	}

	public Integer getEndY() {
		return endY;
	}

	public void setEndY(Integer endY) {
		this.endY = endY;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
