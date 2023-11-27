import java.util.Scanner;
/**
 * Main.java
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 * This class is essentially the driver/tester class which basically is where the program is execueted and the direction of
 * entire program is controlled. It also provides input for the text files that hold the guest files and company files and hold
 * the configuration details and options for the tables and seats.
 */


class Tester {
  /*
  This class directs the program through four major stages: the first stage is the file input where the user inputs the guest
  and company file, configures the details, and creates the party instance. The second stage is when the class calls the manual adjust method and the user 
  themselves can add or register other individuals. The third stage is the arrangement where the program arranges the guests into
  tables. Finally, the last stage is where the user can recieve feedback and is when the tester class calls the results method.
  */
  //Most of the fileio code directly copied from w3schools  
  public static void main(String[] args) {
    System.out.println("Welcome to Party Planner!");
    System.out.print("\n\nFile name containing party guests: ");
    Scanner s1 = new Scanner(System.in);
    String name1 = s1.nextLine();
    System.out.print("\nFile name containing companies: ");
    Scanner s2 = new Scanner(System.in);
    String name2 = s2.nextLine();
    System.out.print("\nNumber of tables: ");
    Scanner s3 = new Scanner(System.in);
    int name3 = s3.nextInt();
    System.out.print("\nNumber of seats per table: ");
    Scanner s4 = new Scanner(System.in);
    int name4 = s4.nextInt();
    //Creates the instance of a party and configures the details
    Party event = new Party(name1, name2, name3, name4);
    event.manualAdjust();
    event.arrange();
    event.results();
  }
}
