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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionUtils;
import Model.Hakem;
import Model.Kategori;
import Model.Mail;
import Model.makale;
import Model.makalehakem;

/**
 * Servlet implementation class MakHakemSecServlet
 */
@WebServlet("/MakHakemSec")
public class MakHakemSecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakHakemSecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Hakem hakem = new Hakem();
		hakem.setId(Integer.parseInt(request.getParameter("hakid1")));
		
		Hakem hakem1 = new Hakem();
		hakem1.setId(Integer.parseInt(request.getParameter("hakid2")));
		
		Hakem hakem2 = new Hakem();
		hakem2.setId(Integer.parseInt(request.getParameter("hakid3")));
		
		makale mak = new makale();
		mak.setId(Integer.parseInt(request.getParameter("id")));
		
		makalehakem maktohak = new makalehakem(hakem, hakem1, hakem2, mak);
		
		try {
			SetmakToHak(maktohak);
			Date n = new Date();
			makDateUpdate(mak.getId(), n, 3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("AlanEditorHome");
		
	}
	public static void SetmakToHak(makalehakem maktohak ) throws SQLException
    {
    Connection con = ConnectionUtils.getMyConnection();
    CallableStatement cpr = con.prepareCall("{CALL maktohakInsert(?, ?, ?, ?)}");
    cpr.setInt(1, maktohak.getMak().getId());
    cpr.setInt(2, maktohak.getHak().getId());
    cpr.setInt(3, maktohak.getHak1().getId());
    cpr.setInt(4, maktohak.getHak2().getId());
    cpr.executeUpdate();
    
    //SendMAIL(maktohak);
    }
    
    public static void SendMAIL(makalehakem maktohak) throws SQLException
    {

        
        List<Hakem> temp = new ArrayList<Hakem>();
        temp.add(SelectHakem(maktohak.getHak().getId()));
        temp.add(SelectHakem(maktohak.getHak1().getId()));
        temp.add(SelectHakem(maktohak.getHak2().getId()));
        
        for(Hakem hakem : temp)
        {
            
            Mail mail = new Mail("Yeni Makale Inceleme Daveti", "Sn. "+hakem.getName()+" "+hakem.getLastname()+" yeni makele devetiniz bulunmaktadýr, sisteme "
                    + "giris yapýp daveti onaylayabilir ya da onaylamayabilirsiniz, NOT: Karar verme süreniz 10 gün !!");
                try {
                    //mail.sendMail("bedirhhanaydin@gmail.com");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }   
        }
    }
    	public static void makDateUpdate(int id, Date d, int sec) throws SQLException {
        
        Connection cn = ConnectionUtils.getMyConnection();
                
         	CallableStatement cpr;
  
            cpr = cn.prepareCall("{CALL makDateUpdate(?, ?)}");
            
            cpr.setInt(1, id);
            cpr.setInt(2, sec);
            cpr.executeUpdate();
        
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
    

}
