import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
import java.util.regex.*;

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
//        File f = new File("/../data/user.txt");
//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        System.out.println(name);
//        while (in.hasNext()) {
//            name = in.nextLine();
//            System.out.println(name);
//        }
//        Scanner in = new Scanner(f);
//        while (in.hasNext()) {
//            System.out.println(in.nextLine());
//        }
//        System.out.println(System.getProperty("user.dir"));
//        File dirc=new File("");
//        System.out.println(dirc.getAbsolutePath());
//        System.out.println(test.class.getResource("data").getPath());
        String userPath=System.getProperty("user.dir");
        String dataPath=userPath+"\\data\\user.txt";
        File userTxt=new File(dataPath);
        Scanner in=new Scanner(userTxt);
        while (in.hasNext()) {
            String tempString=in.nextLine();
            String matchString=".*(u|U)[a-z]*[A-Z]*.*";
            System.out.println(tempString);
            System.out.println(Pattern.matches(matchString,tempString));

        }
    }
}
