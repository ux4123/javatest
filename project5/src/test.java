import java.lang.StringBuffer;
public class test {
    public static void main(String[] args) {
        String s1="abcde";
        System.out.println(s1);
        s1.replace('a','g');
        System.out.println(s1);
        System.out.println( s1.replace('a','g'));

        StringBuffer s2=new StringBuffer("abcde");
        System.out.println(s2);
        System.out.println(s2.replace(0,2,"ccc"));
        System.out.println(s2);


    }
}
