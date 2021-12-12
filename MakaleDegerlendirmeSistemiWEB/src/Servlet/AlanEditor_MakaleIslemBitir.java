package Servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
/**
 * Servlet implementation class Aeditor_MakRaporSONServlet
 */
@WebServlet("/AlanEditor_MakaleIslemBitir")
public class AlanEditor_MakaleIslemBitir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlanEditor_MakaleIslemBitir() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  int AEID = -1;
   	   Cookie[] cookies = request.getCookies();
   	   if(cookies !=null){
   	   for(Cookie cookie : cookies){
   		   if(cookie.getName().equals("AlanEditorID"))
   		   { AEID = Integer.parseInt(cookie.getValue()); break;}
   	   	
   	   }
   	   }
   	   
   	   if(AEID == -1)
   	   {
   		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/AlanEditor_Login.jsp");
		 
        dispatcher.forward(request, response);
   	   }
   	   else
   	   {
   	   int rapid = Integer.parseInt(request.getParameter("id"));
   	   
   	   try {
		MakaleDegerSon(rapid, AEID);
   	   } catch (Exception e) {
		// TODO Auto-generated catch block
   		  e.printStackTrace();
   	   }
   	   
   	   RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/MakPuanViewer.jsp");
	 
   	   dispatcher.forward(request, response);
   	   
   	   }
	}
	public static void MakaleDegerSon(int rapid, int AEID ) throws Exception
    {
        ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID inner join makalehakem on makale.ID = makalehakem.makid"
                + " Where makalehakem.id ='"+rapid+"' AND AeditorID = '"+AEID+"'");
        
        
        if(rs.next());
        {/*PUAN DURUMUNA GÖRE MAÝL YOLLAMA
            if(rs.getInt("Puan") >= 75)
            {

            Mail mail = new Mail("Makale Onay Durum", "Sn. "+rs.getString("name")+" "+rs.getString("lastname")+" "+rs.getDate("YuklenDate")+" tarihinde "
                    + "gondermis oldugunuz makale dergide yayýnlanmaya hak kazanmýstýr.."
                            + "Makle=> Dosya adý: "+rs.getString("fileName"));
            // mail.sendMail(rs.getString("email"));

            mail.sendMail("bedirhhanaydin@gmail.com");
            }
            else
            {
                Mail mail = new Mail("Makale Onay Durum", "Sn. "+rs.getString("name")+" "+rs.getString("lastname")+" "+rs.getDate("YuklenDate")+" tarihinde "
                        + "gondermis oldugunuz makale dergide yayýnlanmaya yeterli gorulmemistir."
                        + "Not: uygun gorulmeyen makaleler sistem üzerinden otomatik silinir!!"
                        + "Makle=> Dosya adý: "+rs.getString("fileName"));
                // mail.sendMail(rs.getString("email"));
 			mail.sendMail("bedirhhanaydin@gmail.com");*/
           
        	Connection con = ConnectionUtils.getMyConnection();
            CallableStatement cb = con.prepareCall("{CALL MakOnay(?, ?)}");
            cb.setInt(1, rs.getInt("makale.ID"));
            cb.setInt(2, rs.getInt("Puan"));
            cb.executeUpdate(); 
            
        }

            
        }
}
