import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.annotation.Native;


import org.tensorflow.*;

class MyPicFind {
    private BufferedImage bipic;
    private String adbpath;
    private int PicNum = 0;

    public MyPicFind(String a) {
        adbpath = a;
    }

    public boolean GetPic(String PicPath) {
        try {
            Process pro;
            //swipe screen
            if (PicNum == 0) {
                PicNum++;
            } else if (PicNum < 2) {
                pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input swipe 2000 800 1000 800");
                pro.waitFor();
                PicNum++;
            } else if (PicNum == 2) {
                pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input swipe 2000 800 1000 800");
                pro.waitFor();
                PicNum = 0;
            }
            //screen cap
            pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell screencap  -p /sdcard/sc.png");
            pro.waitFor();
            //pull pic
            pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb pull /sdcard/sc.png " + adbpath + "\\sc.png");
            pro.waitFor();
            //rm pic
            pro = Runtime.getRuntime().exec(adbpath + "\\adb shell rm /sdcard/sc.png");
            pro.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean FindPic() {
        return true;
    }

}

public class picfind {
    public static void main(String[] args) {
//        try {
//            BufferedImage bi = ImageIO.read(new File("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj\\114.png"));
//            for (int i = 0; i < 10; i++) {
//                for(int j=0;j<20;j++)
//                ImageIO.write(bi.getSubimage(j*100,i*100,100,100),"jpg",new File("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj\\pic\\"+i+"_"+j+"_0.jpg"));
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        try {
//            System.out.println(TensorFlow.version());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}