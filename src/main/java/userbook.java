


import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


/**
 * Servlet implementation class userbook
 */
@WebServlet("/userbook")
public class userbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userbook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request.getSession(true);
		String uname=(String)hs.getAttribute("name");
		String pass=(String)request.getAttribute("password");
		
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
				Statement stmt = con.createStatement();
				
				String sql = "SELECT book_name, userbook.book_isbn, pages,view_book,coverimg FROM book,userbook where book.book_isbn=userbook.book_isbn and username='"+uname+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				out.println("<html><head><link rel=\"stylesheet\" href=\"bookhome.css\"></head><body><h1>"+uname+"Dashboard</h1> <header>\r\n"
						+ "    <nav>\r\n"
						+ "      <ul>\r\n"
						+ "        <li><a href=\"Homepage.html\">Home</a></li>\r\n"
						+ "        <li><a href=\"onlinebookmanagement\">Bookshop</a></li>\r\n"
						+ "        <li><a href=\"About.html\">About</a></li>\r\n"
						+"<li><span> <img src=\"userprofile.png\" id=\"uimg\">:"+uname+"</span></li>\r\n"
						+ "      </ul>\r\n"
						+"</span></a>"
						+ "    </nav>\r\n"
						+ "  </header>");
				out.println("<table border='1'>");
				out.println("<tr>");
				ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            for (int i = 1; i <= columnCount; i++) {
	                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
	            }
	            out.println("</tr>");

	            while (rs.next()) {
	                out.println("<tr>");
	                for (int i = 1; i <=3; i++) {
	                    out.println("<td><b>" + rs.getString(i) + "</b></td>");
	                }
	                for (int i = 4; i <= 4; i++) {
	                	String s=rs.getString(i);
	                	out.println("<td> <a href=\'"+ s +"\'>"+s+ "</a>"+"</td>");
                }
	                for (int i = 5; i <= rs.getMetaData().getColumnCount(); i++) {
	                	
	                    out.println("<td> <img  src=\'"+ rs.getString(i) +"\' class=\"cimg\" >"+"</td>");
	                }
	                out.println("</tr>");
	            }

				out.println("</table>");
				out.println("</body></html>");
				rs.close();
				stmt.close();
				con.close();
			}
			
		}catch(Exception e) {out.println(e);}
	}

}
