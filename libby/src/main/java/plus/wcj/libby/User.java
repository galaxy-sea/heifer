package plus.wcj.libby;

import lombok.Data;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
@Data
public class User<T> {
    private String id;
    private String name;

    private T user;

    public User() {
    }

    public User(String id, String name, T user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
