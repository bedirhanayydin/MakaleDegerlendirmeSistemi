package Servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.Hakem;
import Model.Kategori;

/**
 * Servlet implementation class Hakem_UpdateServlet
 */
@WebServlet("/HakemUp")
public class Hakem_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	Hakem hak = new Hakem(Integer.parseInt(request.getParameter("id")),
    			request.getParameter("name"), 
    			request.getParameter("lastname"), 
    			request.getParameter("email"), 
    			request.getParameter("password"),
    			new Kategori(Integer.parseInt(request.getParameter("Kategori")), ""));
    	

    	try {
			HakemUpdate(hak);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	response.sendRedirect("HakemList");
    		
    	}

	
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Hakem hak = null;
		List<Kategori> katlist = null;
		try {
			 hak =SelectHakem(id);
			 
			  katlist =WhereAllKategori();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/HakemUpdate.jsp");
		request.setAttribute("hak", hak);
		request.setAttribute("katlist", katlist);
		dispatcher.forward(request, response);
		
	}
		public static String HakemUpdate(Hakem hakem) throws SQLException{
    
        
        ResultSet rs =ConnectionUtils.WHERE("SELECT * FROM hakem WHERE email = '"+ hakem.getEmail()+"' AND id != '"+hakem.getId()+"'");
        
        
        if(!rs.next())
        {
        Connection cn =ConnectionUtils.getMyConnection();
        
        PreparedStatement ps = cn.prepareStatement("UPDATE hakem SET name = ?, lastname = ?, email = ?, password = ?, kid =? WHERE id = ?");
        
        ps.setString(1, hakem.getName());
        ps.setString(2, hakem.getLastname());
        ps.setString(3, hakem.getEmail());
        ps.setString(4, hakem.getPassword());
        ps.setInt(5, hakem.getKategori().getId());
		ps.setInt(6, hakem.getId());
        
        ps.executeUpdate();
            return "islem basarili...";
        
        }
        else return "islem basarisiz, girilen email zaten kayitli";
        
        
    }
		
		public static Hakem SelectHakem(int id) throws SQLException
	    {
	        Hakem hak = null;
	        
	    ResultSet rs =    ConnectionUtils.WHERE("SELECT * FROM hakem inner join kategori on hakem.kid = kategori.id WHERE hakem.id = '"+id+"'");
	        
	    while(rs.next())
	    {
	        Kategori k = new Kategori(rs.getInt("kategori.id"),rs.getString("kategori.name"));
	        hak = new Hakem(rs.getInt("hakem.id"), rs.getString("hakem.name"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), k);
	    }
	    return hak;
	}
		
		
		public static List<Kategori> WhereAllKategori() throws SQLException
	    {
	        
	        List<Kategori> katlist = new ArrayList<Kategori>();
	        
	        ResultSet rs = ConnectionUtils.WHERE("Select * from kategori");
	        
	        while(rs.next())
	        {
	            katlist.add(new Kategori(rs.getInt("id"), rs.getString("name")));
	        }
	        
	        return katlist;
	    }

}
