import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.*;
import java.io.IOException;

public class test {
    public BufferedImage bi;

    public void findpic(String path) {
        try {
            File pic = new File(path);
            bi = ImageIO.read(pic);
            for (int i = 0; i < bi.getWidth(); i++)
                for (int j = 0; j < bi.getHeight(); j++) {
                    int pix = bi.getRGB(i, j);
                    int r = (pix & 0xff0000) >> 16;
                    int g = (pix & 0x00ff00) >> 8;
                    int b = (pix & 0x0000ff);
                    if ((Math.abs(r - 61) < 90) && (Math.abs(g - 96) < 90) && (Math.abs(b - 214) < 90)) {
                        bi.setRGB(i, j, 0xFF3030);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savepng(String path) {
        try {
            File pic = new File(path);
            ImageIO.write(bi, "png", pic);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test a = new test();
        a.findpic("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project4\\a.jpg");
        a.savepng("C:\\Users\\gsc\\Documents\\GitHub\\javatest\\project4\\b.png");

    }
}
