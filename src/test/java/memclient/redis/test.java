package memclient.redis;

import java.io.IOException;
import java.util.Set;

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

        System.out.println(myRedis.del("111"));
//        System.out.println(myRedis.lpush("111", "1", "2"));


        System.out.println(myRedis.sadd("s111", "2222", "3333"));//+OK

        Set<String> set = myRedis.smembers("s111");
        System.out.println("-----------SET-----------");
        for (String item : set) {
            System.out.println(item);
        }
        System.out.println("-------------------------");

        System.out.println("-----------LIST----------");
        System.out.println(myRedis.lpush("l111", "1", "2"));
        System.out.println(myRedis.lrange("l111", 0, -1));
        System.out.println(myRedis.lpop("l111"));
        System.out.println(myRedis.lpop("l111"));
        System.out.println("-------------------------");
    }
}
