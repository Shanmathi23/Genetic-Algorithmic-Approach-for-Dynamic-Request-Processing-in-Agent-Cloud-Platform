import java.sql.*;

import jade.core.Agent;

import java.io.*; 
import java.util.*; 
public class ConsumerAgent{
   
	public static void main(String[]args)throws Exception{
		FileInputStream fin = new FileInputStream("C:\\output\\user_input.txt");
		BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fin));
	Connection con=null;
	Statement stmt=null;
	Statement stmt1=null;
    //FileWriter outFile = new FileWriter("C:\\output\\consumer_agent_output.txt");  
    //PrintWriter out = new PrintWriter(outFile); 
    Class.forName("com.mysql.jdbc.Driver");
    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/producer", "root", "");
    //stmt = (Statement) con.createStatement();
	 //con=DriverManager.getConnection(DB_URL,USER,PASS);
	 stmt=con.createStatement();
	 stmt1=con.createStatement();
	 int[] small = new int[100], medium = new int[100], large = new int[100], budget = new int[100];
	 int e_small = 0,e_large = 0,e_medium = 0;
	 String[] Location = new String[100];
	/*READING FROM THE FILE AND GETTING THE COUNT VALUE*/
	 BufferedReader reader = new BufferedReader(new FileReader("C:\\output\\user_input.txt"));
	 int lines = 0;
	 while (reader.readLine() != null) {
	 	lines++;
	 }
	 reader.close();
	 
	 for(int v=0;v<(lines/5);v++) {
		FileWriter outFile = new FileWriter("C:\\output\\consumer_agent_output"+(v+1)+".txt");  
		PrintWriter out = new PrintWriter(outFile);
		System.out.println("\n\n");
		System.out.println("FOR USER: "+(v+1)+"!!!\n");
		small[v] = Integer.parseInt(myReader.readLine()); 
		medium[v] = Integer.parseInt(myReader.readLine()); 
		large[v] = Integer.parseInt(myReader.readLine()); 
		budget[v] = Integer.parseInt(myReader.readLine());
		Location[v] = myReader.readLine();
		System.out.println("Small Candles: "+small[v]);
		System.out.println("Medium Candles: "+medium[v]);
		System.out.println("Large Candles: "+large[v]);
		System.out.println("Budget: "+budget[v]);
		System.out.println("Location: "+Location[v]);
	
	int producer_num = 0;
	float Selling_Price = 0;
	String Quantity_small,Quantity_medium,Quantity_large;
	List<String> producer_list_small=new ArrayList<String>();
	List<String> producer_list_medium=new ArrayList<String>();
	List<String> producer_list_large=new ArrayList<String>();
	List<String> final_producer_list_small=new ArrayList<String>();
	List<String> final_producer_list_medium=new ArrayList<String>();
	List<String> final_producer_list_large=new ArrayList<String>();
	String type=" ";
	int count=0;
	int count_temp=1;//variable to hold the temporary count value
	if(small[v]!=0)
	{
		e_small=1;
		count++;
	}
	if(medium[v]!=0){
		e_medium=2;
		count++;
	}
	if(large[v]!=0){
		e_large=3;
		count++;
	}
	
	ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM producer");
	if(rs.next()) {
		producer_num=rs.getInt(1);
	}
	count_temp=0;//assigning count value for looping
	/*CHECKING AVAILABILITY CONSTRAINT USING THE producer TABLE*/
	//while(!(count_temp==count)){
		
		//for(int i=1;i<=producer_num;i++){
			if(e_small>0) {
				ResultSet rs1 = null;
				ResultSet rs2 = null;
				rs1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Variety='small'");
				int si= 0;
				if (rs1 != null)   
				{  
				  rs1.beforeFirst();  
				  rs1.last();  
				  si = rs1.getRow();  
				}
				rs1.beforeFirst();
			while(rs1.next()) {
				int val = rs1.getInt(1);
				//System.out.println(val);
				//System.out.println("");
				//System.out.println("Small Quantity: "+val);
				rs2=stmt1.executeQuery("SELECT Producer_Name FROM producer WHERE Variety='small' AND Quantity="+val+" AND "+small[v]+"<"+val);
				int size= 0;
				if (rs2 != null)   
				{  
				  rs2.beforeFirst();  
				  rs2.last();  
				  size = rs2.getRow();  
				}
				rs2.beforeFirst();
				//System.out.println("Available Producers: "+size);
				int c = 1;
				for(int s=0;s<size;s++) {
					while(rs2.next()) {
						producer_list_small.add(rs2.getString(1));
						//System.out.println("Producer Available for Small Candles: "+rs2.getString(1));
						c++;
					}
				}
				
			}
			}
			if(e_medium>0) {
				ResultSet rs1 = null;
				ResultSet rs2 = null;
				rs1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Variety='medium'");
				int si= 0;
				if (rs1 != null)   
				{  
				  rs1.beforeFirst();  
				  rs1.last();  
				  si = rs1.getRow();  
				}
				rs1.beforeFirst();
			while(rs1.next()) {
				int val = rs1.getInt(1);
				//System.out.println("");
				//System.out.println("Medium Quantity: "+val);
				rs2=stmt1.executeQuery("SELECT Producer_Name FROM producer WHERE Variety='medium' AND Quantity="+val+" AND "+medium[v]+"<"+val);
				int size= 0;
				if (rs2 != null)   
				{  
				  rs2.beforeFirst();  
				  rs2.last();  
				  size = rs2.getRow();  
				}
				rs2.beforeFirst();
				//System.out.println("Available Producers: "+size);
				int cc = 1;
				for(int s=0;s<size;s++) {
					while(rs2.next()) {
						producer_list_medium.add(rs2.getString(1));
						//System.out.println("Producer Available for Medium Candles: "+rs2.getString(1));
						cc++;
					}
				}
			}
			}
			if(e_large>0) {
				ResultSet rs1 = null;
				ResultSet rs2 = null;
				rs1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Variety='large'");
				int si= 0;
				if (rs1 != null)   
				{  
				  rs1.beforeFirst();  
				  rs1.last();  
				  si = rs1.getRow();  
				}
				rs1.beforeFirst();
			while(rs1.next()) {
				int val = rs1.getInt(1);
				//System.out.println("");
				//System.out.println("Large Quantity: "+val);
				rs2=stmt1.executeQuery("SELECT Producer_Name FROM producer WHERE Variety='large' AND Quantity="+val+" AND "+large[v]+"<"+val);
				int size= 0;
				if (rs2 != null)   
				{  
				  rs2.beforeFirst();  
				  rs2.last();  
				  size = rs2.getRow();  
				}
				rs2.beforeFirst();
				//System.out.println("Available Producers: "+size);
				int ccc = 1;
				for(int s=0;s<size;s++) {
					while(rs2.next()) {
						producer_list_large.add(rs2.getString(1));
						//System.out.println("Producer Available for Large Candles: "+rs2.getString(1));
						ccc++;
					}
				}
				}
			}
	//}//end of for---CHECKING THE producer TABLE
		count_temp++;
//}//end of while--generating array list of producer names

	count_temp=0;
	/*CHECKING BUDGET CONSTRAINT FROM THE ABOVE FETCHED VALUE*/
	//while(!(count_temp==count)){
	producer_list_small = new ArrayList<String>(new LinkedHashSet<String>(producer_list_small));
	producer_list_medium = new ArrayList<String>(new LinkedHashSet<String>(producer_list_medium));
	producer_list_large = new ArrayList<String>(new LinkedHashSet<String>(producer_list_large));
	System.out.println("\n");
	System.out.println("Producers Available:");
	System.out.println("Small: "+producer_list_small);
	System.out.println("Medium: "+producer_list_medium);
	System.out.println("Large: "+producer_list_large);

		int Quantity = 0;
		for(int i=0;i<producer_list_small.size();i++){	
			ResultSet a;
			ResultSet b;
			a=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_small.get(i)+"' AND Variety='small'");
			if (a.next()) {
				Quantity=a.getInt(1);
			}
			b=stmt.executeQuery("SELECT Selling_Price FROM producer WHERE Producer_Name='"+producer_list_small.get(i)+"' AND Variety='small'");
			if (b.next()) {
				Selling_Price = b.getFloat(1);
			}
			if(Quantity*Selling_Price<=budget[v]){
				type="Small:";
				out.println(type);
				ResultSet rr;
				ResultSet rr1;
				ResultSet rr2;
				rr=stmt.executeQuery("SELECT Producer_Name FROM producer WHERE Producer_Name='"+producer_list_small.get(i)+"' AND Variety='small'");
				if (rr.next()) {
					out.println(rr.getString(1));
				}
				rr1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_small.get(i)+"' AND Variety='small'");
				if (rr1.next()) {
					out.println(rr1.getString(1));
				}
				rr2=stmt.executeQuery("SELECT Location FROM producer WHERE Producer_Name='"+producer_list_small.get(i)+"' AND Variety='small'");
				if (rr2.next()) {
					out.println(rr2.getString(1));
				}
				final_producer_list_small.add(producer_list_small.get(i));
				
			}
		}
		for(int i=0;i<producer_list_medium.size();i++){	
			ResultSet rrr;
			ResultSet rrr1;
			rrr=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_medium.get(i)+"' AND Variety='medium'");
			if (rrr.next()) {
				Quantity=rrr.getInt(1);
			}
			rrr1=stmt.executeQuery("SELECT Selling_Price FROM producer WHERE Producer_Name='"+producer_list_medium.get(i)+"' AND Variety='medium'");
			if (rrr1.next()) {
				Selling_Price = rrr1.getFloat(1);
			}
			if(Quantity*Selling_Price<=budget[v]){
				type="Medium:";
				out.println(type);
				ResultSet rr;
				ResultSet rr1;
				ResultSet rr2;
				rr=stmt.executeQuery("SELECT Producer_Name FROM producer WHERE Producer_Name='"+producer_list_medium.get(i)+"' AND Variety='medium'");
				if (rr.next()) {
					out.println(rr.getString(1));
				}
				rr1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_medium.get(i)+"' AND Variety='medium'");
				if (rr1.next()) {
					out.println(rr1.getString(1));
				}
				rr2=stmt.executeQuery("SELECT Location FROM producer WHERE Producer_Name='"+producer_list_medium.get(i)+"' AND Variety='medium'");
				if (rr2.next()) {
					out.println(rr2.getString(1));
				}
				final_producer_list_medium.add(producer_list_medium.get(i));					
			}
		}
		for(int i=0;i<producer_list_large.size();i++){	
			ResultSet rrr;
			ResultSet rrr1;
			rrr=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_large.get(i)+"' AND Variety='large'");
			if (rrr.next()) {
				Quantity=rrr.getInt(1);
			}
			rrr1=stmt.executeQuery("SELECT Selling_Price FROM producer WHERE Producer_Name='"+producer_list_large.get(i)+"' AND Variety='large'");
			if (rrr1.next()) {
				Selling_Price = rrr1.getFloat(1);
			}
			if(Quantity*Selling_Price<=budget[v]){
				type="Large:";
				out.println(type);
				ResultSet rr;
				ResultSet rr1;
				ResultSet rr2;
				rr=stmt.executeQuery("SELECT Producer_Name FROM producer WHERE Producer_Name='"+producer_list_large.get(i)+"' AND Variety='large'");
				if (rr.next()) {
					out.println(rr.getString(1));
				}
				rr1=stmt.executeQuery("SELECT Quantity FROM producer WHERE Producer_Name='"+producer_list_large.get(i)+"' AND Variety='large'");
				if (rr1.next()) {
					out.println(rr1.getString(1));
				}
				rr2=stmt.executeQuery("SELECT Location FROM producer WHERE Producer_Name='"+producer_list_large.get(i)+"' AND Variety='large'");
				if (rr2.next()) {
					out.println(rr2.getString(1));
				}
				final_producer_list_large.add(producer_list_large.get(i));				
			}
		}//The above for loop can be converted to Switch-Case Structure
		out.close();
		
		count_temp++;//incrementing temporary count variable for iteration
	//}//LOCALLY BEST SEARCH
	count_temp=0;
	System.out.println("\n");
	System.out.println("Producers Available (With Budget Constraints):");
	System.out.println("Small: "+final_producer_list_small);
	System.out.println("Medium: "+final_producer_list_medium);
	System.out.println("Large: "+final_producer_list_large);
	System.out.println("\n\n");
	 }
	 myReader.close();
}//end of main
}//end of class

	
	
