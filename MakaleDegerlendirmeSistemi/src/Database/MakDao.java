package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import Model.ConnectionUtils;

public class MakDao {
	public static void makDateUpdate(int id, Date d, int sec) throws SQLException {
		Connection cn =  ConnectionUtils.getMyConnection();
		 java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		CallableStatement cpr;
			cpr = cn.prepareCall("{CALL makDateUpdate(?, ?, ?)}");
			cpr.setInt(1, id);
			cpr.setDate(2, sqlDate);
			cpr.setInt(3, sec);
			cpr.executeUpdate();
	}
	public static String makAeditorInsert(int makID, int AEID)
	{
		Connection con =  ConnectionUtils.getMyConnection();
		try {
			CallableStatement cpr = con.prepareCall("{CALL makAeditorUpdate(?, ?)}");
			cpr.setInt(1, makID);
			cpr.setInt(2, AEID);
			cpr.executeUpdate();
			return "Islem basarili...";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Sistemsel Hata!!";
	}
public static void makDelete(int id) throws SQLException {
		
		Connection cn =  ConnectionUtils.getMyConnection();
		
		CallableStatement cpr;
		
			cpr = cn.prepareCall("{CALL makDelete(?)}");
			
			cpr.setInt(1, id);
			cpr.executeUpdate();
		
	}
	
	
}
