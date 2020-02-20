package memclient.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public String del(final String key) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "del " + key + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

//        String[] results = new String(bytes,0,len).split("\r\n");
//        if (results.length > 1) {
//            return results[1];
//        }
        return new String(bytes,0,len);
    }

    /**
     * SET
     */
    public String sadd(final String key, String... values) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String valuesStr = "";
        for (String value : values) {
            valuesStr += " " + value;
        }
        String cmd = "sadd " + key + " " + valuesStr + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

        return new String(bytes,0,len);
    }


    public Set<String> smembers(final String key) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "smembers " + key + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);


        String[] results = new String(bytes,0,len).split("\r\n");
        if (results.length > 2) {
            Set<String> set = new HashSet<String>();
            for (int i = 2; i < results.length; i++) {
                if (i % 2 == 0) {
                    set.add(results[i]);
                }
            }
            return set;
        }
        return null;
    }


    /**
     * LIST
     */
    public String lpush(final String key, String... values) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String valuesStr = "";
        for (String value : values) {
            valuesStr += " " + value;
        }
        String cmd = "lpush " + key + " " + valuesStr + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);

        return new String(bytes,0,len);
    }

    public String lpop(final String key) throws IOException {

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        String cmd = "lpop " + key + "\r\n";
        os.write(cmd.getBytes());

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);


        String[] results = new String(bytes,0,len).split("\r\n");
//        if (results.length > 2) {
//            Set<String> set = new HashSet<String>();
//            for (int i = 2; i < results.length; i++) {
//                if (i % 2 == 0) {
//                    set.add(results[i]);
//                }
//            }
//            return set;
//        }
        if (results.length > 1)
            return results[1];
        return null;
    }

}
