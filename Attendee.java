/**
 * Attendee.java
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 * The attendee class within the party planner program allows for the creation of objects based on this class which help to describe
 * and detail each of the party guests, which are the data structures which are stored within the ArrayList visitors
 */

class Attendee {
  /* This class is basically the blueprint for how the program describes each of the individuals within the program,
  it stores important informaiotn such as the first and last name of each attendee and the company id they belong to
  as an integer.
  */
  private String firstName;
  private String lastName;
  private int company;

   //Constructor of the class initializes the first name, last name, and the company id of the individual
  public Attendee(String initfirstName, String initlastName, int initCompany) {
    firstName = initfirstName;
    lastName = initlastName;
    company = initCompany;
  }

  //This method is a getter method which retrieves the first name of the individual
  public String getFirst() {
    return firstName;
  }
  //This is also another getter method except it retrieves the last name of the person
  public String getLast() {
    return lastName;	
  }
  //This getter function returns the company Id of the corresponding company that the person belogns to
  public int getCompany() {
    return company;
  }
}
