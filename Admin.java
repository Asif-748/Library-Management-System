import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Person {
    private Scanner scanner;

    public Admin(Scanner scanner) {
        super("", 0, "", "", 0, 0);
        this.scanner = scanner;
        adminLogin();
    }
    public Admin(String name, int age, String email, String phone, int id, int pin) {
        super(name, age, email, phone, id, pin);
    }

    private void adminLogin() { 
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     ***Admin Login***     |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter ID: ");
        int enteredId = scanner.nextInt();
        System.out.print("\t\t\t\t\t\tEnter PIN: ");
        int enteredPin = scanner.nextInt();
        scanner.nextLine();
        if (enteredId == 1748 && enteredPin == 1234) {
            ClearConsole.clear();
            AdminMenu();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("Admin.txt"))) {
            String line = reader.readLine(); 

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData.length < 6)
                    continue;
                int storedId = Integer.parseInt(userData[1].trim());
                int storedPin = Integer.parseInt(userData[5].trim());

                if (storedId == enteredId && storedPin == enteredPin) {
                    System.out.println("\t\t\t\t\t\tLogin Successful! Welcome, " + userData[0].trim());
                    ClearConsole.clear();
                    AdminMenu();
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tNo Such Data Exists!!! \n\t\t\t\t\t\tPress Enter To Return...");
        }
        System.out.println("\t\t\t\t\t\tInvalid ID or PIN!!! \n\t\t\t\t\t\tPress Enter To Return...");
        scanner.nextLine();
        ClearConsole.clear();
        new MainMenu();
    }

    public void addAdmin() { 
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|      ***Add Admin***      |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        scanner.nextLine();
        System.out.print("\t\t\t\t\t\tEnter Name: ");
        String name = scanner.nextLine();

        int id;
        while (true) {
            System.out.print("\t\t\t\t\t\tEnter ID: ");
            id = scanner.nextInt();
            File file = new File("Admin.txt");
            if (!file.exists()) {
                break;
            }
            boolean idExists = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (details.length > 1) {
                        int existingId = Integer.parseInt(details[1]);
                        if (existingId == id) {
                            idExists = true;
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError checking ID uniqueness.");
            }

            if (idExists) {
                System.out.println("\t\t\t\t\t\tThis ID is already taken. Please enter a different ID.");
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
        File file = new File("Admin.txt");
        boolean isNewFile = !file.exists() || file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (isNewFile) {
                writer.write("Name,ID,Age,Email,Phone,PIN");
                writer.newLine();
            }

            writer.write(name + "," + id + "," + age + "," + email + "," + phone + "," + pin);
            writer.newLine();
            writer.flush();

            System.out.print("\t\t\t\t\t\tAdmin Added Successfully!!! \n\t\t\t\t\t\tPress Enter To Return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        } catch (IOException e) {
            System.out.print("\t\t\t\t\t\tError saving admin data!!! \n\t\t\t\t\t\tPress Enter To Return...");
            scanner.nextLine();
            ClearConsole.clear();
            new MainMenu();
        }
    }


    public void AddBook() {
            System.out.println("\t\t\t\t\t\t-----------------------------");
            System.out.println("\t\t\t\t\t\t|       ***Add Book***      |");
            System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tHow many books do you want to add? ");

        int numOfBooks;
        File file = new File("Books.txt");
        boolean isNewFile = !file.exists() || file.length() == 0; 

        try {
            numOfBooks = scanner.nextInt();
            scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { 
                if (isNewFile) {
                    writer.write("Title, Author, ISBN, Released Year, Copies");
                    writer.newLine();
                }

                for (int i = 0; i < numOfBooks; i++) {
                    System.out.println("\n\t\t\t\t\t\tEnter details for Book " + (i + 1));

                    System.out.print("\t\t\t\t\t\tTitle: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("\t\t\t\t\t\tAuthor: ");
                    String author = scanner.nextLine().trim();

                    System.out.print("\t\t\t\t\t\tISBN: ");
                    String isbn = scanner.nextLine().trim();

                    System.out.print("\t\t\t\t\t\tReleased Year: ");
                    while (!scanner.hasNextInt()) { 
                        System.out.print("\t\t\t\t\t\tInvalid input!!! \n\t\t\t\t\t\tEnter a valid year: ");
                        scanner.next();
                    }
                    int year = scanner.nextInt();

                    System.out.print("\t\t\t\t\t\tNumber of Copies: ");
                    while (!scanner.hasNextInt()) { 
                        System.out.print("\t\t\t\t\t\tInvalid input!!! \n\t\t\t\t\t\tEnter a valid number of copies: ");
                        scanner.next();
                    }
                    int copies = scanner.nextInt();
                    scanner.nextLine(); 
                    writer.write(title + ", " + author + ", " + isbn + ", " + year + ", " + copies);
                    writer.newLine();
                }

                System.out.println("\n\t\t\t\t\t\tBooks added successfully!");

            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tAn error occurred while writing to the file.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\t\t\t\t\tInvalid input. \n\t\t\t\t\t\tPlease enter a valid number.");
            scanner.nextLine(); 
        }
        ClearConsole.clear();
        AdminMenu();
    }

    public void RemoveBook() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     ***Remove Book***     |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        File file = new File("Books.txt");
        if (!file.exists() || file.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo books available to remove!!!");
            System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
            scanner.nextLine();
            ClearConsole.clear();
            AdminMenu();
            return;
        }

        System.out.print("\t\t\t\t\t\tTitle of the book: ");
        scanner.nextLine(); 
        String bookToRemove = scanner.nextLine().trim().toLowerCase(); 
        List<String> books = new ArrayList<>();
        boolean bookFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(", "); 
                if (bookData.length > 0 && bookData[0].trim().equalsIgnoreCase(bookToRemove)) {
                    bookFound = true; 
                } else {
                    books.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError reading the file.");
            return;
        }

        if (!bookFound) {
            System.out.println("\t\t\t\t\t\tNo book found with the given title.");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String book : books) {
                    writer.write(book + "\n");
                }
                System.out.println("\t\t\t\t\t\tBook removed successfully!!!");
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError writing to the file!!!");
            }
        }
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine();
        ClearConsole.clear();
        AdminMenu();
    }

    public void Books(int x) {
        if (x == 1) {
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
        } else {
            System.out.println("\t\t\t\t\t\t-----------------------------");
            System.out.println("\t\t\t\t\t\t| ***Requested Book List*** |");
            System.out.println("\t\t\t\t\t\t-----------------------------");

            File file = new File("Requested Books.txt");

            if (!file.exists() || file.length() == 0) {
                System.out.println("\t\t\t\t\t\tNo books available right now.");
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    boolean hasBooks = false;
                    String line = reader.readLine(); 
                    while ((line = reader.readLine()) != null) {
                        String[] bookDetails = line.split(","); 

                        if (bookDetails.length == 2) { 
                            System.out.println("\t\t\t\t\t\tTitle   : " + bookDetails[0].trim());
                            System.out.println("\t\t\t\t\t\tAuthor  : " + bookDetails[1].trim());
                            System.out.println("\t\t\t\t\t\t----------------------------");
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
        }
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine();
        scanner.nextLine();
        ClearConsole.clear();
        AdminMenu();
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
    public void MemberList() {
        File file = new File("User.txt");
            System.out.println("\t\t\t\t\t\t-----------------------------");
            System.out.println("\t\t\t\t\t\t|     ***Member List***     |");
            System.out.println("\t\t\t\t\t\t-----------------------------");

        if (!file.exists() || file.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo users found!");
            System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
            scanner.nextLine(); 
            scanner.nextLine(); 
            ClearConsole.clear();
            AdminMenu();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); 

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    int age = Integer.parseInt(parts[2].trim());
                    String email = parts[3].trim();
                    String phone = parts[4].trim();
                    int pin = Integer.parseInt(parts[5].trim());

                    Person user = new Admin(name, age, email, phone, id, pin);
                    user.displayInfo();
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid data format: " + line);
                }
            }

            System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
            scanner.nextLine();
            scanner.nextLine();
            ClearConsole.clear();
            AdminMenu();
        } catch (IOException e) {
            System.out.println("\t\t\t\t\t\tError reading user data! " + e.getMessage());
            scanner.nextLine();
            scanner.nextLine();
            ClearConsole.clear();
            AdminMenu();
        }
    }
    

    public void TrackBooks() {
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|    ***Borrowed Info***    |");
        System.out.println("\t\t\t\t\t\t-----------------------------");

        File borrowedInfoFile = new File("BorrowedInfo.txt");

        if (!borrowedInfoFile.exists() || borrowedInfoFile.length() == 0) {
            System.out.println("\t\t\t\t\t\tNo books currently borrowed.");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(borrowedInfoFile))) {
                String line = reader.readLine(); 
                if (line == null) {
                    System.out.println("\t\t\t\t\t\tBorrowed books file is empty.");
                    return;
                }
                   int currentUserId = -1;
                   boolean firstEntry = true;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",\s*");
                    if (details.length < 5)
                        continue;
                    int userId = Integer.parseInt(details[1]);
                    String bookTitle = details[2];
                    String author = details[3];
                    String isbn = details[4];
                    if (userId != currentUserId) {
                        if (!firstEntry) {
                            System.out.println(); 
                        }
                        System.out.println("\n\t\t\t\t\t\tUser ID: " + userId);
                        System.out.println("\t\t\t\t\t\t----------------------");
                        currentUserId = userId;
                        firstEntry = false;
                    }
                    System.out.println("\t\t\t\t\t\tBook Title: " + bookTitle);
                    System.out.println("\t\t\t\t\t\tAuthor: " + author);
                    System.out.println("\t\t\t\t\t\tISBN: " + isbn);
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError reading the borrowed books file!!!");
            }
        }
        System.out.print("\n\t\t\t\t\t\tPress Enter to return...");
        scanner.nextLine();
        scanner.nextLine();
        ClearConsole.clear();
        AdminMenu();
    }

    public void AdminMenu() {
        int choice;
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     ***Admin Menu***      |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\t|     1. Book List          |");
        System.out.println("\t\t\t\t\t\t|     2. Add Book           |");
        System.out.println("\t\t\t\t\t\t|     3. Remove Book        |");
        System.out.println("\t\t\t\t\t\t|     4. Requested BookList |");
        System.out.println("\t\t\t\t\t\t|     5. Member List        |");
        System.out.println("\t\t\t\t\t\t|     6. Add Admin          |");
        System.out.println("\t\t\t\t\t\t|     7. Track Borrower     |");
        System.out.println("\t\t\t\t\t\t|     8. Log out            |");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.print("\t\t\t\t\t\tEnter Your Choice: ");
        choice = scanner.nextInt();
        if (choice == 1) {
            ClearConsole.clear();
            Books(1);
        } else if (choice == 2) {
            ClearConsole.clear();
            AddBook();
        } else if (choice == 3) {
            ClearConsole.clear();
            RemoveBook();
        } else if (choice == 4) {
            ClearConsole.clear();
            Books(0);
        } else if (choice == 5) {
            ClearConsole.clear();
            MemberList();
        } else if (choice == 6) {
            ClearConsole.clear();
            addAdmin();
        } else if (choice == 7) {
            ClearConsole.clear();
            TrackBooks();
        } else if (choice == 8) {
            ClearConsole.clear();
            new MainMenu();
        }
    }
}
