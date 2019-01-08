import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import java.io.File;

import java.io.FileWriter;
import java.util.HashMap;

class test {
    public static String ID = "15376524";
    public static String KEY = "Eroj7gaSOuvMyzCBgm61Ylm4";
    public static String secKEY = "I4SFj1oWzDipATW1YozGCIVvxzUCdhv6";

    public static void main(String[] args) {
        try {
            File textt = new File("C:\\Users\\gsc\\Desktop\\re.txt");
            if(!textt.exists())
            {
                textt.createNewFile();
            }
            FileWriter fiw = new FileWriter("C:\\Users\\gsc\\Desktop\\re.txt", true);
            System.out.println(textt.getName());
            AipOcr client = new AipOcr(ID, KEY, secKEY);
            String image = "C:\\Users\\gsc\\Desktop\\111.jpg";
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            options.put("probability", "true");
            JSONObject res = client.basicGeneral(image, options);
           System.out.println(res.toString(2));
            {
//                System.out.println("no text");
                fiw.write(res.toString(2));
                fiw.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}