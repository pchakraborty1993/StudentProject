package studentresult.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentresult.database.DBUtil;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid= request.getParameter("userid");
		String password= request.getParameter("pwd");
		int count=0,totalCount=0;
		String name=null,id=null,score=null,department=null;
		PrintWriter out = response.getWriter();
		String sql="select * from studentdetails";
		RequestDispatcher requestdis = request.getRequestDispatcher("/logout.html");
		
		requestdis.include(request, response);
		
		try {
			java.sql.Connection connect= DBUtil.getDataSource().getConnection();
			Statement statement = connect.createStatement();
			
			ResultSet rs=statement.executeQuery(sql);
			
			out.print("<div align='center'>");
			out.print("<table border='1'>");
			out.print("<tr><td><b>Student ID</b></td>"+"<td><b>Student Name</b></td>"+"<td><b>Department</b></td>"+"<td><b>Score</b></td></tr>");
			while(rs.next())
			{
				id=rs.getString("studentid");
				name=rs.getString("name");
				department=rs.getString("department");
				score=rs.getString("score");
				out.println("<tr><td>" +id + "</td>"+"<td>" +name + "</td>"
				+"<td>" +department + "</td>"+"<td>" +score + "</td></tr>");
				
				
			}
			out.println("</table></div>");
			
		
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
