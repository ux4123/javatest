import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.annotation.Native;

import javafx.print.Printer;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.tensorflow.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

class MyPicFind {
    private BufferedImage bipic;
    private String adbpath;
    private int PicNum = 0;
    private Mat mymat;
    private Mat tempPic = Imgcodecs.imread("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj\\temp.png");

    public MyPicFind(String a) {
        adbpath = a;
    }

    public boolean GetPic() {
        try {
            Process pro;
            //swipe screen
//            if (PicNum == 0) {
//                PicNum++;
//            } else if (PicNum < 2) {
//                pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input swipe 2000 800 1000 800");
//                pro.waitFor();
//                PicNum++;
//            } else if (PicNum == 2) {
//                pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input swipe 2000 800 1000 800");
//                pro.waitFor();
//                PicNum = 0;
//            }
            //screen cap
            pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell screencap  -p /sdcard/sc.png");
            pro.waitFor();
            //pull pic
            pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb pull /sdcard/sc.png " + adbpath + "\\sc.png");
            pro.waitFor();
            //rm pic
            pro = Runtime.getRuntime().exec(adbpath + "\\adb shell rm /sdcard/sc.png");
            pro.waitFor();

            mymat = Imgcodecs.imread("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj\\sc.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private int[] findTB() {
        int[] resul = new int[2];
        //read xml
        CascadeClassifier objdec = new CascadeClassifier("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj\\classfi\\cascade.xml");
        MatOfRect matrec = new MatOfRect();
        //detect
        objdec.detectMultiScale(mymat, matrec);
        //identify
        if (matrec.toArray().length <= 0) {
            System.out.println("not find");
            return new int[]{};
        } else {
            for (Rect rect : matrec.toArray()) {
//                System.out.println(String.valueOf(rect.x) + ":" + String.valueOf(rect.y));
                if ((rect.y < 900) && (rect.y > 50) && (Math.abs(rect.width - 135) < 8) && (Math.abs(rect.height - 135) < 8)) {
                    resul[0] = rect.x + rect.width / 2;
                    resul[1] = rect.y + rect.height / 2;
                    Imgproc.rectangle(mymat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
                    Imgcodecs.imwrite("C:\\Users\\gsc\\Desktop\\dst.jpg", mymat);
                    return resul;
//                    System.out.println("x:" + String.valueOf(rect.x) + " y:" + String.valueOf(rect.y) + " w:" + String.valueOf(rect.width) + " h:" + String.valueOf(rect.height));
                }
            }

        }
        return new int[]{};
    }

    private int[] findPP() {
        Mat DstPic = new Mat(mymat.rows() - tempPic.rows() + 1, mymat.cols() - tempPic.cols() + 1, CvType.CV_32FC1);
        Imgproc.matchTemplate(mymat, tempPic, DstPic, Imgproc.TM_CCORR_NORMED);

        Core.normalize(DstPic, DstPic, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        Point MatchPoint = new Point();

        Core.MinMaxLocResult mmlr = Core.minMaxLoc(DstPic);

        MatchPoint = mmlr.maxLoc;

        if ((MatchPoint.y > 50) && (MatchPoint.y < 900)) {
            Imgproc.rectangle(mymat, MatchPoint, new Point(MatchPoint.x + tempPic.cols(), MatchPoint.y + tempPic.rows()),
                    new Scalar(0, 0, 0, 0));
//
            Imgcodecs.imwrite("C:\\Users\\gsc\\Desktop\\result.png", mymat);

            return new int[]{(int) (MatchPoint.x + tempPic.cols() / 2), (int) (MatchPoint.y + tempPic.rows() / 2)};
        } else
            return new int[]{};


    }

    //opencv get B G R
    private boolean findZB(int[] area, int[] dst) {
        for (int i = area[0]; i < area[2]; i++) {
            for (int j = area[1]; j < area[3]; j++) {
                double[] rgbdata = new double[3];
                rgbdata = mymat.get(j, i);
//                System.out.println((Arrays.toString(rgbdata)));
                if ((Math.abs(rgbdata[2] - dst[0]) < 15) && (Math.abs(rgbdata[1] - dst[1]) < 15) && (Math.abs(rgbdata[0] - dst[2]) < 15)) {
                    System.out.println("get\n");
                    return true;
                }
            }
        }
        return false;
    }

    public int touchScr(int[] point) {
        try {
            Process pro = Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap " + point[0] + " " + point[1]);
            pro.waitFor();
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1733 913");
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1353 1064");
//            Runtime.getRuntime().exec("cmd /c " + adbpath + "\\adb.exe shell input tap 1784 1020");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//    private boolean findED() {
//
//    }

    //0: null
    //1:tubiao
    //2:zhenb
    //3:end
    //type == 1 tubiao
    //type == 2 zhunbei
    //type == 3 end
    public int FindPic(int typ) {
        if (typ == 1) {
            int[] point = findPP();
            if (point.length < 2) {
                return 0;
            } else {
                touchScr(point);
                System.out.println(Arrays.toString(point));
                return 1;
            }
        } else if (typ == 2) {
            if (findZB(new int[]{1900, 750, 2100, 900}, new int[]{218, 194, 168})) {
                touchScr(new int[]{(int) (1 * 2000), 825});
                System.out.println("find zhunbei");
                return 2;
            }
        } else if (typ == 3) {
            if (findZB(new int[]{960, 750, 1250, 900}, new int[]{186, 63, 31})) {
                touchScr(new int[]{(int) (1 * 1120), 815});
                System.out.println("find jieshu");
                return 3;
            }
        }
        return 0;
    }

}

public class picfind {
    private static String keyin = "a";

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        MyPicFind pf = new MyPicFind("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\yysgj");

        Scanner scan = new Scanner(System.in);
        new Thread() {
            public void run() {
                while (!keyin.equals("q")) {
                    System.out.println("program is running,press \'q\' to close...");
                    keyin = scan.nextLine();

                }
                System.out.println("Please wait for closing program...");
            }
        }.start();
        pf.GetPic();
        while (!keyin.equals("q")) {
            pf.GetPic();
            pf.FindPic(1);
            pf.FindPic(3);
//            while (pf.FindPic(1) != 1) {
//                pf.GetPic();
//            }
////            pf.GetPic();
////            while (pf.FindPic(2) != 2) {
////                pf.GetPic();
////            }
//            pf.GetPic();
//            while (pf.FindPic(3) != 3) {
//                pf.GetPic();
//            }
//            pf.GetPic();
        }
        System.out.println("exit\n");

//        int name=175;
//        try {
//            BufferedImage bi = ImageIO.read(new File("C:\\Users\\gsc\\Desktop\\src\\6.png"));
//            for (int i = 0; i < 10; i++) {
//                for(int j=0;j<20;j++)
//                ImageIO.write(bi.getSubimage(j*100,i*100,100,100),"jpg",new File("C:\\Users\\gsc\\Desktop\\neg\\"+(name++)+".jpg"));
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(TensorFlow.version());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}


//        try {
//            Pattern pat = Pattern.compile("\\d*.jpg");
//
//            File fi = new File("C:\\Users\\gsc\\Desktop\\neg.txt");
//            BufferedReader bufr = new BufferedReader(new FileReader(fi));
//
//
//            File fo = new File("C:\\Users\\gsc\\Desktop\\pos_image2.txt");
//            OutputStream out = new FileOutputStream(fo);
//            PrintStream pri = new PrintStream(out);
//
//            String s = bufr.readLine();
//            while (s != null) {
//                System.out.println(s);
//                Matcher m = pat.matcher(s);
//                while (m.find()) {
//                    {
//                        pri.println("neg/" + m.group());
//                    }
//                }
//                s = bufr.readLine();
//            }
//            bufr.close();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }