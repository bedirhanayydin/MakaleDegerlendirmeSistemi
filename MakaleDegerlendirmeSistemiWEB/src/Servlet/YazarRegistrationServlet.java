package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ConnectionUtils;

/**
 * Servlet implementation class YazarRegistrationServlet
 */
@WebServlet("/YazarRegistration")
public class YazarRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YazarRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/YazarRegistration.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adString=request.getParameter("Name");
		String soyadString=request.getParameter("Lastname");
		String emailString=request.getParameter("Email");
		String passwordString=request.getParameter("Password");
		String iþyeriString=request.getParameter("Workplace");
		
		Connection con=ConnectionUtils.getMyConnection();
		try {
			PreparedStatement preparedStatement=con.prepareStatement("Insert into yazarlar(name,lastname,email,password,workplace) Values(?,?,?,?,?)");
			preparedStatement.setString(1, adString);
			preparedStatement.setString(2, soyadString);
			preparedStatement.setString(3, emailString);
			preparedStatement.setString(4, passwordString);
			preparedStatement.setString(5, iþyeriString);
			preparedStatement.executeUpdate();
			response.sendRedirect("YazarLogin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
