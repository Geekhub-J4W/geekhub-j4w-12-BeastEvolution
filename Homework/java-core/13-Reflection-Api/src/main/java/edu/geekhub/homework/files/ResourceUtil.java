package edu.geekhub.homework.files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil {

    private ResourceUtil() {
    }

    public static byte[] getFileData(String fileName, Class<?> cazz) {
        try (InputStream inputStream = cazz.getResourceAsStream("/" + fileName);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            int nRead;
            byte[] data = new byte[1024];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
