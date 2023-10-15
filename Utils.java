package webserver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
    public static byte[] readAllFile(String file) {
        FileInputStream filein;
        byte[] data = null;
        try {
            filein = new FileInputStream(file);
            data = filein.readAllBytes();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getResponseDataType(HttpPackage req) {
        String type = null;
        String requestfile = req.getTypeLine()[1];
        if (requestfile.equals("/") || requestfile.endsWith(".html"))
            type = "text/html";
        else if (requestfile.endsWith(".css"))
            type = "text/css";
        else if (requestfile.endsWith(".jpg"))
            type = "image/jpeg";
        else if (requestfile.endsWith(".ico"))
            type = "image/png";
        else if (requestfile.endsWith(".png"))
            type = "image/png";
        return type;
    }

}
