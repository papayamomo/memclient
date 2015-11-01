package socket.test;

import java.io.*;

/**
 * Created by momo on 15/10/31.
 */
public class ByteUtil {

    public static byte [] write(User user) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        user.write(dos);
        return baos.toByteArray();
    }


    public static User read(byte [] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bais);
        User u = new User();
        return u.read(dis);
    }

    public static byte[] toBytes(User user) {
        return null;
    }

    public static User toUser(byte[] bytes) {
        return null;
    }
}
