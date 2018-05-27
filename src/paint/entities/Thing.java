package paint.entities;

public abstract class Thing {
	private Integer id;

	public Thing(Integer id) {
		super();
		this.id = id;
	}
	
	public Thing() {
		super();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
