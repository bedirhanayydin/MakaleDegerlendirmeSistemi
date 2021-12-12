package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Alaneditor;
import Model.ConnectionUtils;

public class AeditorDao {

	
	private static Boolean VarMi(String email) throws SQLException {
		boolean temp=false;
		Connection cn = ConnectionUtils.getMyConnection();
		
		PreparedStatement pr = cn.prepareStatement("SELECT * FROM alaneditor WHERE email = ?");
		pr.setString(1, email);
		ResultSet rs = pr.executeQuery();
		
		temp = rs.next();
		
		return temp;
	}
	
	
	
	
	
	public static String AeditorInsert(Alaneditor eAeditor) throws SQLException {
		
	if(!VarMi(eAeditor.getEmail()))
	{
	Connection cn = ConnectionUtils.getMyConnection();
	
	PreparedStatement pr = cn.prepareStatement("{CALL aeditorInsert(?, ?, ?, ?, ?)}");
	pr.setString(1, eAeditor.getName());
	pr.setString(2, eAeditor.getLastname());
	pr.setString(3, eAeditor.getEmail());
	pr.setString(4, eAeditor.getPassword());
	pr.setInt(5, eAeditor.getKategori().getId());
	pr.executeUpdate();
		return "islem basarili...";
	}
	else return "islem basarisiz, girilen email zaten kayitli";
	
	}
	
	public static String AeditorUpdate(Alaneditor aeditor) throws SQLException{
	
		
		ResultSet rs = ConnectionUtils.WHERE("SELECT * FROM alaneditor WHERE email = '"+ aeditor.getEmail()+"' AND id != '"+aeditor.getId()+"'");
		
		
		if(!rs.next())
		{
		Connection cn = ConnectionUtils.getMyConnection();
		
		CallableStatement pr = cn.prepareCall("{CALL aeditorUpdate(?, ?, ?, ?, ?, ?)}");
		
		//PreparedStatement pr = cn.prepareStatement("{CALL aeditorUpdate(?, ?, ?, ?, ?)}");
		
		
		pr.setString(1, aeditor.getName());
		pr.setString(2, aeditor.getLastname());
		pr.setString(3, aeditor.getEmail());
		pr.setString(4, aeditor.getPassword());
		pr.setInt(5, aeditor.getId());
		pr.setInt(6, aeditor.getKategori().getId());
		
		pr.executeUpdate();
			return "islem basarili...";
		}
		else return "islem basarisiz, girilen email zaten kayitli";
		
		
		
		
	}
	
	public static String AeditorDelete(int id)
	{
		Connection cn = ConnectionUtils.getMyConnection();
		CallableStatement cpr;
		try {
			cpr = cn.prepareCall("{CALL aeditorDelete(?)}");
			cpr.setInt(1, id);
		
		cpr.executeUpdate();
		
		return "Islem basarili..";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Sistemsel Hatta Olustu...!!";
		}
		
		
	}
	
	
	
	
}
