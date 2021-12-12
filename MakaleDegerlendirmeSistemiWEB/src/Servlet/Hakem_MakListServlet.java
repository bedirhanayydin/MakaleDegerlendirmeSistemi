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
import Model.makalehakem;

/**
 * Servlet implementation class Hakem_MakListServlet
 */
@WebServlet("/Hakem_MakList")
public class Hakem_MakListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hakem_MakListServlet() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/Hakem_Login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			String ctrl = request.getParameter("ctrl");
			List<makalehakem> maktohaks = null;
			
				try {
					if(ctrl.equals("t"))   //ÝNPUTTA KÝ ÝNPUTTAKÝ CONTROL DEÐERÝ T ÝSE TÜM MAKALELERÝ GETÝR
					maktohaks = getAllMak(id, true);
					else if(ctrl.equals("i"))
						maktohaks = getAllMak(id, false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("maktohaks", maktohaks);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/Hakem_MakList.jsp");
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
	public static List<makalehakem> getAllMak(int hid, Boolean ctrl) throws SQLException
    {
        List<makalehakem> maktohaks = new ArrayList<makalehakem>();
        
        ResultSet rs = null;
        
        if(ctrl)  //T ÝSE TÜM MAKALELERÝ GETÝR
         rs = ConnectionUtils.WHERE("Select * from makalehakem inner join hakem on hakem.id = makalehakem.hakid "
                + "inner join makale on makale.ID = makalehakem.makid WHERE hakid = '"+hid+"' AND durum = 1");
        else    // i ise ÝÞLENEN MAKALELERÝ GETÝR
             rs = ConnectionUtils.WHERE("Select * from makalehakem inner join hakem on hakem.id = makalehakem.hakid "
                        + "inner join makale on makale.ID = makalehakem.makid WHERE hakid = '"+hid+"' AND durum = 1 AND raporData IS NULL");
        
        while(rs.next())
        {
            
            makale mak =new makale(rs.getInt("makale.ID"), rs.getInt("YazID"), rs.getString("fileName"), rs.getBlob("fileData"), 
                    rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
                    rs.getDate("AEditorDate"), rs.getDate("HakemDate"), rs.getInt("revizyon"), rs.getInt("onay"));
                    
                    
            maktohaks.add(new makalehakem(rs.getInt("makalehakem.id"),
                    mak,
                    rs.getInt("durum"),
                    rs.getDate("date"),
                    rs.getDate("kabulDate"),
                    rs.getDate("raporDate"),
                    rs.getBlob("raporData"),
                    rs.getString("raporName")));
            
        
        }
        
        return maktohaks;
    }
}
