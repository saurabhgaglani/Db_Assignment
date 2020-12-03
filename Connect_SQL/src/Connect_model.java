import java.sql.*; // Importing the java.sql package 
import java.util.*;

public class Connect_model 
{
	private String url;
	private String user;
	private String password;
	Connection connect;
	private static int flag = 0;
	
	//Initialising local variables
public Connect_model(String ul, String ur, String pword)
{
 url = ul;
 user = ur;
 password = pword;
}

public String getInfo(String query, String get_required_String) throws Exception
{
	//Establish the connection
	Class.forName("com.mysql.jdbc.Driver"); //Registering the Driver
	
	//Opening a connection
	flag += 1;
	if(flag == 1)
	{
	System.out.println("\n$$$$ Connecting to " +url + " $$$$\n");
	}
	connect = DriverManager.getConnection(url, user, password);
	Statement st = connect.createStatement();
	
	//Executing queries
	ResultSet rs = st.executeQuery(query);
	rs.next();
	String req_information = rs.getString(get_required_String);
	
	//Closing the connection
	st.close();
	rs.close();

	return req_information;

}


//Creating a method that Prints multiple elements of a SQL table

public void getInfo(String query) throws Exception
{
	//Establish the connection
	Class.forName("com.mysql.jdbc.Driver"); //Registering the Driver
	
	//Opening a connection
	flag += 1;
	if(flag == 1)
	{
	System.out.println("\n$$$$ Connecting to " +url + " $$$$\n");
	}
	connect = DriverManager.getConnection(url, user, password);
	Statement st = connect.createStatement();
	ResultSet rs = st.executeQuery(query);
	ResultSetMetaData rsmd = rs.getMetaData();
	
	int columns = rsmd.getColumnCount();
	                    

	// Iterate through the data in the result set and display it. 

	while (rs.next()) {
	//Print one row          
	for(int i = 1 ; i <= columns; i++){

	      System.out.print(rs.getString(i) + " "); //Print one element of a row

	}

	  System.out.println();//Move to the next line to print the next row.           

	    }
}
	



public void setInfo(String query, String prompt) throws Exception
{
	//Establish the connection
	Class.forName("com.mysql.jdbc.Driver"); //Registering the Driver
	
	//Opening a connection
	flag += 1;
	if(flag == 1)
	{
	System.out.println("\n$$$$ Connecting to " +url + " $$$$\n");
	}
	connect = DriverManager.getConnection(url, user, password);
	Statement st = connect.createStatement();
	
	//Executing queries
	int count = st.executeUpdate(query);
	System.out.println(count+" Rows Affected");
	
	System.out.println(prompt);

	
	
	//Closing the connection
	st.close();
	

}



public static void main(String args[])throws Exception
{
	Scanner sc = new Scanner(System.in);
	Connect_model o = new Connect_model("jdbc:mysql://localhost:3306/Airline", "root", "");
	System.out.println("1. Add Flights \n2. Register \n3.Book Flight\n4. Change Flight\n5. List Flight \n6. Booking Info\n Enter choice");
	int choice = sc.nextInt();
	
	if(choice<0 || choice>6)
	{
		System.out.println("Invalid choice");
	}
	
	
	else
	{
		switch(choice)
		{
		//Making queries
		
		//Case 1 - Add flights
		case 1: 
			System.out.println("Enter details of flight to be added\n***************\n");
			System.out.println("Enter FlightID,  Flight type and Airline code");
			int FlightID = sc.nextInt();
			sc.nextLine();
			String Flighttype = sc.nextLine();
			int AirCode = sc.nextInt();
			System.out.println("\n*********** Test Output **************");
			System.out.println("insert into Flight (FlightID, PlaneType, AirlineCode) values("+FlightID+","+"'"+Flighttype+"'"+","+AirCode+");");
			o.setInfo("insert into Flight (FlightID, PlaneType, AirlineCode) values("+FlightID+","+"'"+Flighttype+"'"+","+AirCode+");", "\n Flight added succesfully.");
			break;
		
			
		//Case 2 - Register
		case 2:
			String casetwo_ID;
			int ID;
			
			System.out.println("\n ********** Welcome to RyanAir! **********\n");
			System.out.println("Enter your firstname");
			sc.nextLine();
			String fn = sc.nextLine();
			System.out.println("Enter your lastname ");
			String ln = sc.nextLine();
			System.out.println("Choose a username ");
			String un = sc.nextLine();
			System.out.println("Enter your password ");
			String pw = sc.nextLine();
			System.out.println("Enter your email ");
			String em = sc.nextLine();
			
			casetwo_ID = o.getInfo("select MAX(CustomerID) from Customer", "MAX(CustomerID)");
			
			ID = Integer.parseInt(casetwo_ID);
			
			System.out.println(String.format("insert into Customer(CustomerID, CustomerFirstName,CustomerLastName,Username,CustomerPassword, Email) values(%s, '%s', '%s', '%s', '%s', '%s'%n",ID+1, fn, ln, un, pw, em));
			
			o.setInfo(String.format("insert into Customer(CustomerID, CustomerFirstName,CustomerLastName,Username,CustomerPassword, Email) values(%s, '%s', '%s', '%s', '%s', '%s');%n",ID+1, fn, ln, un, pw, em), "\n Registration successful! Welcome to Ryanair, "+fn+".");
			break;
			
		case 3:
			System.out.println("\n ********** Welcome to RyanAir flight booking! **********\n");
			System.out.println("Select Flight you want to book from the list given below \n");
			o.getInfo("select Flight.FlightID, Flight.PlaneType,AirlineCompany.AirlineName from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode;");
			System.out.println();
			int c = sc.nextInt();
			System.out.println("You have chosen this flight");
			o.getInfo(String.format("select Flight.FlightID, Flight.PlaneType,AirlineCompany.AirlineName from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode where FlightID = %s;", c));
			System.out.println("Press y if you would like to continue with your selection and any other key if you want to go back");
			char ch = sc.next().charAt(0);
		
			while (ch != 'y' && ch != 'q')
			{
				System.out.println("Select Flight you want to book from the list given below \n");
				o.getInfo("select Flight.FlightID, Flight.PlaneType,AirlineCompany.AirlineName from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode;");
				System.out.println();
				c = sc.nextInt();
				System.out.println("You have chosen this flight");
				o.getInfo(String.format("select Flight.FlightID, Flight.PlaneType,AirlineCompany.AirlineName from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode where FlightID = %s;", c));
				System.out.println("Press y if you would like to continue with your selection and any other key if you want to go back or press q to exit");
				ch = sc.next().charAt(0);
			}
			
			if(ch == 'y')
			{
				String PayIdstr =  o.getInfo("select MAX(PaymentID) from Payment", "MAX(PaymentID)");
				int PayId = Integer.parseInt(PayIdstr);
				
				System.out.println("\n*************** Payment Page ******************");
				System.out.println("Enter Payment Details");
				System.out.println("Enter Credit Card Number ");
				int cnum = sc.nextInt();
				System.out.println("Enter expiry");
				sc.nextLine();
				int exp = sc.nextInt();
				System.out.println("Enter cvv");
				sc.nextLine();
				int cvv = sc.nextInt();
				
				o.setInfo(String.format("insert into Payment(PaymentID, CardNo, CVV, Expiry) values (%s, %s, %s, %s)",PayId +1, cnum, exp, cvv), "Payment successfull! \n\n Booking successful!");
			}
			
		
	
	
			
			
			
			
			break;
			
		case 4:
			break;
	
	
		}
	}
	
	sc.close();

}

}


