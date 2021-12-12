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
import Model.Hakem;
import Model.makale;
import Model.makalehakem;
import Model.yazarlar;

/**
 * Servlet implementation class Aeditor_MakDurumServlet
 */
@WebServlet("/AlanEditor_MakaleDurum")
public class AlanEditor_MakaleDurum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public AlanEditor_MakaleDurum() {
        super();
        // TODO Auto-generated constructor stub
    }


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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/AlanEditor_Login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			int ctrl = Integer.parseInt(request.getParameter("id"));
			List<makale> maklist = null;
			
			if(ctrl == -1)//ÝÞLEME DEVAM EDÝLEN MAKALELER
			{
			try {
				maklist = getMakPuan(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else if(ctrl == 0) //ONAYLANAN MAKAELELERÝ GETÝR
			{
				
				try {
					maklist = getOnayliMak(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			request.setAttribute("ctrl", ctrl);
			request.setAttribute("maklist", maklist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/MakPuanViewer.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
	}

	public static List<makale> getMakPuan(int AEID) throws SQLException
    {

        List<makale> maklist = new ArrayList<makale>();

        ResultSet rs = ConnectionUtils.WHERE("Select *,makid,count(makid)=3 as TRow from makale inner join yazarlar on yazarlar.id = makale.YazID "
                + "inner join makalehakem on makalehakem.makid = makale.ID Where AeditorID = '"+AEID+"'  AND onay = 0 AND raporData IS NOT NULL GROUP BY (makid)");
        while(rs.next())
        {

        yazarlar yazar = new yazarlar(rs.getInt("yazarlar.id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"), null, rs.getString("workplace"));

        maklist.add(new makale(rs.getInt("makale.ID"),yazar, 
                rs.getString("fileName"), rs.getBlob("fileData"),
                rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), 
                rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
                rs.getDate("AEditorDate"), rs.getDate("HakemDate"),
                rs.getInt("Puan"),rs.getInt("revizyon")));

        }



        return maklist;
    }
	public static List<makale> getOnayliMak(int AEID) throws SQLException
    {

        List<makale> maklist = new ArrayList<makale>();

        ResultSet rs = ConnectionUtils.WHERE("Select * from makale inner join yazarlar on yazarlar.id = makale.YazID Where AeditorID = '"+AEID+"' AND Onay = 1");
        while(rs.next())
        {

        yazarlar yazar = new yazarlar(rs.getInt("yazarlar.id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"), null, rs.getString("workplace"));

        maklist.add(new makale(rs.getInt("makale.ID"),yazar, 
                rs.getString("fileName"), rs.getBlob("fileData"),
                rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), 
                rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
                rs.getDate("AEditorDate"), rs.getDate("HakemDate"),
                rs.getInt("Puan"),rs.getInt("revizyon")));

        }



        return maklist;
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/AlanEditor_Login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			int makid = Integer.parseInt(request.getParameter("id"));
			
			List<makalehakem> raplist = null;
			try {
				raplist = getMakRapor(makid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					
			}
			request.setAttribute("raplist", raplist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/MakPuanViewerHakRapor.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	public static List<makalehakem> getMakRapor(int makid) throws SQLException
    {

        List<makalehakem> raplist = new ArrayList<makalehakem>();

        ResultSet rs = ConnectionUtils.WHERE("select *  from makalehakem inner join hakem on hakem.id = makalehakem.hakid Where makid = '"+makid+"'");
        while(rs.next())
        {

            Hakem hakem =new Hakem(rs.getString("name"), rs.getString("lastname"), rs.getString("email"), null, null);

            raplist.add(new makalehakem(rs.getInt("makalehakem.id"), hakem, rs.getInt("durum"), rs.getDate("date"), rs.getDate("kabulDate"), 
                    rs.getDate("raporDate"), rs.getBlob("raporData"), rs.getString("raporName")));

        }
        return raplist;
    }
}
