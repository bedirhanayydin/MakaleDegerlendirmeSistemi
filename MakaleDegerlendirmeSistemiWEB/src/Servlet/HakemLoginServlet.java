package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.ConnectionUtils;

/**
 * Servlet implementation class HakemLoginServlet
 */
@WebServlet("/HakemLogin")
public class HakemLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HakemLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/HakemLogin.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailString=request.getParameter("email");
		String passwordString=request.getParameter("password");
		
		try {
			ResultSet myResultset= ConnectionUtils.WHERE("Select * from hakem Where email='"+emailString+"' and password='"+passwordString+"'");
			if (myResultset.next()) {
				
				Cookie loginCookie = new Cookie("HakemID",String.valueOf(myResultset.getInt("id")));
	            Cookie loginCookie1 = new Cookie("HakemNAME",String.valueOf(myResultset.getString("name")));
	            Cookie loginCookie2 = new Cookie("HakemLASTNAME",String.valueOf(myResultset.getString("lastname")));
	            Cookie loginCookie3 = new Cookie("HakemEMAIL",String.valueOf(myResultset.getString("email")));
	            Cookie loginCookie4 = new Cookie("HakemKID",String.valueOf(myResultset.getString("kid")));

		        
	            //30dakika boyunca ?erezlerde verileri tut
	            loginCookie.setMaxAge(30*60);loginCookie1.setMaxAge(30*60);loginCookie2.setMaxAge(30*60);loginCookie3.setMaxAge(30*60);loginCookie4.setMaxAge(30*60);
	            response.addCookie(loginCookie);
	            response.addCookie(loginCookie1);
	            response.addCookie(loginCookie2);
	            response.addCookie(loginCookie3);
	            response.addCookie(loginCookie4);

	        
	            response.sendRedirect("HakemHome");				
			}
			else
			{
				HttpSession session = request.getSession();
				response.sendRedirect("HakemLogin");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
