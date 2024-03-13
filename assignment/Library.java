//filename Library.java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    Library(){
        try {
            Scanner reader = new Scanner(new File("users.txt"));
            reader.useDelimiter(";");
            while(reader.hasNext()){
                String id = reader.next().strip();
                if (id.compareTo("") == 0){
                    continue;
                }
                String name = reader.next();
                String contactInfo = reader.next();
                ArrayList<Integer> borrowed = new ArrayList<>();
                String data = reader.next();
                while (data.compareTo("") != 0){
                    borrowed.add(Integer.valueOf(data));
                    data = reader.next();
                }
                users.add(new User(Integer.valueOf(id),name,contactInfo,borrowed));
            }
            reader = new Scanner(new File("books.txt"));
            reader.useDelimiter(";");
            while(reader.hasNext()){
                String id = reader.next().strip();
                if (id.compareTo("") == 0){
                    continue;
                }
                String title = reader.next();
                String author = reader.next();
                String genre = reader.next();
                String available = reader.next();
                books.add(new Book(Integer.valueOf(id),title,author,genre,(available.compareTo("true") == 0)? true: false));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }
    public void addBook(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter title of book: ");
        String title = input.nextLine();
        if (title.isBlank()){
            System.out.println("Invalid title");
            return;
        }
        System.out.println("Enter author of book: ");
        String author = input.nextLine();
        if (author.isBlank()){
            System.out.println("Invalid author");
            return;
        }
        System.out.println("Enter genre of book: ");
        String genre = input.nextLine();
        if (genre.isBlank()){
            System.out.println("Invalid genre");
            return;
        }
        Book book = new Book(books.size(),title,author,genre,true);
        books.add(book);
        System.out.println("Book has been added");

    }
    public void addUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of user: ");
        String name = input.nextLine();
        if (name.isBlank()){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter contact info of user: ");
        String contactInfo = input.nextLine();
        if (contactInfo.isBlank()){
            System.out.println("Invalid contact info");
            return;
        }
        ArrayList<Integer> empty = new ArrayList<>();
        User user = new User(users.size(),name,contactInfo,empty);
        users.add(user);
        System.out.println("User has been added");
    }
    public void checkOut(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of user who wants to borrow book: ");
        String name = input.nextLine();
        int i,j;
        for (i = 0; i < users.size();i++){
            if (users.get(i).name.compareTo(name) == 0){
                break;
            }
        }
        if (i == users.size()){
            System.out.println("No user with that name\n");
            return;
        }
        System.out.println("Enter title of book to borrow: ");
        String title = input.nextLine();
        for (j = 0; j < books.size();j++){
            if (books.get(j).title.compareTo(title) == 0){
                break;
            }
        }
        if (j == books.size()){
            System.out.println("No book with that name\n");
            return;
        }
        if (books.get(j).available == false){
            System.out.println("That book is not yet available\n");
            return;
        }
        users.get(i).borrowed.add(j);
        books.get(j).available = false;
        System.out.println(users.get(i).name + " has borrowed the book \"" + books.get(j).title + "\"");
    }
    public void returnBook(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of user who wants to return book: ");
        String name = input.nextLine();
        int i,j;
        for (i = 0; i < users.size();i++){
            if (users.get(i).name.compareTo(name) == 0){
                break;
            }
        }
        if (i == users.size()){
            System.out.println("No user with that name\n");
            return;
        }
        System.out.println("Enter title of book to return: ");
        String title = input.nextLine();
        for (j = 0; j < users.get(i).borrowed.size();j++){
            if (books.get(users.get(i).borrowed.get(j)).title.compareTo(title) == 0){
                break;
            }
        }
        if (j == users.get(i).borrowed.size()){
            System.out.println("No book with that name has been borrowed\n");
            return;
        }
        books.get(users.get(i).borrowed.get(j)).available = true;
        System.out.println(users.get(i).name + " has returned the book \"" + books.get(users.get(i).borrowed.get(j)).title + "\"");
        users.get(i).borrowed.remove(j);
    }
    public void search(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id of user to search for the borrowed books: ");
        int id = input.nextInt();
        int i;
        for (i = 0; i < users.size();i++){
            if (users.get(i).id == id){
                break;
            }
        }
        if (i == users.size()){
            System.out.println("No user with that name\n");
            return;
        }
        System.out.println(users.get(id).name + " has borrowed the following books\n");
        for (i = 0;i < users.get(id).borrowed.size();i++){
            System.out.println(books.get(users.get(id).borrowed.get(i)).title + "\n");
        }
    }
    public void save(){
        try {
            FileWriter writer = new FileWriter("books.txt");
            for (int i = 0; i < books.size();i++){
                writer.append(Integer.toString(books.get(i).id) + ";" + books.get(i).title + ";" + books.get(i).author + ";" + books.get(i).genre + ";" + ((books.get(i).available == true) ? "true":"false") + ";\n");
            }
            writer.close();
            writer = new FileWriter("users.txt");
            String text = "";
            for (int i = 0; i < users.size();i++){
                for (int j = 0;j < users.get(i).borrowed.size();j++){
                    text += Integer.toString(users.get(i).borrowed.get(j)) + ";";
                }
                writer.append(Integer.toString(users.get(i).id) + ";" + users.get(i).name + ";" + users.get(i).contactInfo + ";" + text + ";\n");
                text = "";
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
