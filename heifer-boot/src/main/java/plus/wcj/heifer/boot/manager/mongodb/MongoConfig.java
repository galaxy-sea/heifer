package plus.wcj.heifer.boot.manager.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/9/10
 */
@EnableMongoRepositories(basePackages = "plus.wcj.heifer.boot.repository.mongo")
@Configuration
public class MongoConfig {
}
