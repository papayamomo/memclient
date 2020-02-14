package memclient.redis;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        SimpleRedis myRedis = new SimpleRedis();

        for (int i = 0; i < 1000; i++) {
            System.out.println("-------------------------");
            System.out.println(myRedis.set("111", "111"));//+OK
            System.out.println("-------------------------");
            System.out.println(myRedis.get("222"));
            System.out.println("-------------------------");
            System.out.println(myRedis.get("111"));
            System.out.println("-------------------------");
        }

        myRedis.expire("111", 1000);
        System.out.println(myRedis.ttl("111"));
    }
}
