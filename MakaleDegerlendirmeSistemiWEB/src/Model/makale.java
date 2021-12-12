package Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;


public class makale  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int yazID;
	private yazarlar yazar;
	private String fileName;
	private Blob fileData;
	private String yazarBilgi;
	private String makaleBilgi;
	private Date yuklenDate;
	private Date editorDate;
	private Date aEditorDate;
	private Date hakemDate;
	private int puan;
	private Alaneditor aeditor;
	private int revizyon;
	private int onay;

	public int getOnay() {
		return onay;
	}
	public void setOnay(int onay) {
		this.onay = onay;
	}
	public int getRevizyon() {
		return revizyon;
	}
	public void setRevizyon(int revizyon) {
		this.revizyon = revizyon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPuan() {
		return puan;
	}
	public void setPuan(int puan) {
		this.puan = puan;
	}
	public Alaneditor getAeditor() {
		return aeditor;
	}
	public void setAeditor(Alaneditor aeditor) {
		this.aeditor = aeditor;
	}
	public yazarlar getYazar() {
		return yazar;
	}
	public void setYazar(yazarlar yazar) {
		this.yazar = yazar;
	}
	public int getID() {
		return id;
	}
	public void setID(int iD) {
		id = iD;
	}
	public int getYazID() {
		return yazID;
	}
	public void setYazID(int yazID) {
		this.yazID = yazID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Blob getFileData() {
		return fileData;
	}
	public void setFileData(Blob fileData) {
		this.fileData = fileData;
	}
	public String getYazarBilgi() {
		return yazarBilgi;
	}
	public void setYazarBilgi(String YazarBilgi) {
		this.yazarBilgi = YazarBilgi;
	}
	public String getMakaleBilgi() {
		return makaleBilgi;
	}
	public void setMakaleBilgi(String MakaleBilgi) {
		this.makaleBilgi = MakaleBilgi;
	}
	public Date getYuklenDate() {
		return yuklenDate;
	}
	public void setYuklenDate(Date yuklenDate) {
		this.yuklenDate = yuklenDate;
	}
	public Date getEditorDate() {
		return editorDate;
	}
	public void setEditorDate(Date editorDate) {
		this.editorDate = editorDate;
	}
	public Date getaEditorDate() {
		return aEditorDate;
	}
	public void setaEditorDate(Date aEditorDate) {
		this.aEditorDate = aEditorDate;
	}
	public Date getHakemDate() {
		return hakemDate;
	}
	public void setHakemDate(Date hakemDate) {
		this.hakemDate = hakemDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public makale(int iD, int yazID, String fileName, Blob fileData, String YazarBilgi, String MakaleBilgi, Date yuklenDate,
			Date editorDate, Date aEditorDate, Date hakemDate, int revizyon, int onay) {
		super();
		id = iD;
		this.yazID = yazID;
		this.fileName = fileName;
		this.fileData = fileData;
		this.yazarBilgi = YazarBilgi;
		this.makaleBilgi = MakaleBilgi;
		this.yuklenDate = yuklenDate;
		this.editorDate = editorDate;
		this.aEditorDate = aEditorDate;
		this.hakemDate = hakemDate;
		this.revizyon = revizyon;
		this.onay = onay;
	}
	public makale(int yazID, String fileName, Blob fileData, String YazarBilgi, String MakaleBilgi, Date yuklenDate,
			Date editorDate, Date aEditorDate, Date hakemDate) {
		super();
		this.yazID = yazID;
		this.fileName = fileName;
		this.fileData = fileData;
		this.yazarBilgi = YazarBilgi;
		this.makaleBilgi = MakaleBilgi;
		this.yuklenDate = yuklenDate;
		this.editorDate = editorDate;
		this.aEditorDate = aEditorDate;
		this.hakemDate = hakemDate;
	}
	
	public makale(int id, yazarlar yazar, String fileName, Blob fileData, String YazarBilgi, String MakaleBilgi, Date yuklenDate,
			Date editorDate, Date aEditorDate, Date hakemDate, int puan, int revizyon ) {
		super();
		this.id = id;
		this.yazar = yazar;
		this.fileName = fileName;
		this.fileData = fileData;
		this.yazarBilgi = YazarBilgi;
		this.makaleBilgi = MakaleBilgi;
		this.yuklenDate = yuklenDate;
		this.editorDate = editorDate;
		this.aEditorDate = aEditorDate;
		this.hakemDate = hakemDate;
		this.revizyon = revizyon;
		this.puan = puan;
	}
	
	
	public makale()
	{
		id = -1;
	}
	

}
