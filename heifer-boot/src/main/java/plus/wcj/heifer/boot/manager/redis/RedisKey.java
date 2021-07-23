package plus.wcj.heifer.boot.manager.redis;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BulkMapper;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.serializer.RedisSerializer;

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
public interface RedisKey<V> {

    /**
     * Determine if given {@code key} exists.
     *
     * @param key must not be {@literal null}.
     *
     * @return
     *
     * @see <a href="https://redis.io/commands/exists">Redis Documentation: EXISTS</a>
     */

    Boolean hasKey(String key);

    /**
     * Count the number of {@code keys} that exist.
     *
     * @param keys must not be {@literal null}.
     *
     * @return The number of keys existing among the ones specified as arguments. Keys mentioned multiple times and
     * existing are counted multiple times.
     *
     * @see <a href="https://redis.io/commands/exists">Redis Documentation: EXISTS</a>
     * @since 2.1
     */

    Long countExistingKeys(Collection<String> keys);

    /**
     * Delete given {@code key}.
     *
     * @param key must not be {@literal null}.
     *
     * @return {@literal true} if the key was removed.
     *
     * @see <a href="https://redis.io/commands/del">Redis Documentation: DEL</a>
     */

    Boolean delete(String key);

    /**
     * Delete given {@code keys}.
     *
     * @param keys must not be {@literal null}.
     *
     * @return The number of keys that were removed. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/del">Redis Documentation: DEL</a>
     */

    Long delete(Collection<String> keys);

    /**
     * Unlink the {@code key} from the keyspace. Unlike with {@link #delete(Object)} the actual memory reclaiming here
     * happens asynchronously.
     *
     * @param key must not be {@literal null}.
     *
     * @return The number of keys that were removed. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/unlink">Redis Documentation: UNLINK</a>
     * @since 2.1
     */

    Boolean unlink(String key);

    /**
     * Unlink the {@code keys} from the keyspace. Unlike with {@link #delete(java.util.Collection)} the actual memory reclaiming
     * here happens asynchronously.
     *
     * @param keys must not be {@literal null}.
     *
     * @return The number of keys that were removed. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/unlink">Redis Documentation: UNLINK</a>
     * @since 2.1
     */

    Long unlink(Collection<String> keys);

    /**
     * Determine the type stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/type">Redis Documentation: TYPE</a>
     */

    DataType type(String key);

    /**
     * Find all keys matching the given {@code pattern}.
     *
     * @param pattern must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/keys">Redis Documentation: KEYS</a>
     */

    Set<String> keys(String pattern);

    /**
     * Return a random key from the keyspace.
     *
     * @return {@literal null} no keys exist or when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/randomkey">Redis Documentation: RANDOMKEY</a>
     */

    String randomKey();

    /**
     * Rename key {@code oldKey} to {@code newKey}.
     *
     * @param oldKey must not be {@literal null}.
     * @param newKey must not be {@literal null}.
     *
     * @see <a href="https://redis.io/commands/rename">Redis Documentation: RENAME</a>
     */

    void rename(String oldKey, String newKey);

    /**
     * Rename key {@code oleName} to {@code newKey} only if {@code newKey} does not exist.
     *
     * @param oldKey must not be {@literal null}.
     * @param newKey must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/renamenx">Redis Documentation: RENAMENX</a>
     */

    Boolean renameIfAbsent(String oldKey, String newKey);

    /**
     * Set time to live for given {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param timeout
     * @param unit must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     */

    Boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * Set time to live for given {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param timeout must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @throws IllegalArgumentException if the timeout is {@literal null}.
     * @since 2.3
     */

    Boolean expire(String key, Duration timeout);

    /**
     * Set the expiration for given {@code key} as a {@literal date} timestamp.
     *
     * @param key must not be {@literal null}.
     * @param date must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     */

    Boolean expireAt(String key, Date date);

    /**
     * Set the expiration for given {@code key} as a {@literal date} timestamp.
     *
     * @param key must not be {@literal null}.
     * @param expireAt must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @throws IllegalArgumentException if the instant is {@literal null} or too large to represent as a {@code Date}.
     * @since 2.3
     */

    Boolean expireAt(String key, Instant expireAt);

    /**
     * Remove the expiration from given {@code key}.
     *
     * @param key must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/persist">Redis Documentation: PERSIST</a>
     */

    Boolean persist(String key);

    /**
     * Move given {@code key} to database with {@code index}.
     *
     * @param key must not be {@literal null}.
     * @param dbIndex
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/move">Redis Documentation: MOVE</a>
     */

    Boolean move(String key, int dbIndex);

    /**
     * Retrieve serialized version of the value stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/dump">Redis Documentation: DUMP</a>
     */

    byte[] dump(String key);

    /**
     * Create {@code key} using the {@code serializedValue}, previously obtained using {@link #dump(Object)}.
     *
     * @param key must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @param timeToLive
     * @param unit must not be {@literal null}.
     *
     * @see <a href="https://redis.io/commands/restore">Redis Documentation: RESTORE</a>
     */

    void restore(String key, byte[] value, long timeToLive, TimeUnit unit);

    /**
     * Create {@code key} using the {@code serializedValue}, previously obtained using {@link #dump(Object)}.
     *
     * @param key must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @param timeToLive
     * @param unit must not be {@literal null}.
     * @param replace use {@literal true} to replace a potentially existing value instead of erroring.
     *
     * @see <a href="https://redis.io/commands/restore">Redis Documentation: RESTORE</a>
     * @since 2.1
     */

    void restore(String key, byte[] value, long timeToLive, TimeUnit unit, boolean replace);

    /**
     * Get the time to live for {@code key} in seconds.
     *
     * @param key must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/ttl">Redis Documentation: TTL</a>
     */

    Long getExpire(String key);

    /**
     * Get the time to live for {@code key} in and convert it to the given {@link java.util.concurrent.TimeUnit}.
     *
     * @param key must not be {@literal null}.
     * @param timeUnit must not be {@literal null}.
     *
     * @return {@literal null} when used in pipeline / transaction.
     *
     * @since 1.8
     */

    Long getExpire(String key, TimeUnit timeUnit);

    /**
     * Sort the elements for {@code query}.
     *
     * @param query must not be {@literal null}.
     *
     * @return the results of sort. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/sort">Redis Documentation: SORT</a>
     */

    List<V> sort(SortQuery<String> query);

    /**
     * Sort the elements for {@code query} applying {@link org.springframework.data.redis.serializer.RedisSerializer}.
     *
     * @param query must not be {@literal null}.
     * @param resultSerializer
     *
     * @return the deserialized results of sort. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/sort">Redis Documentation: SORT</a>
     */

    <T> List<T> sort(SortQuery<String> query, RedisSerializer<T> resultSerializer);

    /**
     * Sort the elements for {@code query} applying {@link org.springframework.data.redis.core.BulkMapper}.
     *
     * @param query must not be {@literal null}.
     * @param bulkMapper
     *
     * @return the deserialized results of sort. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/sort">Redis Documentation: SORT</a>
     */

    <T> List<T> sort(SortQuery<String> query, BulkMapper<T, V> bulkMapper);

    /**
     * Sort the elements for {@code query} applying {@link org.springframework.data.redis.core.BulkMapper} and {@link org.springframework.data.redis.serializer.RedisSerializer}.
     *
     * @param query must not be {@literal null}.
     * @param bulkMapper
     * @param resultSerializer
     *
     * @return the deserialized results of sort. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/sort">Redis Documentation: SORT</a>
     */

    <T, S> List<T> sort(SortQuery<String> query, BulkMapper<T, S> bulkMapper, RedisSerializer<S> resultSerializer);

    /**
     * Sort the elements for {@code query} and store result in {@code storeKey}.
     *
     * @param query must not be {@literal null}.
     * @param storeKey must not be {@literal null}.
     *
     * @return number of values. {@literal null} when used in pipeline / transaction.
     *
     * @see <a href="https://redis.io/commands/sort">Redis Documentation: SORT</a>
     */

    Long sort(SortQuery<String> query, String storeKey);

    /**
     * Watch given {@code key} for modifications during transaction started with {@link #multi()}.
     *
     * @param key must not be {@literal null}.
     *
     * @see <a href="https://redis.io/commands/watch">Redis Documentation: WATCH</a>
     */

    void watch(String key);

    /**
     * Watch given {@code keys} for modifications during transaction started with {@link #multi()}.
     *
     * @param keys must not be {@literal null}.
     *
     * @see <a href="https://redis.io/commands/watch">Redis Documentation: WATCH</a>
     */

    void watch(Collection<String> keys);

    /**
     * Flushes all the previously {@link #watch(Object)} keys.
     *
     * @see <a href="https://redis.io/commands/unwatch">Redis Documentation: UNWATCH</a>
     */

    void unwatch();

    /**
     * Mark the start of a transaction block. <br>
     * Commands will be queued and can then be executed by calling {@link #exec()} or rolled back using {@link #discard()}
     * <p>
     *
     * @see <a href="https://redis.io/commands/multi">Redis Documentation: MULTI</a>
     */

    void multi();

    /**
     * Discard all commands issued after {@link #multi()}.
     *
     * @see <a href="https://redis.io/commands/discard">Redis Documentation: DISCARD</a>
     */

    void discard();

    /**
     * Executes all queued commands in a transaction started with {@link #multi()}. <br>
     * If used along with {@link #watch(Object)} the operation will fail if any of watched keys has been modified.
     *
     * @return List of replies for each executed command.
     *
     * @see <a href="https://redis.io/commands/exec">Redis Documentation: EXEC</a>
     */

    List<Object> exec();

    /**
     * Execute a transaction, using the provided {@link org.springframework.data.redis.serializer.RedisSerializer} to deserialize any results that are byte[]s or
     * Collections of byte[]s. If a result is a Map, the provided {@link org.springframework.data.redis.serializer.RedisSerializer} will be used for both the keys
     * and values. Other result types (Long, Boolean, etc) are left as-is in the converted results. Tuple results are
     * automatically converted to TypedTuples.
     *
     * @param valueSerializer The {@link org.springframework.data.redis.serializer.RedisSerializer} to use for deserializing the results of transaction exec
     *
     * @return The deserialized results of transaction exec
     */

    List<Object> exec(RedisSerializer<?> valueSerializer);

    /**
     * Closes a given client connection identified by {@literal ip:port} given in {@code client}.
     *
     * @param host of connection to close.
     * @param port of connection to close
     *
     * @since 1.3
     */

    void killClient(String host, int port);

    /**
     * Change redis replication setting to new master.
     *
     * @param host must not be {@literal null}.
     * @param port
     *
     * @see <a href="https://redis.io/commands/slaveof">Redis Documentation: SLAVEOF</a>
     * @since 1.3
     */

    void slaveOf(String host, int port);

    /**
     * Change server into master.
     *
     * @see <a href="https://redis.io/commands/slaveof">Redis Documentation: SLAVEOF</a>
     * @since 1.3
     */

    void slaveOfNoOne();

    /**
     * Publishes the given message to the given channel.
     *
     * @param destination the channel to publish to, must not be {@literal null}.
     * @param message message to publish
     *
     * @return the number of clients that received the message
     *
     * @see <a href="https://redis.io/commands/publish">Redis Documentation: PUBLISH</a>
     */

    void convertAndSend(String destination, Object message);

}
