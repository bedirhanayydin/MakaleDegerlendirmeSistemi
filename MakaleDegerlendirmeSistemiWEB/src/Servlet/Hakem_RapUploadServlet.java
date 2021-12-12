package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.ConnectionUtils;


/**
 * Servlet implementation class Hakem_RapUploadServlet
 */
@WebServlet("/Hakem_RapUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Hakem_RapUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_RapUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/view/HakemHome.jsp");
		 
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		 Connection conn = null;
	        try {
	            conn = ConnectionUtils.getMyConnection();
	            conn.setAutoCommit(false);
	           int maktohakID =Integer.parseInt(request.getParameter("id"));
	            int Puan = Integer.parseInt(request.getParameter("puan"));
	            response.setContentType("text/html");

	           
	            // Part list (multi files).
	            for (Part part : request.getParts()) {
	                String fileName = extractFileName(part);
	                if (fileName != null && fileName.length() > 0) {
	                    // File data
	                    InputStream is = part.getInputStream();
	                    //dosya boyutu kontrol
	                    
	                    // Write to file
	                    
	                    
	                
	                    this.writeToDB(conn, is, fileName, maktohakID,Puan);
	                    
	                }
	            }
	            conn.commit();
	            response.sendRedirect(request.getContextPath() + "/HakemHome");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Error: " + e.getMessage());
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/HakemHome.jsp");
	            dispatcher.forward(request, response);
	        } finally {
	            this.closeQuietly(conn);
	        }
		
		
		
		
		
		
	}
	
	

	 private String extractFileName(Part part) {
	        
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                
	                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                clientFileName = clientFileName.replace("\\", "/");
	                int i = clientFileName.lastIndexOf('/');
	               
	                return clientFileName.substring(i + 1);
	            }
	        }
	        return null;
	    }
	
	
	  private void writeToDB(Connection conn, InputStream is, String filename, int id, int Puan) throws SQLException {

	        Connection cn = ConnectionUtils.getMyConnection();
	        
	        CallableStatement cb =cn.prepareCall("{CALL RaporUploadAndPuan(?, ?, ?, ?)}");
	        cb.setBlob(1, is);
	        cb.setString(2, filename);
	        cb.setInt(3, id);
	        cb.setInt(4, Puan);
	        cb.executeUpdate();
	        
	        
	        
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
