package development.alberto.com.javacodingtest.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alber on 11/11/2016.
 */

public class Reader {

    public static List<String> read(String file, InputStream is) {

        List<String> textList = new ArrayList<String>();
        BufferedReader br = null;
        InputStream input = null;

        try {


            String sCurrentLine;
            input = is;
            br = new BufferedReader(new InputStreamReader(is));

            while ((sCurrentLine = br.readLine()) != null) {
                String x = sCurrentLine;
                String array [] = x.split(", ");
                for (String s : array) {
                    Log.i("Text reader: ", s);
                    textList.add(s);
                    if(sCurrentLine == null){
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null || input != null || is != null) {
                    br.close();
                    input.close();
                    is.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return textList;
    }
}
