package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.core.toolkit.AES;

public class GenerateRandomKey {

    public static void main(String[] args) {
        System.out.println("mpw:" + AES.encrypt("jdbc:mysql://xxxxx :3306/heifer_boot?serverTimezone=UTC", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));


        System.out.println(AES.encrypt("r-xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxx"));
        System.out.println(AES.decrypt("xxxxxxxxxxxxxxxx==","xxxxxxxxxxxxxxxx"));

    }
}
