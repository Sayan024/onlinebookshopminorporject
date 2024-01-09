

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class bookselect
 */
@WebServlet("/bookselect")
public class bookselect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookselect() {
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
		HttpSession hs=request.getSession(true);
		String uname=(String)hs.getAttribute("name");
		String pass=(String)request.getAttribute("password");
		String bi=request.getParameter("f1");
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
				PreparedStatement ps=con.prepareStatement("insert into userbook values(?,?)");
				ps.setString(1, uname);
				ps.setString(2, bi);
				
				int x=ps.executeUpdate();
				if(x>0) {
					out.println("done");
					response.sendRedirect("/userbook");
					
				}
			}
		}catch(Exception e) {out.println(e);}
	}

}
