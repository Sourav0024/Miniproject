

package com.insert;
import java.sql.*;
import java.util.Scanner;

public class Quiz {
	Connection connection=null;
	PreparedStatement pss=null;
	Statement ps2=null;
	private void  insertStudentData(String sname,String scity,String smobile){
		try {
			ConnectionTest connectionTest=new ConnectionTest();
		connection=connectionTest.getConnectionDetails();
		
		
		//to insert student records
		pss=connection.prepareStatement("insert into student(sname,scity,smobile)values(?,?,?)");
		pss.setString(1, sname);
		pss.setString(2, scity);
		pss.setString(3, smobile);
		int i=pss.executeUpdate();
		System.out.println("Record inserted");
		connection.close();
		pss.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	public static void main(String[] args) { 
	Scanner sc=new Scanner(System.in);
	int count=0; 

	
	System.out.println("Enter name:");
	String sname=sc.next();
	System.out.println("Enter city:");
	String scity=sc.next();
	System.out.println("Enter mobile number:");
	String smobile=sc.next();
	
	Quiz quiz=new Quiz();
	quiz.insertStudentData(sname, scity, smobile);

	//for retriving questions
try {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","root");
//select query
PreparedStatement ps=con.prepareStatement("select * from user");
ResultSet rs=ps.executeQuery();

while(rs.next()) {

System.out.print("Q "+rs.getInt(1));
System.out.println(" "+rs.getString(2));
System.out.println("a. "+rs.getString(3));
System.out.println("b. "+rs.getString(4));
System.out.println("c. "+rs.getString(5));
System.out.println("d. "+rs.getString(6));
String a1=sc.next();

	if(a1.equals(rs.getString(7))) {
		count++;	
	}
}
//for inserting the marks
System.out.println("total count is "+count);
int c=count;
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","root");

PreparedStatement p=con1.prepareStatement("insert into student(smarks)values(?)");
p.setInt(1,c);
p.execute();


con.close();
ps.close();
rs.close();
}
catch(Exception e) {
e.printStackTrace();
	}
}
}