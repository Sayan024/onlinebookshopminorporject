

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
 * Servlet implementation class InsertBook
 */
@WebServlet("/InsertBook")
public class InsertBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBook() {
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
		String bn=request.getParameter("t1");
		String bi=request.getParameter("t2");
		String pr=request.getParameter("t3");
		String li=request.getParameter("t4");
		String ci=request.getParameter("t5");
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
				PreparedStatement ps=con.prepareStatement("INSERT INTO BOOK VALUES (?,?,?,?,?)");
				ps.setString(1, bn);
				ps.setString(2, bi);
				ps.setString(3, pr);
				ps.setString(4, li);
				ps.setString(5, ci);
				int x=ps.executeUpdate();
				if(x>0) {
					response.sendRedirect("onlinebookmanagement");
				}
			}
		}catch(Exception e) {out.println(e);}
	}

}
