
import javax.swing.*;
import java.io.*;
import java.util.regex.*;

class mydata {
    private String FilePath;
    private int contimes = 50;

    mydata(String a) {
        FilePath = a;
    }

    mydata(String a, int b) {
        FilePath = a;
        contimes = b;
    }

    //    void Fileread() {
////        File inFile = new File(FilePath);
//
//    }
    String Filename(String originName) {
        Pattern p = Pattern.compile("\\w+.txt");
        Matcher m = p.matcher(originName);
        String rname = new String();
        while (m.find()) {
            rname = m.group().replaceAll(".txt", "ch");
        }
        return rname;
    }

    String filename(int i) {
        return new String(FilePath.replaceAll(".txt", "CH") + i + ".txt");
    }

    void filedebug() {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
//            for (int i = 0; i < 1000; i++) {
//                System.out.println(inputFile.read());
//            }
            int n = 0;
            int num = 0;
            int cct = 0;
            int num2 = 0;
            while (n != -1) {
                n = inputFile.read();
//                System.out.println(n);
                cct++;
                int cnt = 0;
//                cnt = 0;
                while (n != 0) {

                    cnt++;
                    n = inputFile.read();
//                    System.out.println(n);
                    cct++;
                    if (n == -1) break;
                }
//                System.out.println("cnt:" + cnt);
                if (cnt != 3) {
                    num++;
                } else num2++;

            }
            System.out.println("error times:" + num);
            System.out.println("right times:" + num2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double handlemath(byte[] temp) {
        if (temp.length != 4) {
            return 0;
        } else {
            long jisuan = 0;
            double jisuan2 = 0;
            jisuan = ((long) ((temp[0] << 8) + (temp[1] << 16) + (temp[2])));
            if (jisuan > 0x7FFFFF) {
                jisuan -= 0x800000;
                jisuan2 = 2.50 - jisuan * 2.50 / 8388607.0;
            } else {
                jisuan2 = 2.50 + jisuan * 2.50 / 8388607.0;
            }
            return jisuan2;
        }
    }

    void filedebug(int a) {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
            for (int i = 0; i < a; i++) {
                System.out.println(inputFile.read());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Filecreate() {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
            FileWriter outFile1 = new FileWriter(Filename(FilePath) + "1.txt");
            FileWriter outFile2 = new FileWriter(Filename(FilePath) + "2.txt");
            byte[] temp = new byte[4];
            for (int i = 0; i < contimes; i++) {
                inputFile.read(temp);
                while (temp[2] != 0x00) {
                    temp[0] = temp[1];
                    temp[1] = temp[2];
                    temp[2] = temp[3];
                    temp[3] = (byte) inputFile.read();
                }
                //***************************************************************************************************************
                System.out.println("start:*************\n");
                for (byte j : temp) {
                    System.out.println(j);
                }
                System.out.println((Math.abs((temp[0] << 8) + (temp[1] << 16) + temp[3]) / 8388607.5 - 1.0) * 3.3);
                System.out.println("end:****************\n");
                //***************************************************************************************************************
                if (i % 2 == 0) {
                    outFile1.write((Math.abs((temp[0] << 8) + (temp[1] << 16) + temp[3]) / 8388607.5 - 1.0) * 3.3 + "\n");
                    outFile1.flush();
                } else {
                    outFile2.write((Math.abs((temp[0] << 8) + (temp[1] << 16) + temp[3]) / 8388607.5 - 1.0) * 3.3 + "\n");
                    outFile2.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Filecreate(int skiptime, int cnttime) {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
            FileWriter outFile1 = new FileWriter(filename(1));
            FileWriter outFile2 = new FileWriter(filename(2));

            inputFile.skip(skiptime);
            byte[] temp = new byte[4];
            inputFile.read(temp);
            for (int i = 0; i < cnttime; i++) {
                inputFile.read(temp);
                //***************************************************************************************************************
//                System.out.println((Math.abs((temp[0] << 16) + (temp[1] << 8) + temp[3]) / 8388607.5 - 1.0) * 3.3);
//                System.out.println("end:****************\n");
                //***************************************************************************************************************
                if (i % 2 == 0) {
                    outFile1.write(handlemath(temp) + "\n");
                    outFile1.flush();
                } else {
                    outFile2.write(handlemath(temp) + "\n");
                    outFile2.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class drawsingle extends JFrame {
    drawsingle() {
        setSize(800, 800);
        setVisible(true);
    }

}

public class dataconv {
    public static void main(String args[]) {
        if (args.length < 1) {
            mydata ex = new mydata("C:\\Users\\gsc\\Desktop\\发射波形_12.5Hz.txt", 100);
//            ex.Filecreate();
//            drawsingle pic = new drawsingle();
//            ex.filedebug(10000);
            ex.Filecreate(3, 10000);
//            String m = "11";
//            System.out.println(Integer.valueOf(m));
//            Pattern p = Pattern.compile("\\w+.txt");
//            Matcher m = p.matcher("C:\\adad\\11.txt");
//            while (m.find()) {
//                System.out.println(m.group().replaceAll(".txt", "ch"));
//            }

        } else {
            mydata ex = new mydata(args[0], Integer.valueOf(args[1]));
            ex.Filecreate();
//            System.out.println(args.length);
        }
    }

}
//C:\\Users\\gsc\\OneDrive\\F20190117.txt
//"D:\\毕设资料\\贵扬Nov.01\\DataConvert_image\\L00P00.txt"