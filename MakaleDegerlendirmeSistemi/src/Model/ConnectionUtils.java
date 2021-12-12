package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getMyConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makaledegerlendirme?useSSL=false","root","BURAYA KENDÝ VERÝTABANINIZDAKÝ ÞÝFRENÝZÝ GÝRÝNÝZ!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
public static ResultSet WHERE(String sorgu ) throws SQLException {
		
		Connection cn = getMyConnection();
		
		PreparedStatement pr = cn.prepareStatement(sorgu);
		ResultSet rs = pr.executeQuery();
		
		return rs;
	}
}
