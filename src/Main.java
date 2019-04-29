import java.util.Scanner;

public class Main {
   private String department;
   private String payType;
   private String educationLevel;
    /*
        takes two parameter of type string
        compare every character of two string with each other
        return an integer, denoting the number of differences
     */
    public int compareString(String value1, String value2)
    {
        if (value1.length() == value2.length()) {
            int countDifference = 0;
            for (int characterPosition = 0; characterPosition < value1.length(); characterPosition++) {
                char charOfStringOne = value1.charAt(characterPosition);
                char charOfStringTwo = value2.charAt(characterPosition);
                if (charOfStringOne != charOfStringTwo) {
                    countDifference++;
                }
            }
            return countDifference;
        }
        else
        {
            System.out.println("Strings are not equal");
            return 0;
        }
    }

    public String getDepartment()
    {
        return department;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getPayType() {
        return payType;
    }

    public void getUserInputForDatabase()
    {
        // Reading from System.in
        Scanner reader = new Scanner(System.in);
        department = verifyInput(reader,"Department");
        payType = verifyInput(reader,"pay Type");
        educationLevel = verifyInput(reader,"education level");
        //close the scanner object once finished
        reader.close();
    }

    public String verifyInput(Scanner scanner, String userType)
    {
        System.out.println("Enter a " + userType);
       String userData =  scanner.nextLine();
       while (userData.isEmpty())
       {
           System.out.println("Enter a " + userType);
           userData =  scanner.nextLine();
       }
       return userData;
    }

    public static void main(String[] args) {
        Main m = new Main();
       System.out.println(m.compareString("M4TD52E","M4TD52E"));
       System.out.println(m.compareString("M4TD52E","M4TD52F"));
       System.out.println(m.compareString("M4TD52E","M4TD33F"));
       System.out.println(m.compareString("M4TD52DE","M4TD33F"));
       System.out.println(m.compareString("",""));
       m.getUserInputForDatabase();
       Database database = new Database();
       database.getDataFromDatabase(m.getDepartment(),m.getPayType(),m.getEducationLevel());
    }
}
