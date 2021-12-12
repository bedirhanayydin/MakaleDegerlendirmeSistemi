package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

import Model.ConnectionUtils;
import Model.yazarlar;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/YazarLogin")
public class YazarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YazarLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/YazarLogin.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailString=request.getParameter("Email");
		String passwordString=request.getParameter("Password");
		
		try {
			ResultSet myResultset= ConnectionUtils.WHERE("Select * from yazarlar Where email='"+emailString+"' and password='"+passwordString+"'");
			if (myResultset.next()) {
				
				Cookie loginCookie = new Cookie("YazarID",String.valueOf(myResultset.getInt("id")));
	            Cookie loginCookie1 = new Cookie("YazarNAME",String.valueOf(myResultset.getString("name")));
	            Cookie loginCookie2 = new Cookie("YazarLASTNAME",String.valueOf(myResultset.getString("lastname")));
	            Cookie loginCookie3 = new Cookie("YazarEMAIL",String.valueOf(myResultset.getString("email")));
	        
	            //30dakika boyunca çerezlerde verileri tut
	            loginCookie.setMaxAge(30*60);loginCookie1.setMaxAge(30*60);loginCookie2.setMaxAge(30*60);loginCookie3.setMaxAge(30*60);
	            response.addCookie(loginCookie);
	            response.addCookie(loginCookie1);
	            response.addCookie(loginCookie2);
	            response.addCookie(loginCookie3);
	        
	            response.sendRedirect("YazarHome");				
			}
			else
			{
				HttpSession session = request.getSession();
				
				response.sendRedirect("YazarLogin");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
