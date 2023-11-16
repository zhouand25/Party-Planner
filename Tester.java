 
 public class Tester {
 
 //COPIED DIRECTLY FROM w3schools [CREDIT TO w3Schools]
 
	public static void main(String[] args) {
		// "myArray = text.split(",");" (From class)
		try {
			File users = new File("filename.txt");
			Scanner myReader = new Scanner(users);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }
 //
 
 }
