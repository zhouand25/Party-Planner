public class Attendee {
	String name;
	int company;
	String role;
	String[] allergies;
	int table;
	int num;
	
	public Attendee(String initName, int initCompany, String initRole, String[] initAllergies, int initTable, int initNum) {
		name = initName;
		company = initCompany;
		role = initRole;
		allergies = initAllergies;
		table = initTable;
		num = initNum;
	}

}
