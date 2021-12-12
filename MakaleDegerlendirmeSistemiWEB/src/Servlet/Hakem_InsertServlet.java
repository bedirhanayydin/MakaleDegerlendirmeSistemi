package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.Hakem;
import Model.Kategori;


/**
 * Servlet implementation class Hakem_InsertServlet
 */
@WebServlet("/HakemInsert")
public class Hakem_InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Hakem hak = new Hakem(-1,
    			request.getParameter("name"), 
    			request.getParameter("lastname"), 
    			request.getParameter("email"), 
    			request.getParameter("password"),
    			new Kategori(Integer.parseInt(request.getParameter("Kategori")), ""));
    	try {
			HakemInsert(hak);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	response.sendRedirect("HakemList");
	}
	public static String HakemInsert(Hakem hakem) throws SQLException {
        
	    if(!VarMi(hakem.getEmail()))
	    {
	    Connection cn =ConnectionUtils.getMyConnection();
	    
	    PreparedStatement pr = cn.prepareStatement("INSERT INTO hakem (name, lastname, email, password, kid) VALUES (?, ?, ?, ?,?)");
	    pr.setString(1, hakem.getName());
	    pr.setString(2, hakem.getLastname());
	    pr.setString(3, hakem.getEmail());
	    pr.setString(4, hakem.getPassword());
	    pr.setInt(5, hakem.getKategori().getId());
	    pr.executeUpdate();
	        return "islem basarili...";
	    }
	    else return "islem basarisiz, girilen email zaten kayitli";
	    }
	static Boolean VarMi(String email) throws SQLException {
        boolean temp=false;
        Connection cn = ConnectionUtils.getMyConnection();
        PreparedStatement pr = cn.prepareStatement("SELECT * FROM hakem WHERE email = ?");
        pr.setString(1, email);
        ResultSet rs = pr.executeQuery();
        temp = rs.next();
        
        return temp;
    }

}
