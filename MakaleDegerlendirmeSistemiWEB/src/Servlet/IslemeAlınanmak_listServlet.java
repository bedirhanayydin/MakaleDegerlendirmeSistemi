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
import Model.makale;

/**
 * Servlet implementation class IslemeAlýnanmak_listServlet
 */
@WebServlet("/IþlemeAlýnanMakaleler")
public class IslemeAlýnanmak_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IslemeAlýnanmak_listServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 int YazID =-1;
			        Cookie[] cookies = request.getCookies();
			        if(cookies !=null){
			        for(Cookie cookie : cookies){
			            if(cookie.getName().equals("YazarID"))
			                {
			            	YazID = Integer.parseInt(cookie.getValue()); 
			            	
			                break;
			                }
			        	}
			        }
			        
			        if(YazID == -1)
			        {
			            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/YazarLogin.jsp");
			            dispatcher.forward(request, response);
			        }
			        else
			        {
			        List<makale> makaleler=new ArrayList<makale>();
			        try(Connection connection = ConnectionUtils.getMyConnection(); 
			                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM makale WHERE YazID =? AND onay=?");){
			            preparedStatement.setInt(1, YazID);
			            preparedStatement.setInt(2,0);
			            ResultSet rs = preparedStatement.executeQuery();
			            while(rs.next())
			            {
			            	makaleler.add(new makale(rs.getInt("ID"), rs.getInt("YazID"), rs.getString("fileName"), rs.getBlob("fileData"), 
			                        rs.getString("YazarBilgi"), rs.getString("MakaleBilgi"), rs.getDate("YuklenDate"), rs.getDate("EditorDate"), 
			                        rs.getDate("AEditorDate"), rs.getDate("HakemDate") , rs.getInt("revizyon"), rs.getInt("onay")));
			            }
			        }catch (SQLException e) {
			            e.printStackTrace();
			        }
			        request.setAttribute("makaleler", makaleler);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/mak-list.jsp");
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

}
