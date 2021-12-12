package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import Model.Hakem;
import Model.Kategori;


/**
 * Servlet implementation class HakemListServlet
 */
@WebServlet("/HakemList")
public class HakemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HakemListServlet() {
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
			if(cookie.getName().equals("AlanEditorID"))
				{id = Integer.parseInt(cookie.getValue()); break;}
		}
		}
		
		
		if(id == -1)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/AlanEditorLogin.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			List<Hakem> Haklist = null;
			try {
				Haklist =WhereAllHakem();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			request.setAttribute("Haklist", Haklist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/Hakem-List.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.doGet(request, response);
		
		
	}
	public static List<Hakem> WhereAllHakem() throws SQLException
	{
	    
	    List<Hakem> Haklist = new ArrayList<Hakem>();
	    
	    Connection cn = ConnectionUtils.getMyConnection();
	    
	    PreparedStatement pr = cn.prepareStatement("SELECT * FROM hakem inner join kategori on hakem.kid = kategori.id");
	    ResultSet rs = pr.executeQuery();
	    
	    while(rs.next())
	    {
	        Kategori k = new Kategori(rs.getInt("kategori.id"),rs.getString("kategori.name"));
	        
	        Haklist.add(new Hakem(rs.getInt("hakem.id"), rs.getString("hakem.name"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), k));
	    }
	    
	    return Haklist;
	    
	}
}

