package studentresult.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;


import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


import studentresult.database.DBUtil;

/**
 * Servlet implementation class ManageStudent
 */
@WebServlet("/ManageStudent")
public class ManageStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sId=request.getParameter("studentID");
		String sName=request.getParameter("studentName");
		PrintWriter out = response.getWriter();
		
		String name = null,Id = null,score = null,department = null;
		
		String sql="select * from studentdetails where name= ? and studentid= ?";
		
		try {
			java.sql.Connection connect= DBUtil.getDataSource().getConnection();
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, sName);
			statement.setString(2, sId);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				name=rs.getString("name");
				Id=rs.getString("studentid");
				department=rs.getString("department");
				score=rs.getString("score");
					
				
			}
			out.print("</br>");
			out.print("</br>");
			out.println("<div align=\"center\"> ");
			out.println("<html>"  +
					  " <body>"+ "<table  border='1'>"+ "<tr><td>"+ "Student ID : </td> " 
					  		+ "<td>"+Id+"</td>"
					  				+ "</tr>"
					  					  + "<tr><td> Student Name :</td> <td>"+name+"</td></tr>"
					  					  + "<tr><td> Department :  </td> <td>"+department+"</td></tr>"
					  					  + "<tr><td>Score: </td><td>"+score+'%'+"</td></tr>"
					  +"</table>"
					  + "</body>"
					  + "</html>");
			out.println("</div>");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		
		
		
	


}
