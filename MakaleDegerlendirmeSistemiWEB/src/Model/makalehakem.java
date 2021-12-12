package Model;

import java.sql.Blob;
import java.sql.Date;

public class makalehakem {

	private int id;
	private Hakem hak;
	private Hakem hak1;
	private Hakem hak2;
	private makale mak;
	private int durum;
	private Date date;
	private Date kabulDate;
	private Date raporDate;
	private Blob raporData;
	private String raporName;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getKabulDate() {
		return kabulDate;
	}
	public void setKabulDate(Date kabulDate) {
		this.kabulDate = kabulDate;
	}
	public Date getRaporDate() {
		return raporDate;
	}
	public void setRaporDate(Date raporDate) {
		this.raporDate = raporDate;
	}
	public Blob getRaporData() {
		return raporData;
	}
	public void setRaporData(Blob raporData) {
		this.raporData = raporData;
	}
	public String getRaporName() {
		return raporName;
	}
	public void setRaporName(String raporName) {
		this.raporName = raporName;
	}
	public int getDurum() {
		return durum;
	}
	public void setDurum(int durum) {
		this.durum = durum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Hakem getHak() {
		return hak;
	}
	public void setHak(Hakem hak) {
		this.hak = hak;
	}
	public makale getMak() {
		return mak;
	}
	public void setMak(makale mak) {
		this.mak = mak;
	}

	public Hakem getHak1() {
		return hak1;
	}
	public void setHak1(Hakem hak1) {
		this.hak1 = hak1;
	}
	public Hakem getHak2() {
		return hak2;
	}
	public void setHak2(Hakem hak2) {
		this.hak2 = hak2;
	}
	
	
	
	
	
	public makalehakem(int id, makale mak, int durum, Date date, Date kabulDate, Date raporDate, Blob raporData,
			String raporName) {
		super();
		this.id = id;
		this.mak = mak;
		this.durum = durum;
		this.date = date;
		this.kabulDate = kabulDate;
		this.raporDate = raporDate;
		this.raporData = raporData;
		this.raporName = raporName;
	}
	public makalehakem(int id, Hakem hak, Hakem hak1, Hakem hak2, makale mak) {
		super();
		this.id = id;
		this.hak = hak;
		this.hak1 = hak1;
		this.hak2 = hak2;
		this.mak = mak;
	}
	public makalehakem(Hakem hak, Hakem hak1, Hakem hak2, makale mak) {
		super();
		this.hak = hak;
		this.hak1 = hak1;
		this.hak2 = hak2;
		this.mak = mak;
		
	}
	public makalehakem(Blob raporData, String raporName) {
		super();
		this.raporData = raporData;
		this.raporName = raporName;
	}
	public makalehakem(int id, Hakem hak, int durum, Date date, Date kabulDate, Date raporDate, Blob raporData,
			String raporName) {
		super();
		this.id = id;
		this.hak = hak;
		this.durum = durum;
		this.date = date;
		this.kabulDate = kabulDate;
		this.raporDate = raporDate;
		this.raporData = raporData;
		this.raporName = raporName;
	}


	
	
	
}
