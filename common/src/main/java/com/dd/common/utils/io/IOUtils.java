package com.dd.common.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class IOUtils {

    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                arrayOutputStream.write(buff, 0, rc);
            }
            byte[] bytes = arrayOutputStream.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
