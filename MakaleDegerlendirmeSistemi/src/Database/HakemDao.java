package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.ConnectionUtils;

public class HakemDao {
	private static Boolean VarMi(String email) throws SQLException {
		boolean temp=false;
		Connection cn =  ConnectionUtils.getMyConnection();
		PreparedStatement pr = cn.prepareStatement("SELECT * FROM hakem WHERE email = ?");
		pr.setString(1, email);
		ResultSet rs = pr.executeQuery();
		temp = rs.next();
		return temp;
	}
	public static String HakemInsert(Model.Hakem hakem) throws SQLException {
	if(!VarMi(hakem.getEmail()))
	{
	Connection cn =  ConnectionUtils.getMyConnection();
	PreparedStatement pr = cn.prepareStatement("{CALL hakemInsert(?, ?, ?, ?, ?)}");
	pr.setString(1, hakem.getName());
	pr.setString(2, hakem.getLastname());
	pr.setString(3, hakem.getEmail());
	pr.setString(4, hakem.getPassword());
	pr.setInt(5, hakem.getKategori().getId());
	pr.executeUpdate();
		return "islem basarili...";
	}
	else return "islem basarisiz, girilen email zaten kayitli";
	}
	public static String HakemUpdate(Model.Hakem hakem) throws SQLException{
		ResultSet rs = ConnectionUtils.WHERE("SELECT * FROM hakem WHERE email = '"+ hakem.getEmail()+"' AND id != '"+hakem.getId()+"'");
		if(!rs.next())
		{
		Connection cn =  ConnectionUtils.getMyConnection();
		CallableStatement pr = cn.prepareCall("{CALL hakemUpdate(?, ?, ?, ?, ?, ?)}");
		pr.setString(1, hakem.getName());
		pr.setString(2, hakem.getLastname());
		pr.setString(3, hakem.getEmail());
		pr.setString(4, hakem.getPassword());
		pr.setInt(5, hakem.getId());
		pr.setInt(6, hakem.getKategori().getId());
		pr.executeUpdate();
			return "islem basarili...";
		}
		else return "islem basarisiz, girilen email zaten kayitli";
	}
	public static String hakemDelete(int id)
	{
		Connection cn =  ConnectionUtils.getMyConnection();
		CallableStatement cpr;
		try {
			cpr = cn.prepareCall("{CALL hakemDelete(?)}");
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
