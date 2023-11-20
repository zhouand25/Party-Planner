 import java.io.File;
 import java.io.FileNotFoundExcwp
 import java.util.Scanner;
 
 public class Tester {
 
ArrayList<attendee> visitors = new ArrayList<attendee>();
int[][] tables = new int[10][10];
int[] tablelength = new int[10];
 
//Most of the fileio code directly copied from w3schools  
  public static void main(String[] args) {
	  try {
      File myObj = new File("partyguests.txt");
      Scanner myReader = new Scanner(myObj);
      ArrayList<attendee> tempVisit = new ArrayList<attendee>();
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] temp = data.splice(",");
		binaryInsert(temp[2], temp[1], temp[3]);
      }
      manualAdjust();
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
   }
  
   
   public static void binaryInsert(String firstName, String lastName, String company) {
	      int min = 0;
	      int max = visitors.size()-1; 
		  int counter = 0;
		  
		  while(Math.pow(2,counter)<visitor.size()) {
			++counter;
			int mid = (min+max)/2;
			int value = parseInt(company);
			
			if(value == visitor.get(mid).getCompany) {
			  visitor.add(value, new attendee(firstName, lastName, company));
			  return;
			}
			if(value>visitor.get(mid).getCompany) {
			  min = mid;		
			}
			if(value>visitor.get(mid).getCompany) {
			  max = mid;		
			}
			
		  }
		  visitor.add(max, new attendee(firstName, lastName, company);    
   }
   
   public static void manualAdjust() {
	 //Maybe put in tester class and not this class which is technically party but too lazy to change
	 System.out.println("Hello, Welcome to PARTY PLANNER!\n");
	 while (true) {
	   System.out.print("If you want to add more people, type 1. If you want to start the arrangement, type 0: ");
	   Scanner r1 = new Scanner(System.in)
	   int mode = r1.nextInt();
	   if(mode==0) {
		 break;   
	   }
	   if(mode==1) {
		System.out.print("First Name: ");
		Scanner s1 = new Scanner(System.in);
		String first = s1.nextLine();
		
		System.out.print("Last Name: ");
		Scanner s2 = new Scanner(System.in);
		String last = s2.nextLine();
		
		//Assume its an int for now but needs to be changed
		System.out.print("Company: ");
        Scanner s3 = new Scanner(System.in);
        int company = s3.nextInt();
        
		binaryInsert(first, last, company);
		System.out.println("User Added\n");
	   }
	   arrangement();
	 }  
  }
  
  public static void arrangement() {
	  
  }
 
 }
