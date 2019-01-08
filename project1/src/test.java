import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.DriverManager;

public class test {
    public static void main(String argc[]) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbTest?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "GSC1996926gsc");
            Statement sta;
            ResultSet re;

//            sta = con.createStatement();
//            re = sta.executeQuery("show databases;");
//            while (re.next())
//                System.out.println(re.getString(1));
//
//            sta = con.createStatement();
//            re = sta.executeQuery("use dbTest;");
//            while (re.next())
//                System.out.println(re.getString(1));

            sta = con.createStatement();
            re = sta.executeQuery("desc person");
            while (re.next())
                System.out.println(re.getString(2));

//            sta = con.createStatement();
            re = sta.executeQuery("select * from person");
            while (re.next())
                System.out.println(re.getString(3));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        URL aboutURL=test.class.getResource("icon.ico");
//
//        InputStream str=test.class.getResourceAsStream("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project1\\data\\user.txt");
//
//        Scanner in=new Scanner(str);
//
//        while(in.hasNext())
//            System.out.println(in.nextLine());
        //File f = new File("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project1\\data\\user.txt");
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
//        URL name=test.class.getResource("");
//        System.out.println(name);
//        File name = new File("");
//        File name1 = new File(System.getProperty("user.dir"));
//        System.out.println(name.getAbsolutePath());
//        System.out.println(name1.getAbsolutePath());
//        File[] lisf = name1.listFiles();
//        for (File i : lisf
//        ) {
//            //System.out.println(i.getName());
//            if(i.getName().compareTo("data")==0)
//            {
////                System.out.println("1");
//                File[] lis=i.listFiles();
//                for(File j:lis)
//                {
//                    if(j.getName().compareTo("user.txt")==0)
//                    {
//                        Scanner in=new Scanner(j);
//                        while(in.hasNext())
//                        System.out.println(in.nextLine());
//                    }
//                }
//                break;
//            }
//        }
//        System.out.println();
//        String userPath = System.getProperty("user.dir");
//        String txtPath = userPath + "\\data\\user.txt";
//        File na = new File(txtPath);
//        Scanner in = new Scanner(na);
//        while (in.hasNext())
//            System.out.println(in.nextLine());
//        System.out.println(na.exists());

    }
}
