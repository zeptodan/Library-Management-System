import java.util.Scanner;
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to MyLibrary \nEnter 1 to add new user \nEnter 2 to add new book \nEnter 3 to borrow book \nEnter 4 to return book \nEnter 5 to search for borrowed books \nEnter 6 to exit and save data");
        int choice = input.nextInt();
        while (true){
            switch(choice){
                case 1:
                library.addUser();
                break;
                case 2:
                library.addBook();
                break;
                case 3:
                library.checkOut();
                break;
                case 4:
                library.returnBook();
                break;
                case 5:
                library.search();
                break;
                case 6:
                library.save();
                input.close();
                return;
                default:
                System.out.println("Unrecognized command\n");
            }
            System.out.println("Enter 1 to add new user \nEnter 2 to add new book \nEnter 3 to borrow book \nEnter 4 to return book \nEnter 5 to search for borrowed books \nEnter 6 to exit and save data");
            choice = input.nextInt();
        }
    }
}
