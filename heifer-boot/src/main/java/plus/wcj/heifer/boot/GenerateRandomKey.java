package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.core.toolkit.AES;

public class GenerateRandomKey {

    public static void main(String[] args) {
        System.out.println("mpw:" + AES.encrypt("jdbc:mysql://xxxxx :3306/heifer_boot?serverTimezone=UTC", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));
        System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));


        System.out.println(AES.encrypt("r-bp1vyc4m1618jli5kwpd.redis.rds.aliyuncs.com", "thisForYinjuanLi"));
        System.out.println(AES.decrypt("jiD4Yw3oNwCOQZO1bX0ksHZQoZrTH5j+CIqHSZNIWR6H2fb2/Al/Mb6EfAiQrMDKBil4CvkeBh7134KMM3RS5g==","thisForYinjuanLi"));

    }
}
