package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Model.makale;
import Model.yazarlar;

/**
 * Servlet implementation class Hakem_MakViewServlet
 */
@WebServlet("/Hakem_MakView")
public class Hakem_MakViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_MakViewServlet() {
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
		
		if(id == -1)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/HakemLogin.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			
		int makID = Integer.parseInt(request.getParameter("id"));
	System.out.println(makID);
		makale mak = null;
		List<Hakem> HakemList = null;
		List<Hakem> SecHakemList = null;
		try {
			 mak = WhereMyMak(id, makID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
		request.setAttribute("mak", mak);
		request.setAttribute("GHid",id);
		request.setAttribute("HakemList", HakemList);
		request.setAttribute("SecHakemList", SecHakemList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/MakViewer.jsp");
		dispatcher.forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static makale WhereMyMak(int Hid, int id) throws SQLException
    {
        
		makale mak = null;
        
        ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID inner join makalehakem on makale.ID = makalehakem.makid Where hakid ='"+Hid+"' AND makale.ID = '"+id+"'");
        while(rs.next())
        {
            
        yazarlar yazar = new yazarlar(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"), null, rs.getString("workplace"));
        
        mak = new makale(rs.getInt("ID"),yazar, 
                rs.getString("fileName"), rs.getBlob("fileData"),
                rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), 
                rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
                rs.getDate("AEditorDate"), rs.getDate("HakemDate"),
                rs.getInt("Puan"),rs.getInt("revizyon"));
        
        }
        return mak;
    }
    
}
