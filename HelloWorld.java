import java.util.*;

/**
 * Hello World JAVA File.
 * Sorry for my poor jave code, since I haven't really write java for a long time. 
 * So, feel free to improve it!
 * @version 1.0.0
 * @since   Sept. 10, 2021
 * @author  Alex-XJK
 */
public class HelloWorld {

    static private String courseNumber = "CS 3343";
    static private String groupName = "Group 6";
    static private ArrayList<String> member = new ArrayList<>();

    public static void main(String[] args)
    {
        member.add("Alex_XU");
        // To-do 
        // member.add("Your_Name_Here!");

        System.out.printf("We are %s in course %s \n", groupName, courseNumber);
        System.out.printf("Our menbers are : %s \n", member.toString());
        System.out.println();
    }
    
}
