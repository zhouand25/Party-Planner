class Attendee {
	private String firstName;
	private String lastName;
	private int company;
	
	public Attendee(String initfirstName, String initlastName, int initCompany) {
		firstName = initfirstName;
		lastName = initlastName;
		company = initCompany;
	}
	
	public String getFirst() {
          return firstName;
	}
	public String getLast() {
	  return lastName;	
	}
	public int getCompany() {
	  return company;
	}
}
