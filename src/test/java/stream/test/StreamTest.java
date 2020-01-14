package stream.test;

import org.junit.Test;

import java.io.*;

/**
 * Created by momo on 15/10/31.
 */
public class StreamTest {

    @Test
    public void WriteReadFile() {
//        String fileName = "/Users/momo/IdeaProjects/MyMemClient/src/test/java/stream/test/StreamFile";
//        writeFile(fileName, "你好,我是中文!");
//        readFile(fileName);
    }

    private void readFile(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            int tempbyte;
            while((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean writeFile(String fileName, String content) {
        boolean ret = false;
        File file = new File(fileName);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(content.getBytes());
            return true;
        } catch(IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


}