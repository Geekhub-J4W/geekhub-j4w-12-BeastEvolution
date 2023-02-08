package edu.geekhub.homework.resurces;

import edu.geekhub.homework.resurces.exceptions.FileReadingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ResourcesUtil {

    private ResourcesUtil() {
    }

    public static byte[] getFileData(String fileName, Class<?> cazz) {
        if (Objects.isNull(fileName)) {
            throw new IllegalArgumentException(
                "Passed fileName String with value equal null"
            );
        } else if (Objects.isNull(cazz)) {
            throw new IllegalArgumentException(
                "Passed cazz Class<?> with value equal null"
            );
        }
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
            throw new FileReadingException("Can't read data form file: " + fileName, e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(
                "File with name: " + fileName + " not exist"
            );
        }
    }
}
