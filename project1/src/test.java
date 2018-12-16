import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.*;
import java.util.*;

public class test {
    public static void main(String argc[]) throws FileNotFoundException {
//        URL aboutURL=test.class.getResource("icon.ico");
//
//        InputStream str=test.class.getResourceAsStream("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project1\\data\\user.txt");
//
//        Scanner in=new Scanner(str);
//
//        while(in.hasNext())
//            System.out.println(in.nextLine());
        File f = new File("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project1\\data\\user.txt");
//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        System.out.println(name);
//        while (in.hasNext()) {
//            name = in.nextLine();
//            System.out.println(name);
//        }
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            System.out.println(in.nextLine());
        }
    }
}
