
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Front end class for London Subway System with 10 functions
 * @author Jiaming Zhang
 *
 */
public class SubwayFrontEnd {
	private static String menu = "--------------------------------------"
			+ "\nLondon Subway System Menu:"
			+ "\n1.Navigation"
			+ "\n2.List Stations"
			+ "\n3.List Connections"
			+ "\n4.Number of Stations"
			+ "\n5.Number of Connections"
			+ "\n6.Add a Station"
			+ "\n7.Add a Connection"
			+ "\n8.Look up a Station"
			+ "\n9.Look up a Connection"
			+ "\n0.Exit\n";
	
	/**
	 * Main method of front end, calls different method in back end
	 * according to user's input
	 * @param args
	 */
	public static void main(String[] args){
		SubwayBackEnd g = new SubwayBackEnd();
		Scanner sc = new Scanner(System.in);
		int command;
		
		do {
		System.out.println(menu);
		command = filterInput(sc);
	    String input;
	    switch(command) {
	    	case 1:
		    	System.out.println("Please enter your current station: ");
		    	input = sc.nextLine();
		    	Station s1 = new Station("\"" + input + "\"");
		    	System.out.println("Please enter your destination: ");
		    	input = sc.nextLine();
		    	Station s2 = new Station("\"" + input + "\"");
		    	try {
		    		System.out.println("Best route:\n" + g.shortestPath(s1, s2));
		    		System.out.println("Estimate time: " + g.getPathCost(s1, s2) + " min");
	    		}catch(NoSuchElementException nse) {
	    			System.out.println("Invalid station name(s) or path doesn't exit!");
	    		}
	    		break;
	    	case 2:
	    		System.out.println("Station list: ");
	    		g.printStations();
	    		break;
	    	case 3:
	    		System.out.println("Connection List: ");
	    		g.printConnections();
	    		break;
	    	case 4:
	    		System.out.println("Number of stations: " + (g.numStations()-1));
	    		break;
	    	case 5:
	    		System.out.println("Number of Connections: " + g.getEdgeCount());
	    		break;
	    	case 6:
	    		System.out.println("Enter Station name: ");
	    		input = sc.nextLine();
	    		System.out.println("Enter longtitude: ");
	    		double longtitude = sc.nextDouble();
	    		System.out.println("Enter altitude: ");
	    		double altitude = sc.nextDouble();
	    		System.out.println("Enter zone: ");
	    		double zone = sc.nextDouble();
	    		System.out.println("Enter total lines: ");
	    		int numLines = sc.nextInt();
	    		System.out.println("Enter number of rails: ");
	    		int numRails = sc.nextInt();
	    		sc.nextLine();
	    		if(g.insertVertex(new Station(g.numStations() + 1,"\"" + input + "\"",longtitude,altitude,zone,numLines,numRails))) {//station ID is assigned with the next available integer (size+1)
	    			System.out.println(input + " successfully added");
	    		}else {
	    			System.out.println(input + " station already exists");
	    		}
	    		break;
	    	case 7:
	    		System.out.println("Enter starting station: ");
	    		input = sc.nextLine();
	    		Station s3 = new Station("\"" + input + "\"");
	    		System.out.println("Enter destination: ");
	    		input =  sc.nextLine();
	    		Station s4 = new Station("\"" + input + "\"");
	    		System.out.println("Enter commuting time: ");
	    		int i = sc.nextInt();
	    		sc.nextLine();
	    		try {
	    			g.insertEdge(s3, s4, i);
	    			System.out.println("Successfully added connection from " + s3 + " to" + s4);
	    		}catch(IllegalArgumentException ile) {
	    			if(ile.getMessage().equals("Cannot add edge with vertices that do not exist")) System.out.println("Invalid station name(s)!");
	    			if(ile.getMessage().equals("Cannot add edge with negative weight")) System.out.println("Commuting time cannot be negative!");
	    		}
	    		break;
	    	case 8:
	    		System.out.println("Enter the station name: ");
	    		input = sc.nextLine();
	    		Station s5 = g.vertices.get(new Station("\"" + input + "\"")).data;
	    		if(s5 == null) {
	    			System.out.println(input + "station doesn't exit");
	    		}else {
	    			System.out.println(s5.printStation());
	    		}
	    		break;
	    	case 9:
	    		System.out.println("Enter starting station: ");
	    		input = sc.nextLine();
	    		Station s6 = new Station("\"" + input + "\"");
	    		System.out.println("Enter ending station: ");
	    		input = sc.nextLine();
	    		Station s7 = new Station("\"" + input + "\"");
	    		if(g.containsEdge(s6, s7)) {
	    			Graph<Station>.Vertex v = g.vertices.get(s6);
	    			for(Graph<Station>.Edge e : v.edgesLeaving)
	    	            if(e.target == g.vertices.get(s7)) {
	    	            	System.out.println("From: " + s6.getStringName() + " to: " + s7.getStringName() + "\nTime: " + e.weight + " minute(s)");
	    	            }
	    	                
	    		}else {
	    			System.out.println("Connection doesn't exist.");
	    		}
	    		break;
	    	case 0:
	    		sc.close();
	    		break;
	    	}
		}while(command != 0);
		System.out.println("Thank you for using London Subway system!\nHave a great trip!");
	}
	
	
	
	/**
	 * Make sure the user inputs a number between 0-9
	 * 
	 * @param sc Scanner
	 * @return a valid user input
	 */
	private static int filterInput(Scanner sc) {
		int command = 0;
		String input;
		boolean isInt = false;
		while(!isInt) {
			try {
				System.out.println("Please enter your command: ");
				input = sc.nextLine();
				command = Integer.parseInt(input);
				if(command < 0 || command > 9) {
					System.out.println("Input has to be between 0-9");
					continue;
				}
				break;
			}catch(NumberFormatException nfe) {
				System.out.println("Input has to be an integer");
				continue;
			}
		}
		return command;
	}
}
