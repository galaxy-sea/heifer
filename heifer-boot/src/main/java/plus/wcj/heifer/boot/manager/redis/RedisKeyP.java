package plus.wcj.heifer.boot.manager.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BulkMapper;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/23
 */
@Component
@RequiredArgsConstructor
public class RedisKeyP<V> implements RedisKey<V> {

    private final RedisOperations<String, ?> redisOperations;


    @Override
    public Boolean hasKey(String key) {
        return redisOperations.hasKey(key);
    }

    @Override
    public Long countExistingKeys(Collection<String> keys) {
        return redisOperations.countExistingKeys(keys);
    }

    @Override
    public Boolean delete(String key) {
        return redisOperations.delete(key);
    }

    @Override
    public Long delete(Collection<String> keys) {
        return redisOperations.delete(keys);
    }

    @Override
    public Boolean unlink(String key) {
        return redisOperations.unlink(key);
    }

    @Override
    public Long unlink(Collection<String> keys) {
        return redisOperations.unlink(keys);
    }

    @Override
    public DataType type(String key) {
        return redisOperations.type(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisOperations.keys(pattern);
    }

    @Override
    public String randomKey() {
        return redisOperations.randomKey();
    }

    @Override
    public void rename(String oldKey, String newKey) {
        redisOperations.rename(oldKey, newKey);
    }

    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisOperations.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisOperations.expire(key, timeout, unit);
    }

    @Override
    public Boolean expire(String key, Duration timeout) {
        return redisOperations.expire(key, timeout);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return redisOperations.expireAt(key, date);
    }

    @Override
    public Boolean expireAt(String key, Instant expireAt) {
        return redisOperations.expireAt(key, expireAt);
    }

    @Override
    public Boolean persist(String key) {
        return redisOperations.persist(key);
    }

    @Override
    public Boolean move(String key, int dbIndex) {
        return redisOperations.move(key, dbIndex);
    }

    @Override
    public byte[] dump(String key) {
        return redisOperations.dump(key);
    }

    @Override
    public void restore(String key, byte[] value, long timeToLive, TimeUnit unit) {
        redisOperations.restore(key, value, timeToLive, unit);
    }

    @Override
    public void restore(String key, byte[] value, long timeToLive, TimeUnit unit, boolean replace) {
        redisOperations.restore(key, value, timeToLive, unit, replace);
    }

    @Override
    public Long getExpire(String key) {
        return redisOperations.getExpire(key);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisOperations.getExpire(key, timeUnit);
    }

    @Override
    public List<V> sort(SortQuery<String> query) {
        return redisOperations.sort(query);
    }

    @Override
    public <T> List<T> sort(SortQuery<String> query, RedisSerializer<T> resultSerializer) {
        return redisOperations.sort(query, resultSerializer);
    }

    @Override
    public <T> List<T> sort(SortQuery<String> query, BulkMapper<T, V> bulkMapper) {
        return redisOperations.sort(query, bulkMapper);
    }

    @Override
    public <T, S> List<T> sort(SortQuery<String> query, BulkMapper<T, S> bulkMapper, RedisSerializer<S> resultSerializer) {
        return redisOperations.sort(query, bulkMapper, resultSerializer);
    }

    @Override
    public Long sort(SortQuery<String> query, String storeKey) {
        return redisOperations.sort(query, storeKey);
    }

    @Override
    public void watch(String key) {
        redisOperations.watch(key);
    }

    @Override
    public void watch(Collection<String> keys) {
        redisOperations.watch(keys);
    }

    @Override
    public void unwatch() {
        redisOperations.unwatch();
    }

    @Override
    public void multi() {
        redisOperations.multi();
    }

    @Override
    public void discard() {
        redisOperations.discard();
    }

    @Override
    public List<Object> exec() {
        return redisOperations.exec();
    }

    @Override
    public List<Object> exec(RedisSerializer<?> valueSerializer) {
        return redisOperations.exec(valueSerializer);
    }

    @Override
    public void killClient(String host, int port) {
        redisOperations.killClient(host, port);
    }

    @Override
    public void slaveOf(String host, int port) {
        redisOperations.slaveOf(host, port);
    }

    @Override
    public void slaveOfNoOne() {
        redisOperations.slaveOfNoOne();
    }

    @Override
    public void convertAndSend(String destination, Object message) {
        redisOperations.convertAndSend(destination, message);
    }
}
