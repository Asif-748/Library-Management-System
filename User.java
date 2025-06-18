import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class User extends Person {
    Scanner scanner = new Scanner(System.in);
    private UserSession currentUser=null;

    public User() {
        super("", 0, "", "", 0, 0);
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|   ***Log in/Register***   |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      1. Log in            |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      2. Register          |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tPlease enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            ClearConsole.clear();
            login();
        } else if (choice == 2) {
            ClearConsole.clear();
            signUp();
        } else {
            System.out.print("\n\t\t\t\t\t\tInvalid Input! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }

    public User(String name, int age, String email, String phone, int id, int pin) {
        super(name, age, email, phone, id, pin);
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|   ***Log in/Register***   |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      1. Log in            |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      2. Register          |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tPlease enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            ClearConsole.clear();
            login();
        } else if (choice == 2) {
            ClearConsole.clear();
            signUp();
        } else {
            System.out.print("\n\t\t\t\t\t\tInvalid Input! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }

    private void signUp() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|        ***Sign up***      |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter Name: ");
        String name = scanner.nextLine();

        int id;
        while (true) {
            System.out.print("\t\t\t\t\t\tEnter ID: ");
            id = scanner.nextInt();

            File file = new File("User.txt");
            if (!file.exists()) {
                break;
            }

            boolean idase = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine();
                String text;
                while ((text = reader.readLine()) != null) {
                    String[] details = text.split(",");
                    if (details.length > 1) {
                        int id2 = Integer.parseInt(details[1]);
                        if (id2 == id) {
                            idase = true;
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError checking ID uniqueness.");
            }

            if (idase) {
                System.out.println("\t\t\t\t\t\tThis ID is already used.");
            } else {
                break;
            }
        }

        System.out.print("\t\t\t\t\t\tEnter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t\t\t\t\t\tEnter Email: ");
        String email = scanner.nextLine();

        System.out.print("\t\t\t\t\t\tEnter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("\t\t\t\t\t\tEnter PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        File file = new File("User.txt");
        boolean isNewFile = !file.exists() || file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (isNewFile) {
                writer.write("Name,ID,Age,Email,Phone,PIN");
                writer.newLine();
            }
            writer.write(name + "," + id + "," + age + "," + email + "," + phone + "," + pin);
            writer.newLine();
            writer.flush();
            System.out.print("\t\t\t\t\t\tUser Registered Successfully! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        } catch (IOException e) {
            System.out.print("\t\t\t\t\t\tError saving user data! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }

    private void login() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|        ***Log in***       |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter ID: ");
        int enteredId = scanner.nextInt();
        System.out.print("\t\t\t\t\t\tEnter PIN: ");
        int enteredPin = scanner.nextInt();
        scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length < 6)
                    continue;
                int storedId = Integer.parseInt(userData[1].trim());
                int storedPin = Integer.parseInt(userData[5].trim());

                if (storedId == enteredId && storedPin == enteredPin) {
                        currentUser = new UserSession(storedId,userData[0].trim());                         
                    System.out.println("\t\t\t\t\t\tLogin Successful! Welcome, " + userData[0].trim());
                    ClearConsole.clear();
                    UserMenu();
                    
                }
            }
            System.out.print("\t\t\t\t\t\tInvalid ID or PIN! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        } catch (IOException e) {
            System.out.print("\t\t\t\t\t\tNot Registered! Press Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }

    public void displayInfo() {
        System.out.println("\t\t\t\t\t\tName  : " + name);
        System.out.println("\t\t\t\t\t\tID    : " + id);
        System.out.println("\t\t\t\t\t\tAge   : " + age);
        System.out.println("\t\t\t\t\t\tEmail : " + email);
        System.out.println("\t\t\t\t\t\tPhone : " + phone);
        System.out.println("\t\t\t\t\t\tPIN   : " + pin);
        System.out.println("\t\t\t\t\t\t-----------------------------");
    }

    public void ViewBooks() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      ***Book List***      |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        File file = new File("Books.txt");

        if (!file.exists() || file.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo books available right now.");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                boolean hasBooks = false;

                while ((line = reader.readLine()) != null) {
                    String[] bookDetails = line.split(",\\s*");

                    if (bookDetails.length == 5) {
                        System.out.println("\t\t\t\t\t\tTitle   : " + bookDetails[0].trim());
                        System.out.println("\t\t\t\t\t\tAuthor  : " + bookDetails[1].trim());
                        System.out.println("\t\t\t\t\t\tISBN    : " + bookDetails[2].trim());
                        System.out.println("\t\t\t\t\t\tYear    : " + bookDetails[3].trim());
                        System.out.println("\t\t\t\t\t\tCopies  : " + bookDetails[4].trim());
                        System.out.println("\t\t\t\t\t\t-----------------------------");
                        hasBooks = true;
                    } else {
                        System.out.println("\t\t\t\t\t\tInvalid book data: " + line);
                    }
                }

                if (!hasBooks) {
                    System.out.println("\t\t\t\t\t\tNo books found in the file.");
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError reading the file.");
            }
        }

        System.out.print("\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine();
        scanner.nextLine();
        ClearConsole.clear();
        UserMenu();
    }

    public void RequestBook() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     ***Book Request***    |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        File file = new File("Requested Books.txt");
        boolean fileExists = file.exists(); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { 
            if (!fileExists) {
                writer.write("Book Title, Author");
                writer.newLine();
            }

            System.out.println("\n\t\t\t\t\t\tEnter details for the book:");

            scanner.nextLine();

            System.out.print("\t\t\t\t\t\tTitle: ");
            String title = scanner.nextLine().trim();

            System.out.print("\t\t\t\t\t\tAuthor: ");
            String author = scanner.nextLine().trim();
            writer.write(title + ", " + author);
            writer.newLine();
            writer.flush(); 

            System.out.println("\t\t\t\t\t\tRequest was successful!");

        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tAn error occurred while writing to the file.");
        }

        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine(); 
        ClearConsole.clear();
        UserMenu();
    }

    public void BorrowBook() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     ***Borrow  Book***    |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        File bookFile = new File("Books.txt");
        File borrowedInfoFile = new File("BorrowedInfo.txt");
        //boolean isHeaderPresent = borrowedInfoFile.exists() && borrowedInfoFile.length() > 0;

        if (!bookFile.exists() || bookFile.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo books available.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(bookFile));
                BufferedWriter borrowedWriter = new BufferedWriter(new FileWriter(borrowedInfoFile, true))) {

            reader.readLine(); 
            System.out.print("\t\t\t\t\t\tEnter Book Title: ");
            scanner.nextLine();
            String titleToBorrow = scanner.nextLine().trim();
            boolean bookFound = false;
            //boolean borrowed = false;

            List<String> updatedBooks = new ArrayList<>();
            String line;

            updatedBooks.add("Title, Author, ISBN, Year, Copies");

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",\\s*");
                if (details.length < 5) {
                    updatedBooks.add(line);
                    continue;
                }

                String title = details[0].trim();
                int copies = Integer.parseInt(details[4].trim());

                if (title.equals(titleToBorrow)) {
                    bookFound = true;
                    if (copies > 0) {
                        copies--;
                        //borrowed = true;
                        borrowedWriter.write(currentUser.getUserName() + ", " + currentUser.getUserId() + ", " +
                                details[0] + ", " + details[1] + ", " + details[2]);
                        borrowedWriter.newLine();
                        System.out.println("\n\t\t\t\t\t\tBook borrowed successfully!");
                    } else {
                        System.out.println("\t\t\t\t\t\tBook is out of stock.");
                    }
                }

                updatedBooks.add(details[0] + ", " + details[1] + ", " + details[2] + ", " + details[3] + ", " + copies);
            }

            if (!bookFound) {
                System.out.println("\t\t\t\t\t\tBook not found in the library.");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookFile))) {
                for (String bookLine : updatedBooks) {
                    writer.write(bookLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError processing the request: " + e.getMessage());
        }

        System.out.print("\t\t\t\t\t\tPress Enter to return...");
        // scanner.nextLine();
        scanner.nextLine();
        ClearConsole.clear();
        UserMenu();
    }

    public void ReturnBook() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      ***Return Book***    |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
    
        File bookFile = new File("Books.txt");
        File borrowedInfoFile = new File("BorrowedInfo.txt");
    
        if (!borrowedInfoFile.exists() || borrowedInfoFile.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo borrowed books found.");
            System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            UserMenu();
            return;
        }
    
        System.out.print("\t\t\t\t\t\tBook Title: ");
        scanner.nextLine();
        String titleToReturn = scanner.nextLine().trim();
    
        boolean bookReturned = false;
    
        List<String> updatedBorrowedList = new ArrayList<>();
        List<String> updatedBookList = new ArrayList<>();
    
        try (BufferedReader borrowedReader = new BufferedReader(new FileReader(borrowedInfoFile))) {
            String line = borrowedReader.readLine(); 
            
    
            while ((line = borrowedReader.readLine()) != null) {
                String[] details = line.split(",\\s*");
                if (details.length < 5) continue;
    
                int borrowerId = Integer.parseInt(details[1].trim());
                String title = details[2].trim();
    
                if (borrowerId == currentUser.getUserId() && title.equals(titleToReturn)) {
                    bookReturned = true;
                    System.out.println("\n\t\t\t\t\t\tBook returned successfully!");
                } else {
                    updatedBorrowedList.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError reading borrowed file: " + e.getMessage());
        }
    
        try (BufferedReader bookReader = new BufferedReader(new FileReader(bookFile))) {
            String line = bookReader.readLine(); 
            if (line != null) updatedBookList.add(line);
    
            while ((line = bookReader.readLine()) != null) {
                String[] details = line.split(",\\s*");
                if (details.length < 5) {
                    updatedBookList.add(line);
                    continue;
                }
    
                String title = details[0].trim();
                int copies = Integer.parseInt(details[4].trim());
    
                if (title.equals(titleToReturn)) {
                    copies++; 
                }
    
                updatedBookList.add(details[0] + ", " + details[1] + ", " + details[2] + ", " + details[3] + ", " + copies);
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError reading book file: " + e.getMessage());
        }
        try (BufferedWriter borrowedWriter = new BufferedWriter(new FileWriter(borrowedInfoFile))) {
            for (String entry : updatedBorrowedList) {
                borrowedWriter.write(entry);
                borrowedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError updating borrowed records: " + e.getMessage());
        }
    
        try (BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile))) {
            for (String entry : updatedBookList) {
                bookWriter.write(entry);
                bookWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError updating book file: " + e.getMessage());
        }
    
        if (!bookReturned) {
            System.out.println("\t\t\t\t\t\tNo matching record found for your account.");
        }
    
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine();
        ClearConsole.clear();
        UserMenu();
    }
    

    private void UserMenu() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      ***User Menu***      |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      1. View Books        |");
        System.out.println("\t\t\t\t\t\t|      2. Borrow Book       |");
        System.out.println("\t\t\t\t\t\t|      3. Return Book       |");
        System.out.println("\t\t\t\t\t\t|      4. Request Book      |");
        System.out.println("\t\t\t\t\t\t|      5. Log out           |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter Your Choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                ClearConsole.clear();
                ViewBooks();
                break;
            case 2:
                ClearConsole.clear();
                BorrowBook();
                break;
            case 3:
                ClearConsole.clear();
                ReturnBook();
                break;
            case 4:
                ClearConsole.clear();
                RequestBook();
                break;
            case 5:
                ClearConsole.clear();
                new MainMenu();
                break;
            default:
                System.out.print("\t\t\t\t\t\tInvalid Choice! Try Again...");
                scanner.nextLine();
                scanner.nextLine();
                ClearConsole.clear();
                UserMenu();
        }
    }

}
