import java.util.*;
public class Developer {
    private final String[][] developers;

    public Developer() {
        this.developers = new String[][]{
            {"Md. Asif Jahan", "Lead Developer", "asif.4@gmail.com"},
            {"Taslimun", "Backend Developer", "taslimun.hasan@gmail.com"},
            {"Alif", "Frontend Developer", "alif@gmail.com"},
            {"Anik", "Database Engineer", "anik@gmail.com"}
        };
    }

    public void display(Scanner ob) {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      ***Developers***     |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        
        for (String[] dev : developers) {
            System.out.println("\t\t\t\t\t\tName  : " + dev[0]);
            System.out.println("\t\t\t\t\t\tRole  : " + dev[1]);
            System.out.println("\t\t\t\t\t\tEmail : " + dev[2]);
            System.out.println("\t\t\t\t\t\t-----------------------------");
        }
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        ob.nextLine();
        ob.nextLine();
        ClearConsole.clear();
        new MainMenu();
    }
}