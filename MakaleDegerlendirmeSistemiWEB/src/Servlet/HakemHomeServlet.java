package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.makale;
import Model.yazarlar;

/**
 * Servlet implementation class HakemHomeServlet
 */
@WebServlet("/HakemHome")
public class HakemHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HakemHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = -1;
	        Cookie[] cookies = request.getCookies();
	        if(cookies !=null){
	        for(Cookie cookie : cookies){
	            if(cookie.getName().equals("HakemID"))
	                {id = Integer.parseInt(cookie.getValue()); break;}
	        }
	        }
	        
	    
	    List<makale> makaleler = null;
	        
	        try {
	        	makaleler =WhereMyAllMak(id);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        request.setAttribute("makaleler", makaleler);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/HakemHome.jsp");
	        dispatcher.forward(request, response);
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static List<makale> WhereMyAllMak(int id) throws SQLException
    {
        
        List<makale> maklist = new ArrayList<makale>();
        
        ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID inner join makalehakem on makale.ID = makalehakem.makid Where hakid ='"+id+"' AND durum = 0");
        while(rs.next())
        {
            
        yazarlar yazar = new yazarlar(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"), null, rs.getString("workplace"));
        
        maklist.add(new makale(rs.getInt("ID"),yazar, 
                rs.getString("fileName"), rs.getBlob("fileData"),
                rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), 
                rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
                rs.getDate("AEditorDate"), rs.getDate("HakemDate"),
                rs.getInt("Puan"),rs.getInt("revizyon")));
        
        }
        return maklist;
    }
}
