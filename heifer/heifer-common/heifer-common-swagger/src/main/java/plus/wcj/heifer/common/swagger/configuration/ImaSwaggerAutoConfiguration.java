package plus.wcj.heifer.common.swagger.configuration;

import jakarta.annotation.PostConstruct;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import plus.wcj.heifer.metadata.iam.User;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/6/11
 */
@Configuration
@ConditionalOnClass(name = "plus.wcj.heifer.metadata.iam.User")
public class ImaSwaggerAutoConfiguration {

    @PostConstruct
    public void init() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(User.class);
    }

}
