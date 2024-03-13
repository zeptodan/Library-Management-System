import java.util.ArrayList;

public class User {
    int id;
    String name;
    String contactInfo;
    ArrayList<Integer> borrowed = new ArrayList<>();
    User(int id,String name,String contactInfo,ArrayList<Integer> borrowed){
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        for (int i = 0; i < borrowed.size();i++){
            this.borrowed.add(borrowed.get(i)); 
        }
    }
}
