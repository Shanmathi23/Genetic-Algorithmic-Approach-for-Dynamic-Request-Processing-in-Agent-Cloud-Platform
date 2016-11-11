import jade.core.Agent;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ServiceProviderAgent
{
	
public static void main(String[]args)throws Exception
{
	BufferedReader reader = new BufferedReader(new FileReader("C:\\output\\user_input.txt"));
	 int lines = 0;
	 while (reader.readLine() != null) {
	 	lines++;
	 }
	 reader.close();
	 
	 for(int v=0;v<(lines/5);v++) {
		 System.out.println("\n\n\n");
		 System.out.println("FOR USER: "+(v+1)+"!!!\n");
	Connection con=null;
	Statement stmt=null;
	Class.forName("com.mysql.jdbc.Driver");
    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/producer", "root", "");
    stmt=con.createStatement();
FileInputStream in=new FileInputStream("C:\\output\\consumer_agent_output"+(v+1)+".txt");
BufferedReader myReader = new BufferedReader(new InputStreamReader(in));
Scanner file = new Scanner(new File("C:\\output\\consumer_agent_output"+(v+1)+".txt"));

FileWriter outStream = new FileWriter("C:\\output\\service_provider_output"+(v+1)+".txt");
PrintWriter out = new PrintWriter(outStream); 

List<String> Type=new ArrayList<String>();
		
List<String> Producer_name=new ArrayList<String>();
		
List<Integer> Quantity=new ArrayList<Integer>();
		
List<String> Prod_location=new ArrayList<String>();
		
List<String> location=new ArrayList<String>();
		
List<Integer> no_of_customers=new ArrayList<Integer>();
BufferedReader reader1 = new BufferedReader(new FileReader("C:\\output\\consumer_agent_output"+(v+1)+".txt"));
int lines1 = 0;
while (reader1.readLine() != null) {
	lines1++;
}
reader.close();
int g = 0;
boolean flag = true;
while(file.hasNextLine() && flag){		
Type.add(myReader.readLine());
Producer_name.add(myReader.readLine());
Quantity.add(Integer.parseInt(myReader.readLine()));
location.add(myReader.readLine());
g++;
if (g==(lines1/4)) {
	flag = false;
}
}//end of reading from the consumer_agent_output.java
System.out.println("Type: "+Type);	
System.out.println("Producer: "+Producer_name);	
System.out.println("Quantity: "+Quantity);	
System.out.println("Location: "+location);	
//int count=Integer.parseInt(Type.get(Type.size()-1));
		
//String customer_location=Producer_name.get(Producer_name.size()-1);
		
	ResultSet a;
	a=stmt.executeQuery("SELECT DISTINCT Location FROM producer");
	int size= 0;
	if (a != null)   
	{  
	  a.beforeFirst();  
	  a.last();  
	  size = a.getRow();  
	}
	a.beforeFirst();
	
	for(int d=1;d<=size;d++) {
		while(a.next()) {
			Prod_location.add(a.getString(d));
		}
	}
	System.out.println("All Locations: "+Prod_location);
	
for(int i=0;i<Prod_location.size();i++) {
	System.out.println("\nLocation: "+Prod_location.get(i));
	out.println("");
	out.println(Prod_location.get(i));
	for(int j=0;j<location.size();j++) {
		if(Prod_location.get(i).equals(location.get(j))) {
			System.out.println("Producer: "+Producer_name.get(j)+" - "+Type.get(j));
			out.println(Producer_name.get(j)+" - "+Type.get(j));
		}
	}
}
	out.close();
	/*
	if(Prod_location.equals(location.get(i))){
		ResultSet rr;
		Statement stt=null;
		rr=stmt.executeQuery("SELECT Producer_Name FROM producer WHERE Location='"+location.get(i)+"'");
	} else {
		if(no_of_customers.size()==0)
			out.println("SELECT Producer_Name FROM producer WHERE Number_of_Customers=0");
		else{
			if(no_of_customers.get(i)-no_of_customers.get(i-1)<0)
				out.println("SELECT Producer_Name FROM producer WHERE Number_of_Customers="+no_of_customers.get(i));
		}//end of inner else
	}
		
}//end of writing producer name output to service_provider_output
		
*/
	 }
}
}	
						
			
