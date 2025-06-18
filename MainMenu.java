import java.util.Scanner;
public class MainMenu {
    int choice;
    public MainMenu(){
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|    ***WELCOME TO LMS***   |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t| 1. Admin                  |");
        System.out.println("\t\t\t\t\t\t| 2. User                   |");
        System.out.println("\t\t\t\t\t\t| 3. Terms and Conditions   |");
        System.out.println("\t\t\t\t\t\t| 4. Developer              |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter your choice: ");
        Scanner ob=new Scanner(System.in);
        choice=ob.nextInt();
        if(choice==1){
            ClearConsole.clear();
            new Admin(ob);
        }else if(choice==2){
            ClearConsole.clear();
            new User();
        }
        else if(choice==3){
            ClearConsole.clear();
            Terms t=new Terms();
            t.display(ob);
        }
        else if(choice==4){
            ClearConsole.clear();
            Developer devTeam = new Developer();
            devTeam.display(ob);
        }else{
            System.out.print("\n\t\t\t\t\t\tInvalid Choice!!!\n\t\t\t\t\t\tTry Again...");
            ob.nextLine();
            ob.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }
}
