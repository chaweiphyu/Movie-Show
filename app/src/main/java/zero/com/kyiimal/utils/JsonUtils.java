package zero.com.kyiimal.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {

    private static byte[] readJsonFile(Context context, String filename) throws IOException {
        InputStream inputStream = context.getAssets().open(filename);
        int size = inputStream.available();
        byte[] buff = new byte[size];
        inputStream.read(buff);
        inputStream.close();
        return buff;
    }

    public static String loadBasicDataItem(Context context, String filename) throws IOException {
        byte[] buff = readJsonFile(context, filename);
        return new String(buff, "UTF-8");
    }
}
