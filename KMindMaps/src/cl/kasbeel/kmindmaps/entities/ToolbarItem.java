package cl.kasbeel.kmindmaps.entities;

public class ToolbarItem {
	private String name;
	private int resource;
	
	public ToolbarItem(String name, int resource) {
		super();
		this.name = name;
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}
	
	
}
