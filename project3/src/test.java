import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;

class adbtest {
    private String adbpath;


    public adbtest(String path) {
        adbpath = path;

    }

    public int getPic() {
        try {
//            Runtime.getRuntime().exec("cmd /c cd" + adbpath);
            BufferedReader ref = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell screen -p /sdcard/sc.png").getInputStream()));
            StringBuffer b = new StringBuffer();
            String line = null;
            while ((line = ref.readLine()) != null) {
                b.append(line + "\n");
            }
            System.out.println(b.toString());
//            System.out.println(adbpath + "\\adb.exe shell screen -p /sdcard/sc.png");
            Thread.sleep(1000);
            Runtime.getRuntime().exec("cmd /c" + adbpath + "\\adb shell pull /sdcard/sc.png " + adbpath);
            Thread.sleep(1000);
//            Runtime.getRuntime().exec(adbpath + "\\adb shell rm /sdcard/sc.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

public class test {


    static public void main(String[] args) {
        String adbpath = System.getProperty("user.dir");
        adbtest adb = new adbtest(adbpath);
        adb.getPic();

        System.out.println(adbpath);
    }
}
