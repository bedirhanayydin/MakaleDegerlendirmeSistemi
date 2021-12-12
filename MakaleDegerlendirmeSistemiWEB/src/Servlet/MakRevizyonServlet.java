package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.Mail;


/**
 * Servlet implementation class MakRevizyonServlet
 */
@WebServlet("/MakRevizyon")
public class MakRevizyonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakRevizyonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/MakViewer.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int id = -1;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("AlanEditorID"))
				{id = Integer.parseInt(cookie.getValue()); break;}
		}
		}
		
		if(id == -1)
		{
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/AlanEditorLogin.jsp");
			 
	        dispatcher.forward(request, response);
		}
		else
		{
			int makid = Integer.parseInt(request.getParameter("id"));
			try {
				makRevizyonON(makid);
				//yazar bilgilendirme mailli gonderme
				//SendRevizyonMail(makid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("AlanEditorHome");
			
		}
		

	}
		public static void makRevizyonON(int makid) throws SQLException
	    {
	        Connection con = ConnectionUtils.getMyConnection();
	        PreparedStatement cb = con.prepareStatement("Update makale set revizyon = 1 WHERE ID = ?");
	        cb.setInt(1, makid);
	        cb.executeUpdate();
	    }
		public static void SendRevizyonMail(int makid) 
	    {

	        try {
	            ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID Where makale.ID ='"+makid+"'");
	            if(rs.next())
	            {
	                Mail mail = new Mail("Makale Revizyon Istegi", "Sn."+rs.getString("name")+" "+rs.getString("lastname")
	                +" "+rs.getString("YuklenDate")+" tarihinde sistemimize yüklemiþ olduðunuz makale yi tekrar gondermeniz istenemktedir."
	                + " 10 gün icinde makaleyi gerekli düzenlemeleri yapýp 'islenen makaleler' kýsmýndan yükleyiniz."
	                +" Makale=> "+rs.getString("MakaleBilgi")+", Dosya Adý: "+rs.getString("fileName"));
	            
	                
	                /*Mailler gerçek olsaydý aþaðýdaki gibi gönderirdim ama mailler deneme oldugu için farklý maile gönderiyorum
	                 * mail.sendMail(rs.getString("email"));
	                 */

	                mail.sendMail("bedirhhanaydin@gmail.com");
	            }

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
