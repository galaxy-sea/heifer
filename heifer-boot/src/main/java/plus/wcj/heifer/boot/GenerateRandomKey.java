package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.core.toolkit.AES;

public class GenerateRandomKey {

    public static void main(String[] args) {
        System.out.println("mpw:" + AES.encrypt("jdbc:mysql://47.114.167.3:3306/heifer_boot?serverTimezone=UTC", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("root", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("weichangjin", "xxxxxxxxxxxxxxxx"));


        String encrypt = AES.encrypt("weichangjin", "xxxxxxxxxxxxxxxx");
        System.out.println(AES.decrypt(encrypt, "xxxxxxxxxxxxxxxx"));

    }
}
