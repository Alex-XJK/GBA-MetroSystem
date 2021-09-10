import java.util.*;

/**
 * Hello World
 */
public class HelloWorld {

    static private String courseNumber = "CS 3343";
    static private String groupName = "Group 6";
    static private ArrayList<String> member = new ArrayList<>();

    public static void main(String[] args)
    {
        member.add("Alex_XU");
        // member.add("Your_Name_Here!");

        System.out.printf("We are %s in course %s \n", groupName, courseNumber);
        System.out.printf("Our menbers are : %s \n", member.toString());
        System.out.println();
    }
    
}