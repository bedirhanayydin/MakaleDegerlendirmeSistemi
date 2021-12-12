package Database;

import Model.Yonetici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.ConnectionUtils;


public class YoneticiDao {

	public static Yonetici YoneticiLogin(Yonetici yon) throws SQLException {
		
		
		
		Connection con = ConnectionUtils.getMyConnection();
		PreparedStatement pr = con.prepareStatement("{CALL YoneticiLogin(?, ?)}");
		pr.setString(1, yon.getEmail());
		pr.setString(2, yon.getPassword());
		
		ResultSet rs = pr.executeQuery();
		
		while(rs.next())
		{
			yon.setId(rs.getInt("ID"));
			yon.setName(rs.getString("name"));
			yon.setLastname(rs.getString("lastname"));
			
		}
		
		return yon;
	}
	
	public static String YoneticiUpdate(Yonetici yon)
	{
		
		
		Connection con = ConnectionUtils.getMyConnection();
		PreparedStatement pr;
		try {
			pr = con.prepareStatement("{CALL YoneticiUpdate(?, ?, ?, ?, ?)}");
			
			pr.setInt(1, yon.getId());
			pr.setString(2, yon.getName());
			pr.setString(3, yon.getLastname());
			pr.setString(4, yon.getEmail());
			pr.setString(5, yon.getPassword());
			pr.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return "HATA";
		}
		
		
		
		return "Profil Güncelleme Ýþlemi Baþarýyla Gerçekleþti!!!";
	}
	
	
}
