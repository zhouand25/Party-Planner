import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Party.java
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 * The class shown below depicts the blueprint of the party possessing important methods and data structures which are key to
 * the program. An object of the party class is constructed by taking the different files as input as well as the number of tables and seats, 
 * allowing its aspects to be configured and run with different specifications, while maintaing core function (planning and arranging the seating of the 'party').
 */

public class Party {
  /* The party class is a class that is essentially the structure for a party including the list of visitors,
  the list of companies involved, and allows for input through the txt/csv files with other configuration paraeters
  involving the number of tables and the number of seats. Additionally, it also has multiple methods allowing it to
  arrange tables, create the lists and allow for information to be given to the user.
  */

  ArrayList<Attendee> visitors = new ArrayList<Attendee>();
  ArrayList<String> companies = new ArrayList<String>();
  int[] companyNum;
  int[][] tables;
  int[] tablelength;
  int error[];

  int numTables;
  int numSeats;
  int companyLimit;
  //numTables is companyLimit as implied through Pigeonhole principle

  //row by columns
  //Each row is a table

  public Party(String guestFile, String companyFile, int initnumTables, int initnumSeats) {
    /* Constructor of the party class, involves the two text files involving the list of guests and the list of companies
    it also provides configuration details regarding the number of Table and number of seats allowing it to flexibly extend
    in the event of other scenarios or similar events
    */
    try {
      //Reads in the Companies
      //Most of the fileio code directly copied from w3schools  
      File comp = new File(companyFile);
      Scanner readerTwo = new Scanner(comp);
      ArrayList<String[]> companyTemp = new ArrayList<String[]>();
      while (readerTwo.hasNextLine()) {
        String data = readerTwo.nextLine();
        if(data=="") {
          continue;
        }
        String[] temp = data.split(",");
        companyTemp.add(temp);
      }
      int high = 0;
      for(int i=0; i<companyTemp.size(); ++i) {
        high = Math.max(high, Integer.parseInt(companyTemp.get(i)[0]));
      }
      for(int i=0; i<high+1; ++i) {
        companies.add("");
      }
      //Creates the array list of companies
      //Had a little difficulty because I figured out
      for(int i=0;i<companyTemp.size(); ++i) {
        companies.set(Integer.parseInt(companyTemp.get(i)[0]), companyTemp.get(i)[1]);
      }

    //Reads in the guests
      File guests = new File(guestFile);
      Scanner readerOne = new Scanner(guests);

      numTables = initnumTables;
      numSeats = initnumSeats;
      tables = new int[numTables][numSeats];
      tablelength = new int[numTables];
      companyLimit = initnumTables;
      //Initalizes important variables concerning the details of the party

      companyNum = new int[companies.size()];   

      //Fills the table array with -1 to differentiate the IDs from the empty parts of the array
      for(int i=0; i<numTables; ++i) {
        for(int j=0; j<numSeats; ++j) {
          tables[i][j] = -1;
        }
      }

      while (readerOne.hasNextLine()) {
        String data = readerOne.nextLine();
        //String splice method allowing csv file to be parsed
        String[] temp = data.split(",");
        binaryInsert(temp[2], temp[1], temp[3]);
        ++companyNum[Integer.parseInt(temp[3])];
      }
      readerOne.close();

      //verification funciton, and slight bits of error handling showing problems
      if(!verify()) {
        System.out.print("ERROR IN NUMBER OF REPRESENTATIVES\n\n");
        System.out.println(error[0]);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }



   public void binaryInsert(String firstName, String lastName, String company) {
    /*
    This function essentially sorts the ArrayList of visitors through a insertion sort hybrid with the addition of binary search
    This ultimatly resembles heap sort with a time complexity of nlogn being faster than slower sorts like insertion who have a
    time complexity of n^2. Anyway, the purpose of the sorting is to synchronnize the guest ArrayList with the arrangment function
    to create ease in both the printing and the algorithm.
     */
    
     int value = Integer.parseInt(company);

     if(visitors.size()==0) {
        visitors.add(new Attendee(firstName, lastName, value));
      }

      //Actually is -1 because this is different from normal binary search where here the elements can actually replace the 0th index or the highest index (insertion not a search)
      int min = -1;
      int max = visitors.size(); 
      int counter = 0;

      //Binary Search limit with the additional positions
      while(Math.pow(2,counter)<visitors.size()+2) {
        int mid = (min+max)/2;

        if(value == visitors.get(mid).getCompany()) {
          visitors.add(mid, new Attendee(firstName, lastName, value));
          return;
        }
        if(value>visitors.get(mid).getCompany()) {
          min = mid;		
        }
        //Was actually stuck on this for a while where I accidentally put > instead of <
        if(value<visitors.get(mid).getCompany()) {
          max = mid;		
        }
        ++counter;
      }
      visitors.add(max, new Attendee(firstName, lastName, value));    
   }

   public boolean verify() {
     /*
      These two adjacent parts act as a whole as the verification function which is essentially a screen for the ArrayList of visitors
      making sure that they abide by the rules created. The first part of the verificaiton method checks if the number of visitor is within
      the limit while the second part (overload method) of the method accounts for manual insertions checking to make sure that each company has a max of only
      10 representatives.
     */
     //PART 1
     if(visitors.size()>(numTables*numSeats)) {
       return false;
     }
     //Checks company-guest quanitity array
     for(int i=0; i<companyNum.length; ++i) {
       if(companyNum[i]>numSeats) {
         return false;
       }
     }
     return true;
   }
   public boolean verify(int compID) {
     //PART 2
     return !(companyNum[compID]==numSeats);
   }

   public int exchange(String company) {
    /* This function also has two adjacent parts that act as a bridge between the company names and their numerical IDs. The first part of the method takes in the company name
       then spits out the company ID after a search. The second part (overloaded) directly consults the companies array which acts as a table storing the company name directly
       based on its ID.
     */
    for(int i=0;i<companies.size(); ++i) {
      String target = companies.get(i);
      //String method .equals for string comparison and search
      if(target.equals(company)) {
        return i;
      }
    }
     return 0;
   }
   public String exchange(int index) {
     return companies.get(index);
   }

   public void manualAdjust() {
     /* This method is one of the phases of the driver class and essentially acts as a menu allowing users to add and register their own participants
        The method asks for information such as one's first name, last name, and company name (already exisisting), allowing more people to join. This
        stored data is carried within the visitor ArrayList and inserted through binary Insertion allowing the list of participants to be dynamically alterted at runtime
       */
      System.out.println("Hello, Welcome to PARTY PLANNER!\n");
     System.out.println("FILES LOADED...");

     while (true) {
        System.out.print("If you want to add more people, type 1. If you want to start the arrangement, type 0: ");
        Scanner r1 = new Scanner(System.in);

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
          String company = s3.nextLine();

         //Uses the verification function to check that the manual inserts are valid and can be used
          if(verify(exchange(company))) { 
            binaryInsert(first, last, ""+exchange(company));
          } else {
            System.out.println("Max number of representatives for a company exceeded.");
            continue;
          }
          ++companyNum[exchange(company)];
          System.out.println("User Added\n");
        }
    }  
  }

  public void arrange() {
    /*
    The arrange method arranges the guests from differnet companies into groups of tables with chairs. This method works on a pretty simple algorithm
    it esentially uses the companyNum array which stores the number of representatives from each company and essentially company by company spreads all of 
    its guests sequentialy in different tables (as best as possible, sometimes skipping to avoid full tables)
    */
    int counter = 0;
    for(int i=0; i<companyNum.length; ++i) {
      int tableID=0;
      for(int j=0; j<companyNum[i]; ++j) {
 //CHECK ERORR STUFF
        while(tablelength[tableID]==numSeats) {
          ++tableID;
        }  
        tables[tableID][tablelength[tableID]] = counter;
        ++counter;
        ++tablelength[tableID];
        ++tableID;
        //Important part: The placements sometimes need to wrap around the array in order to reach certain tables and fulfill the requirements
        //Don't need to worry about infractions or duplicates due to the verification process already passing
        if(tableID>=numTables) {
          tableID-=numTables;
        }
      }
    } 
  }

  public void results() {
    /*
    This method basically acts as a menu for the user so that they can receive the informaiton or form of interaction most helpful for them.
    This method is a part of the driver class and offers one the ability to to print and show the configuraitons by table or by company, or a search to 
    locate individual seating.
    */
    while (true) {
      //The differnet options
      System.out.println("\n\nTables arranged. To quit, type 0. To print the rosters by table, type 1. To print the rosters by company, type 2. To search for table information as an individual, type 3");
      Scanner s1 = new Scanner(System.in);
      int mode = s1.nextInt();

      if(mode==0) {
        break;
      }
      if(mode==1) {
        printTable();
      }
      if(mode==2) {
        printCompany();
      }
      if(mode==3) {
        indiv();
      }
    } 
  }

  public void printTable() {
    /*
    This option originating from the results method allows one to print the roster based on the tables. The print will have a heading of the table number
    while listing seats showing the seat number and the first and last name of the individual within that seat, while printing EMPTY when there are no
    occupants of the seat.
    */
    for(int i=0; i<numTables; ++i) {
      System.out.println("\n\n\nTable "+(i+1)+": ");
      for(int j=0; j<numSeats; ++j) {
        System.out.print("Seat "+(j+1)+": ");
        int value = tables[i][j];
        //filler background of the tables matrix
        if(value==-1) {
          System.out.println("EMPTY");
        } else {
          System.out.println(visitors.get(value).getFirst()+" "+visitors.get(value).getLast());
        }
      }
    }
  }

  public void printCompany() {
    /*
    This method prints the table configuration based on the companies which show each of the seating arrangements for each of the individuals within
    that company. Providing an easier, more convenient display for company clients to find their seating arrangements and the arrangements of their
    coworkers in an organized manner.
    */
    int counter = 0;
    for(int i=0; i<companyNum.length; ++i) {
      //Uses exchange method to find the name based on the ID
      String ID = exchange(i);
      if(ID=="") {
        continue;
      } else {
      System.out.println("\n\n\nCompany "+ID+": ");
      }
      for(int j=0; j<companyNum[i]; ++j) {
        //First uses search method returning the position on the table matrix which can allow one to figure out the seat and table they belong to
        int[] temp = search(counter);
        System.out.println(visitors.get(counter).getFirst()+" "+visitors.get(counter).getLast()+"  -->  Table: "+(temp[0]+1)+" Seat: "+(temp[1]+1));
        ++counter;
      }
    }
  }

  public int[] search(int index) {
    /*
    This method is the search method of the program which allows one to find the position of their seat in terms of an array with two elements
    storing the corresponding index of the row and column on the matrix. The input of the method is the index of the person which is essentially
    their ID which is determined based on their order in the visitors ArrayList (which was sorted and arranged in such a manner to make methods
    like these easier to execute and print)
    */
    int[] answer = new int[2];
    for(int i=0; i<numTables; ++i) {
      for(int j=0; j<numSeats; ++j) {
        if(tables[i][j]==index) {
          answer[0]=i;
          answer[1]=j;
          return answer;
        }
      }
    }
    //Returns -1 for both values of the output array in case of the target not existing
    answer[0] = -1;
    answer[1] = -1;
    return answer;
  }

public void indiv() {
  /*
  This method short for individual, is a sort of prerequisite to the individual searching method shown previously. This method collects the first
  and last name of the person as input and then does a different search to identify the ID of the person (order in the ArrayList) based on the name. 
  Then, the ID is used in the search method above to return the person's seating arrangement. This method then prints out the result.
  */
  System.out.println("\nIf nothing shows up as a result, the individual is not on the guest list.");
  System.out.println("\nFirst Name: ");
  Scanner s1 = new Scanner(System.in);
  String first = s1.nextLine();
  System.out.println("Last Name: ");
  Scanner s2 = new Scanner(System.in);
  String last = s2.nextLine();
  //Name input
  
  int[] temp = new int[2];
  for(int i=0; i<visitors.size(); ++i) {
    //Checking that both first and last name match
    if(first.equals(visitors.get(i).getFirst()) && last.equals(visitors.get(i).getLast())) {
      temp = search(i);
      if(temp[0]==-1) {
        //In case of guest not existing the function merely returns
       return;
      }
      System.out.println("\n\n"+first+" "+last+"  -->  "+"Table: "+(temp[0]+1)+" Seat: "+(temp[1]+1));
      break;
    }
  }
}
  
}  

