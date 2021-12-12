package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.makalehakem;

/**
 * Servlet implementation class Aeditor_MakRaporDownServlet
 */
@WebServlet("/AlanEditor_MakaleRaporIndir")
public class AlanEditor_MakaleRaporIndir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlanEditor_MakaleRaporIndir() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		

		 Connection conn = null;
	       try {
	    	   
	    	
	    	   int AEID = -1;
	    	   Cookie[] cookies = request.getCookies();
	    	   if(cookies !=null){
	    	   for(Cookie cookie : cookies){
	    		   if(cookie.getName().equals("AlanEditorID"))
	    		   { AEID = Integer.parseInt(cookie.getValue()); break;}
	    	   	
	    	   }
	    	   }
	    	   
	    	   if(AEID == -1)
	    		   response.sendRedirect("AlanEditorLogin");
	    	
	    	   
	           conn = ConnectionUtils.getMyConnection();
	           int id =  Integer.parseInt(request.getParameter("id"));
	           
	           makalehakem rp = getmkFromDB(conn, id, AEID);
	 
	           if (rp == null | rp.getRaporData() == null) {
	               // No record found.
	               response.getWriter().write("No data found");
	               return;
	           }
	 
	          
	           String fileName =  rp.getRaporName();
	 
	           String contentType = this.getServletContext().getMimeType(fileName);
	 
	           response.setHeader("Content-Type", contentType);
	 
	           response.setHeader("Content-Length", String.valueOf(rp.getRaporData().length()));
	 
	           response.setHeader("Content-Disposition", "inline; filename=\"" + rp.getRaporName() + "\"");
	 
	           // For big BLOB data.
	           Blob fileData = rp.getRaporData();
	           InputStream is = fileData.getBinaryStream();
	 
	           byte[] bytes = new byte[1024];
	           int bytesRead;
	 
	           while ((bytesRead = is.read(bytes)) != -1) {
	               // Write image data to Response.
	               response.getOutputStream().write(bytes, 0, bytesRead);
	           }
	           is.close();
	 
	       } catch (Exception e) {
	           throw new ServletException(e);
	       } finally {
	           this.closeQuietly(conn);
	       }
	}
	
	 private makalehakem getmkFromDB(Connection conn, int id, int AEID) throws SQLException {
		
	       String sql = "select * from makalehakem inner join makale on makale.ID = makalehakem.makid Where makalehakem.id = ? AND AeditorID = ?";
	  
	       PreparedStatement pstm = conn.prepareStatement(sql);
	       pstm.setInt(1, id);
	       pstm.setInt(2, AEID);
	   
	       ResultSet rs = pstm.executeQuery();
	       if (rs.next()) {
	           String fileName = rs.getString("raporName");
	           Blob fileData = rs.getBlob("raporData");
	    
	           return new makalehakem(fileData, fileName);
	           
	       }
	       return null;
	   }
	 
	   private void closeQuietly(Connection conn) {
	       try {
	           if (conn != null) {
	               conn.close();
	           }
	       } catch (Exception e) {
	       }
	   }	

}
