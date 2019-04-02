
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
            jisuan = ((long) ((long) (temp[0] << 8) + (long) (temp[1] << 16) + (long) (temp[3])));
            if (jisuan > 8388607) {
                jisuan -= 16777215;
//                jisuan2 = 2.50 - jisuan * 2.50 / 8388607.0;
                jisuan2 = jisuan * 2.50 * 2.50 / 2.32 / 8388607.0;
            } else {
//                jisuan2 = 2.50 + jisuan * 2.50 / 8388607.0;
                jisuan2 = jisuan * 2.50 * 2.50 / 2.32 / 8388607.0;
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
            System.out.println(filename(1) + " and " + filename(2));
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

    void Filecreate(int skiptime) {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
            FileWriter outFile1 = new FileWriter(filename(1));
            FileWriter outFile2 = new FileWriter(filename(2));
            FileWriter outFile3 = new FileWriter(filename(0));

            System.out.println(filename(1) + " and " + filename(2));

            inputFile.skip(skiptime);
            byte[] temp = new byte[4];
            long filesize = inputFile.available();
            int i = 0;
            double num = 0;
            while (filesize != 0) {
                filesize -= 4;
                inputFile.read(temp);
                if (i % 2 == 0) {
                    num = handlemath(temp);
                    outFile1.write(num + "\n");
                    outFile1.flush();
                } else {
                    outFile2.write(handlemath(temp) + "\n");
                    outFile2.flush();

//                    num += handlemath(temp);
//                    outFile3.write(num + "\n");
//                    outFile3.flush();
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double mathhandle(byte[] temp) {
        long jisuan = 0;
        double jisuan2 = 0;
        int temp0 = temp[0];
//        System.out.println(temp[0]);
        if (temp[0] < 0) {
            temp0 = temp[0] + 256;
        }

        jisuan = ((long) ((long) (temp0 << 16) + (long) (temp[1] << 8) + (long) (temp[2])));
        System.out.println(jisuan);
        if (jisuan > 8388607) {
            jisuan -= 16777215;
//                jisuan2 = 2.50 - jisuan * 2.50 / 8388607.0;
            jisuan2 = jisuan * 2.048  / 8388607.0;

        } else {
//                jisuan2 = 2.50 + jisuan * 2.50 / 8388607.0;
            jisuan2 = jisuan * 2.048  / 8388607.0;
        }
        return jisuan2;
    }

    void ad7764(int num) {
        try {
            FileInputStream inputFile = new FileInputStream(FilePath);
            FileWriter outFile = new FileWriter(new String("C:\\Users\\gsc\\Desktop\\test.txt"));
            while (num >= 0) {
                int temp = inputFile.read();
//                System.out.println(temp);
                num--;
                if (temp == 136) {
                    byte[] cal = new byte[3];
                    inputFile.read(cal);
                    num -= 3;
                    double res = mathhandle(cal);
//                    System.out.println(res);
                    outFile.write(res + "\n");
                    outFile.flush();
                }
//                System.out.println(temp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void akm5394(int num) {
        try {
            int i=0;
            FileInputStream inputFile = new FileInputStream(FilePath);
            FileWriter outFile1 = new FileWriter(filename(1));
            FileWriter outFile2 = new FileWriter(filename(2));
            while (num >= 0) {
                int temp = inputFile.read();
//                System.out.println(temp);
                num--;
                if (temp == 0) {
                    byte[] cal = new byte[3];
                    inputFile.read(cal);
                    num -= 3;
                    double res = mathhandle(cal);
                    i++;
//                    System.out.println(res);
                    if(i%2==0) {
                        outFile1.write(res + "\n");
                        outFile1.flush();
                    }
                    else
                    {
                        outFile2.write(res + "\n");
                        outFile2.flush();
                    }
                }
//                System.out.println(temp);

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
            mydata ex = new mydata("F:\\block_1G.bin", 100);
            ex.ad7764(1500);
//            ex.filedebug(100);
//            ex.akm5394(1000);
//            ex.Filecreate();
//            drawsingle pic = new drawsingle();
//            ex.filedebug(10000);
//            ex.Filecreate(0);
//            String m = "11";
//            System.out.println(Integer.valueOf(m));
//            Pattern p = Pattern.compile("\\w+.txt");
//            Matcher m = p.matcher("C:\\adad\\11.txt");
//            while (m.find()) {
//                System.out.println(m.group().replaceAll(".txt", "ch"));
//            }

        } else {
            mydata ex = new mydata(args[0], Integer.valueOf(args[1]));
//            ex.Filecreate();
//            System.out.println(args.length);
        }
    }

}
//C:\\Users\\gsc\\OneDrive\\F20190117.txt
//"D:\\毕设资料\\贵扬Nov.01\\DataConvert_image\\L00P00.txt"