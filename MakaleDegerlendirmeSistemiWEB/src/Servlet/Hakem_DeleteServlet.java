package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;

/**
 * Servlet implementation class Hakem_DeleteServlet
 */
@WebServlet("/HakemDelete")
public class Hakem_DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));

	        Connection cn =ConnectionUtils.getMyConnection();
	        PreparedStatement preparedStatement;
	        try {
	        	preparedStatement = cn.prepareCall("DELETE FROM hakem WHERE id = ?");
	        	preparedStatement.setInt(1, id);

	        	preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		response.sendRedirect("HakemList");
	}
}
