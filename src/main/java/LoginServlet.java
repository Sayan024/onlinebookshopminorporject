

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String uid=request.getParameter("t1");
		String pass=request.getParameter("t2");
		RequestDispatcher rd=null;
		HttpSession hs=request.getSession(true);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","123456");
			if(con==null)
			{
				out.println("<h2>Connection not created...</h2>");
			}
			else
			{
				PreparedStatement ps=con.prepareStatement("SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?");
				ps.setString(1, uid);
				ps.setString(2, pass);
				ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			hs.setAttribute("name",uid);
			request.setAttribute("password", pass);
			response.sendRedirect("options.html");
			
		}
		else
		{
			rd=request.getRequestDispatcher("AdminPanel.html");
			response.sendRedirect("AdminPanel.html");
		}
		rd.forward(request, response);
		
		
		}
		}
		catch(Exception e) {out.println(e);}
	}

}
