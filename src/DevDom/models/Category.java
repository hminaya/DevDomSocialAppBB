package DevDom.models;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;


public class Category {

	public Category(int id, String categoryName){
		
		setCategoryName(categoryName);
		setId(id);
		
		tutorials = new Vector();
		
	}
	
	private int id;
	private String categoryName;
	private Vector tutorials;
	private String description;
	private Bitmap image;
	private String imageUrl;
	
	public void setImage(Bitmap image){
		this.image = image;
	}
	
	public Bitmap getImage(){
		return this.image;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	
	public void addTutorial(Tutorial tutorial){
		this.tutorials.addElement(tutorial);
	}
	
	public Vector getTutorials(){
		return tutorials;
	}
	
	public void setTutorials(Vector tutorials){
		this.tutorials = tutorials;
	}
	
}
