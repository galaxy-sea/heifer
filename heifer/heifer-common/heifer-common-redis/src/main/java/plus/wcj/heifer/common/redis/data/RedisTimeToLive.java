// package plus.wcj.heifer.common.redis.data;
//
// import org.apache.commons.lang3.RandomUtils;
//
// import org.springframework.data.redis.core.TimeToLive;
//
// import java.security.SecureRandom;
// import java.util.Random;
//
// /**
//  * @author changjin wei(魏昌进)
//  * @since 2021/8/24
//  */
// public interface RedisTimeToLive {
//
//      static final SecureRandom RANDOM = new SecureRandom();
//
//     long startInclusive = 10 * 60;
//     long endExclusive = 100 * 60;
//
//     /**
//      * redis 过期时间
//      *
//      * @return 随机时间
//      */
//     @TimeToLive
//     default long timeToLive() {
//         return RandomUtils.nextLong(startInclusive, endExclusive);
//     }
// }
