package Servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import Model.makale;
import Model.yazarlar;


/**
 * Servlet implementation class MakViewerYazServlet
 */
@WebServlet("/MakViewerAll")
public class MakViewerYazServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Hakem> SecilenHakemlist;List<Hakem> Hakemlist;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakViewerYazServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = -1;
		int kid = -1;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("AlanEditorID"))
				{id = Integer.parseInt(cookie.getValue());}
			else if(cookie.getName().equals("AlanEditorKID"))
			{kid = Integer.parseInt(cookie.getValue());}
			
		}
		}
		
		if(id == -1)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/AlanEditorLogin.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
		Date d = new Date();
		int makID = Integer.parseInt(request.getParameter("id"));
		List<Hakem> haklist=null;
		List<Hakem> sechaklist=null;

		makale mak = null;
		try {
			ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID WHERE AeditorID ='"+id+"' AND makale.ID ='"+makID+"'");
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
	        
	        
	        
	        
	        
	        
	        
	        
	        Hakemlist = new ArrayList<Hakem>();
	        
	        Connection cn = ConnectionUtils.getMyConnection();
	        
	        PreparedStatement prR = cn.prepareStatement("SELECT * FROM hakem inner join kategori on hakem.kid = kategori.id WHERE kid = '"+kid+"'");
	        ResultSet myrs = prR.executeQuery();
	        
	        while(myrs.next())
	        {
	            Kategori k = new Kategori(myrs.getInt("kategori.id"),myrs.getString("kategori.name"));
	            
	            Hakemlist.add(new Hakem(myrs.getInt("hakem.id"), myrs.getString("hakem.name"), myrs.getString("lastname"), myrs.getString("email"), myrs.getString("password"), k));
	        }
	        
	        haklist=Hakemlist;
	        
	        
	        
	        
	        
	        
	        
	        SecilenHakemlist = new ArrayList<Hakem>();
	        
	        Connection con = ConnectionUtils.getMyConnection();
	        PreparedStatement pRr = con.prepareStatement("select * from makalehakem inner join hakem on hakem.id = makalehakem.hakid Where makid = ?");
	        pRr.setInt(1, makID);
	    ResultSet srs =    pRr.executeQuery();
	        
	    while(srs.next())
	    {
	        Kategori kategori = new Kategori(srs.getInt("kid"), null);
	        
	        SecilenHakemlist.add(new Hakem(srs.getInt("hakid"), srs.getString("name"), srs.getString("lastname"), srs.getString("email"), null, kategori));
	    }			 
		
	    
	    
	    	    
		   Connection connection = ConnectionUtils.getMyConnection();
           CallableStatement cpr;

           cpr = connection.prepareCall("{CALL makDateUpdate(?, ?)}");

           cpr.setInt(1, makID);
           cpr.setInt(2, 2); //alan editörü oldugu için 2.seçeneði yapcak
           cpr.executeUpdate();
	    
	    
	    
	    
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
		 sechaklist=SecilenHakemlist;
		
		request.setAttribute("mak", mak);
		request.setAttribute("GHid",-1);
		request.setAttribute("HakemList", haklist);
		request.setAttribute("SecHakemList", sechaklist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/MakViewer.jsp");
		dispatcher.forward(request, response);
		}
		
		
	}

	
	

}
