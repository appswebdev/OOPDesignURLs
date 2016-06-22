package college.edu.tomer.oopdesignurls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by master on 22/06/16.
 */
public class HttpHandler {
    public static String getUrl(String address) throws IOException {
        URL url = new URL(address);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = reader.readLine();

        StringBuilder newJson = new StringBuilder();

        while (line!=null){
            newJson.append(line);
            line = reader.readLine();
        }
        in.close();
        return newJson.toString();
    }
}
