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
 * Servlet implementation class ManageDepartment
 */
@WebServlet("/ManageDepartment")
public class ManageDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departName= request.getParameter("dName");
		Integer score=null;
		int successcount=0,totalCount=0;
		PrintWriter out = response.getWriter();
		String sql="select score from studentdetails where department= ?";
		
		try {
			java.sql.Connection connect= DBUtil.getDataSource().getConnection();
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, departName);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				
				totalCount=totalCount+1;
				score=Integer.parseInt(rs.getString("score"));
				if(score>40)
				{
					successcount =successcount+1;
				}				
				
			}
			
			out.println("<h3>Total Number of Student in&nbsp;:-" +departName+ "</h3>" + 
					"<p>"+totalCount+"</p>");
			double averagePass=(100*successcount)/totalCount;
			double averageFail=100-averagePass;
			out.println("<p><b>Pass percentage</p></b>  " + String.format("%.2f", averagePass));
			out.println("<p><b>Fail percentage</p></b> "+String.format("%.2f", averageFail));
			allStudentResult(out, request, response,departName);
		
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	public void allStudentResult(PrintWriter out,HttpServletRequest request,HttpServletResponse response,String department) throws IOException, ServletException
	{
		
		
		String name=null,id=null,score=null;
		String departMent=department;
		PrintWriter out1 = response.getWriter();
		String sql="select * from studentdetails where department= ?";
	
		
		try {
			java.sql.Connection connect= DBUtil.getDataSource().getConnection();
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, departMent);
			ResultSet rs1=statement.executeQuery();
			
			out1.print("<div align='center'>");
			out1.print("<table border='1'>");
			out1.print("<tr><td><b>Student ID</b></td>"+"<td><b>Student Name</b></td>"+"<td><b>Department</b></td>"+"<td><b>Score</b></td></tr>");
			while(rs1.next())
			{
				id=rs1.getString("studentId");
				name=rs1.getString("name");
				department=rs1.getString("department");
				score=rs1.getString("score");
				out1.println("<tr><td>" +id + "</td>"+"<td>" +name + "</td>"
				+"<td>" +department + "</td>"+"<td>" +score + "</td></tr>");
				
				
			}
			out1.println("</table></div>");
			
		
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
