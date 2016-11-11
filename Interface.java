import java.io.*;
import java.util.*;
public class Interface{
	
	public void setup() throws IOException{
		ConsumerAgent consumer=new ConsumerAgent();
		FileWriter outFile = new FileWriter("C:\\output\\user_input.txt");  
		PrintWriter pw = new PrintWriter(outFile);
		System.out.println("Enter the number of users\n:");
		Scanner r=new Scanner(System.in);
		int user=r.nextInt();
		
		for (int i=0;i<user;i++) {
			System.out.println("Enter the number of small candles for user"+(i+1)+"\n:");
			Scanner reader=new Scanner(System.in);
			Scanner reader1=new Scanner(System.in);
			int small=reader.nextInt();
			pw.println(small);
			System.out.println("Enter the number of medium candles for user"+(i+1)+"\n:");
			int medium=reader.nextInt();
			pw.println(medium);
			System.out.println("Enter the number of large candles for user"+(i+1)+"\n:");
			int large=reader.nextInt();
			pw.println(large);
			System.out.println("Enter the user budget for user"+(i+1)+"\n:");
			int budget=reader.nextInt();
			pw.println(budget);
			System.out.println("Enter the location for user"+(i+1)+":\n");
			String location=reader1.nextLine();
			pw.println(location);
		}
		pw.close();
	}
	
	public static void main(String args[]) throws Exception {
		
		Interface i = new Interface();
		i.setup();
		
		System.out.println("\n\n---------------------------------------------------------------------\n\n");
		ConsumerAgent ca = new ConsumerAgent();
		ConsumerAgent.main(args);
		System.out.println("\n\n---------------------------------------------------------------------\n\n");
		CloudSimExample1 cs = new CloudSimExample1();
		CloudSimExample1.main(args);
		
}//end of setup
}//end of class Interface
