package Servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.Mail;

/**
 * Servlet implementation class Hakem_MakKabRedServlet
 */
@WebServlet("/Hakem_MakKabRed")
public class Hakem_MakKabRedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_MakKabRedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String is = request.getParameter("cv");
		int makid = Integer.parseInt(request.getParameter("id"));
		int Hid = Integer.parseInt(request.getParameter("HakID"));
		try {		
			HakemMakKabRed(Hid, makid, is);
			HakemMakKabRedMail(Hid, makid, is);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("HakemHome");
	}
	public static void HakemMakKabRed(int hid, int mid, String cv)
    {
        Connection con = ConnectionUtils.getMyConnection();

        try {
            CallableStatement cb = con.prepareCall("{CALL HakemMakKabRed(?, ?, ?)}");
            cb.setInt(1, mid);
            cb.setInt(2, hid);
            cb.setString(3, cv);
            cb.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
	public static void HakemMakKabRedMail(int hid, int mid, String cv) throws SQLException
	{

		if(cv.equals("KABUL_ET"))
		{
			
			ResultSet rs = ConnectionUtils.WHERE("Select name, lastname, email from hakem Where id = '"+hid+"' ");
			if(rs.next())
			{
				
				Mail mail = new Mail("Makale Kabul Islemi","Sn. "+rs.getString("name")+" "+rs.getString("lastname")+" az önce  makale inceleme istegini kabu ettiniz, inceleme "
						+ "raporunu 21 gün icinde sisteme yükleyiniz..");
				try {
				//mail.sendMail("bedirhhanaydin@gmail.com");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(cv.equals("REDDET"))
		{
			/*Þuan Mail Adresleri doðru olmadýðý için hata almamak için yorum satýrý aldým çünkü mail adresleri var olan bir adres deðil mail bulunamadý hatasý alýrým.
			 *Projede bütün mail adresleri doðru girildiði zaman reddete basýnca burdan alan editörüne maili gönderilir.
			 * 
			 * 
			 * 
			 * 
			 * ResultSet rs = ConnectionUtils.WHERE("Select * from makalehakem inner join hakem on hakem.id = makalehakem.hakid "
					+ "inner join makale on makale.ID = makalehakem.makid WHERE hakid = '"+hid+"' AND makid = '"+mid+"'");
			rs.next();
			
			ResultSet rs1 = ConnectionUtils.WHERE("Select * from alaneditor Where id = '"+rs.getInt("AeditorID")+"'");
			
			rs1.next();
			
			
			Mail mail = new Mail("Makale Inceleme Istek Reddi",
					"Sn. "+rs1.getString("name")+" "+rs1.getString("lastname")+" "+rs.getString("HakemDate")+" tarihinde "
			+rs.getString("name")+" "+rs.getString("lastname")+" 'a gonderdiginiz makale inceleme istegini kabul etmemistir. Sistem uzerinden ilgili makaleye yeni hakem atayiniz.. "
					+ " Makale; Dosya adý:"+rs.getString("fileName")+" Acýklama:"+rs.getString("Ek"));
			
		try {	
				//mail.sendMail("bedirhhanaydin@gmail.com");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	*/		
		}
		
	}


}
