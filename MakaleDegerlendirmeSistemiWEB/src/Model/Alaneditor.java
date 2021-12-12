package Model;

public class Alaneditor {

	private int id;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private Kategori kategori;
	
	public Kategori getKategori() {
		return kategori;
	}
	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public Alaneditor(int id, String name, String lastname, String email, String password, Kategori kategori) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.kategori = kategori;
	}
	

	public Alaneditor(String name, String lastname, String email, String password, Kategori kategori) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.kategori = kategori;
	}
	public Alaneditor()
	{
		this.id = -1;
		//this.kategori.setId(-1);
	}
	
	
	
}
