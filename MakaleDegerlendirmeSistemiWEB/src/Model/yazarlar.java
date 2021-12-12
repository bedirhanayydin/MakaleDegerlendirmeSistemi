package Model;

public class yazarlar {

	private int ID;
	private String name;
	private String lastname;
	private String Email;
	private String Password;
	private String workplace;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public yazarlar()
	{
		
	}
	public yazarlar(int iD, String name, String lastname, String email, String password, String workplace) {
		super();
		ID = iD;
		this.name = name;
		this.lastname = lastname;
		Email = email;
		Password = password;
		this.workplace = workplace;
	}
	
	
	
}
