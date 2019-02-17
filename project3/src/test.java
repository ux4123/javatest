import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.lang.*;
//import org.opencv.core.*;

class adbtest {
    private String adbpath;
    private File pic;

    public adbtest(String path) {
        adbpath = path;
        pic = new File(adbpath + "\\sc.png");
    }

    public int getPic() {
        try {

            Process pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell screencap  -p /sdcard/sc.png");
            pro.waitFor();
//            Thread.sleep(1000);
            pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb pull /sdcard/sc.png " + adbpath + "\\sc.png");
//            Thread.sleep(1000);
            pro.waitFor();
            pro = Runtime.getRuntime().exec(adbpath + "\\adb shell rm /sdcard/sc.png");
//            Thread.sleep(1000);
            pro.waitFor();
//            pro.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 挑战  244，178，95
    public boolean picFind(int[] area, int[] dst) {
        if (area.length != 4) {
            System.out.println("area is not completed\n");
            return false;
        } else {

            BufferedImage bi = null;
            try {
                bi = ImageIO.read(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(bi.getHeight());
//            System.out.println(bi.getWidth());
            for (int i = area[0]; i < area[2]; i++)
                for (int j = area[1]; j < area[3]; j++) {
                    int pix = bi.getRGB(i, j);
                    int r = (pix & 0xff0000) >> 16;
                    int g = (pix & 0x00ff00) >> 8;
                    int b = (pix & 0x0000ff);
                    if ((r == dst[0]) && (g == dst[1]) && (b == dst[2])) {
                        System.out.println("get\n");
                        return true;
                    }
                }


            return false;
        }
    }

    public int touchScr(int[] point) {
        try {
            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap " + point[0] + " " + point[1]);
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1733 913");
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1353 1064");
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
            double xishu = 1920 / 2280.0;
            String adbpath = System.getProperty("user.dir");
            adbtest adb = new adbtest(adbpath);
            while (true) {
                adb.getPic();
                if (adb.picFind(new int[]{(int) (xishu * 1500), 700, (int) (xishu * 1700), 800}, new int[]{244, 178, 95})) {
                    adb.touchScr(new int[]{(int) (xishu * 1600), 750});
                } else if (adb.picFind(new int[]{(int) (xishu * 1900), 750, (int) (xishu * 2100), 900}, new int[]{197, 172, 145})) {
                    adb.touchScr(new int[]{(int) (xishu * 2000), 825});
                } else {
                    adb.touchScr(new int[]{(int) (xishu * 1140), 540});
                }
//                adb.touchScr();
                System.out.println("touch screen\n");
                Thread.sleep(1500);
//                adb.picFind();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
