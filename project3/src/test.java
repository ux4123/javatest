import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.lang.*;

class adbtest {
    private String adbpath;


    public adbtest(String path) {
        adbpath = path;

    }

    public int getPic() {
        try {

            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell screencap  -p /sdcard/sc.png");
            Thread.sleep(500);
            Runtime.getRuntime().exec("cmd /k " + adbpath + "\\adb pull /sdcard/sc.png " + adbpath + "\\sc.png");
            Thread.sleep(5000);
            Runtime.getRuntime().exec(adbpath + "\\adb shell rm /sdcard/sc.png");
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean picFind() {
        File pic = new File(adbpath + "\\sc.png");
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int pix = bi.getRGB(i, j);
                int r = (pix & 0xff0000) >> 16;
                int g = (pix & 0x00ff00) >> 8;
                int b = (pix & 0x0000ff);
                if ((r == 255) && (g == 225) && (b == 77)) {
                    System.out.println("get\n");
                    return true;
                }
            }
        }


        return false;
    }

    public int touchScr() {
        try {
            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1733 913");
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1784 1020");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

public class test {


    static public void main(String[] args) {
        try {
            String adbpath = System.getProperty("user.dir");
            adbtest adb = new adbtest(adbpath);
            adb.getPic();
//            adb.picFind();
            while (true) {
                adb.touchScr();
                System.out.println("touch screen\n");
                Thread.sleep(1000);

//                adb.picFind();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
