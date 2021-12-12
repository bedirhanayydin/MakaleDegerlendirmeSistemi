package Servlet;


import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.ConnectionUtils;
import Model.Mail;
import Model.makale;
import Model.yazarlar;


@WebServlet("/uploadToDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadToDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private makale mk;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/view/YazarHome.jsp");
 
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
 
            String description = request.getParameter("YazarBilgi");
            String ek = request.getParameter("MakaleBilgi");
            int makid =Integer.parseInt(request.getParameter("id"));
  
            response.setContentType("text/html");

        	  yazarlar yazar = new yazarlar();
        	  yazar.setID(-1);
        	Cookie[] cookies = request.getCookies();
        	if(cookies !=null){
        	for(Cookie cookie : cookies){
        		switch(cookie.getName())
        		{
        		case "YazarNAME":
        			yazar.setName(cookie.getValue());
        			break;
        		case "YazarLASTNAME":
        			yazar.setLastname(cookie.getValue());
        			break;
        		case "YazarEMAIL":
        			yazar.setEmail(cookie.getValue());
        			break;
        		case "YazarID":
        			yazar.setID(Integer.parseInt(cookie.getValue()));
        			break;
        		}
        		
        	}
        	}
            // Part list (multi files).
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    // File data
                    InputStream is = part.getInputStream();
                    //dosya boyutu kontrol
                    
                    // Write to file
                    
                    if(makid == -1)
                    {	System.out.println(yazar.getID());
                    	mk = new makale(yazar.getID(), fileName, null, description, ek, null, null, null, null);
                    this.writeToDB(conn, is, mk);
                    }
                    else
                    {
                    	this.updateToDB(conn, is, makid,fileName );
                    }
                   
                    
                }
            }
            conn.commit();

           // SendMailAllEd(yazar); //tum bas editorlere mail gönder...
            //SendMailYazar(yazar); //makaleyi yukliyen yazara gonder...
            
            // Upload successfully!.
            response.sendRedirect(request.getContextPath() + "/YazarHome");
          
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/YazarHome.jsp");
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
    private void SendMailYazar(yazarlar yazar) throws SQLException{
    	
    	
    	String Email = null;
		try(Connection connection = ConnectionUtils.getMyConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM baseditor;");){
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
			
				Email = rs.getString("email");
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		Mail mail = new Mail("Makaleniz Sisteme Yüklendi",
				"Hesabýnýzdan yapmýþ olduðunuz makale gönderme islemi basarili!!<"+Email+">");
					try {
						mail.sendMail("bedirhhanaydin@gmail.com");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
}
    private void SendMailAllEd(yazarlar yazar) throws SQLException{
    	
    	
    		try(Connection connection = ConnectionUtils.getMyConnection(); 
    				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM baseditor;");){
    			
    			
    			ResultSet rs = preparedStatement.executeQuery();
    			
    			while(rs.next())
    			{
    				Mail mail = new Mail("Sisteme Yeni Makale Yüklendi",yazar.getWorkplace()+" adlý kurumda çalýþmakta olan "
    			+yazar.getName()+" "+yazar.getLastname()+" az önce bir makale gönderdi...  <"+yazar.getEmail()+">");
    				try {
						mail.sendMail("bedirhhanaydin@gmail.com");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}catch (SQLException e) {
    			e.printStackTrace();
    		}
    			
    }
 
    private void writeToDB(Connection conn, InputStream is, makale mk) throws SQLException {
 
        Date dd = new Date();
        java.sql.Date sqlDate = new java.sql.Date(dd.getTime());
        CallableStatement pstm = conn.prepareCall("{CALL makInsert(?,?,?,?,?,?,?,?,?,?,?)}");
        
        pstm.setInt(1, mk.getYazID());
        pstm.setString(2, mk.getFileName());
        pstm.setBlob(3, is);
        pstm.setString(4, mk.getYazarBilgi());
        pstm.setString(5, mk.getMakaleBilgi());
        pstm.setDate(6, sqlDate);
        pstm.setDate(7, mk.getEditorDate());
        pstm.setDate(8, mk.getaEditorDate());
        pstm.setDate(9, mk.getHakemDate());
        pstm.setInt(10, -1);
        pstm.setInt(11, 0);
        pstm.executeUpdate();
    }
    
    private void updateToDB(Connection conn, InputStream is, int makid, String fileName) throws SQLException
    {
    	CallableStatement cb = conn.prepareCall("{CALL makRevizyon(?, ?, ?)}");
    	cb.setBlob(1, is);
    	cb.setInt(2, makid);
    	cb.setString(3, fileName);
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