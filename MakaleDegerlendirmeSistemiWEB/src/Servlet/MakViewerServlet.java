package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.makale;

/**
 * Servlet implementation class MakViewerServlet
 */
@WebServlet("/MakViewer")
public class MakViewerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakViewerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	           
	           makale mk = getmkFromDB(conn, id, AEID);
	 
	           if (mk == null) {
	               // No record found.
	               response.getWriter().write("No data found");
	               return;
	           }
	 
	          
	           String fileName = mk.getFileName();
	 
	           String contentType = this.getServletContext().getMimeType(fileName);
	 
	           response.setHeader("Content-Type", contentType);
	 
	           response.setHeader("Content-Length", String.valueOf(mk.getFileData().length()));
	 
	           response.setHeader("Content-Disposition", "inline; filename=\"" + mk.getFileName() + "\"");
	 
	           // For big BLOB data.
	           Blob fileData = mk.getFileData();
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
	
	 private makale getmkFromDB(Connection conn, int id, int AEID) throws SQLException {
		   

	       String sql = "SELECT * FROM makale WHERE ID = ? AND AeditorID = ?";
	  
	       PreparedStatement pstm = conn.prepareStatement(sql);
	       pstm.setInt(1, id);
	       pstm.setInt(2, AEID);
	       ResultSet rs = pstm.executeQuery();
	       if (rs.next()) {
	           String fileName = rs.getString("fileName");
	           Blob fileData = rs.getBlob("fileData");
	           String description = rs.getString("YazarBilgi");
	           
	           return new makale(-1, fileName, fileData, description, null, null, null, null, null);
	           
	           
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
