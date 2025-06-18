import java.util.*;
public class Terms {
    private final String[] terms;

    public Terms() {
        this.terms = new String[]{
            "\t\t\t\t\t\tLibrary Management System - Terms & Conditions",
            "\t\t\t\t\t\t--------------------------------------------------",
            "\t\t\t\t\t\t1. Users must return borrowed books within the due date.",
            "\t\t\t\t\t\t2. A fine will be imposed for late returns.",
            "\t\t\t\t\t\t3. Books should be handled with care; damaged books must be replaced.",
            "\t\t\t\t\t\t4. Users should not lend borrowed books to others.",
            "\t\t\t\t\t\t5. The library reserves the right to update policies at any time."
        };
    }

    public void display(Scanner ob) {
        for (String line : terms) {
            System.out.println(line);
        }
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        ob.nextLine();
        ob.nextLine();
        ClearConsole.clear();
        new MainMenu();
    }
}
