package memclient.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleRedis {

    private Socket socket = null;

    public SimpleRedis() {
        try {
            PropertiesReader propertiesReader=new PropertiesReader();
            propertiesReader.getPropertiesReader();

            socket = new Socket(propertiesReader.getData().get("redis_host"), Integer.parseInt(propertiesReader.getData().get("redis_port")));

            socket.setReuseAddress(true);
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);
            socket.setSoLinger(true, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String set(final String key, String value) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "set " + key + " " + value + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

        return new String(bytes,0,len);
    }


    public String get(final String key) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "get " + key + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

        String[] results = new String(bytes,0,len).split("\r\n");
        if (results.length > 1) {
            return results[1];
        }
        return null;
    }

    public void expire(final String key, final int seconds) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "expire " + key + " " + seconds + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

        System.out.println(new String(bytes,0,len));
//        String[] results = new String(bytes,0,len).split("\r\n");
//        if (results.length > 1) {
//            return results[1];
//        }
//        return null;
    }


    public String ttl(final String key) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "ttl " + key + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

//        String[] results = new String(bytes,0,len).split("\r\n");
//        if (results.length > 1) {
//            return results[1];
//        }
        return new String(bytes,0,len);
    }

}
