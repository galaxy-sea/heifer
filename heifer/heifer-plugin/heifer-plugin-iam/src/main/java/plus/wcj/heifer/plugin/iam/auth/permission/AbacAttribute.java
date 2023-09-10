package plus.wcj.heifer.plugin.iam.auth.permission;

import java.util.List;
import java.util.Objects;

/**
 * 获取Abac的属性
 *
 * @author changjin wei(魏昌进)
 * @since 2023/8/27
 */
public interface AbacAttribute {

    /**
     * 获取属性名
     *
     * @return 属性名
     */

    Attribute getAttributeNames();

    /**
     * 获取属性
     *
     * @return 权限名称
     */
    Attribute getAttributes(Long accountId, Long tenantId);

    /**
     * 获取表达式
     *
     * @param accountId 账户id
     * @param tenantId  租户id
     * @return 表达式
     */
    List<AbacExpression> listAbacExpression(Long accountId, Long tenantId);

    /**
     * 获取账户下的所有权限
     *
     * @param accountId 账户id
     * @param tenantId  租户id
     * @return 权限列表
     */
    List<String> listPermission(Long accountId, Long tenantId, AbacExpression abacAttributeExpression);


    class Attribute {
        private String name;
        private Objects value;
        private Class<?> type;
    }


    class AbacExpression {

        private Long id;

        private String expression;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }


        @Override
        public String toString() {
            return "AbacAttributeExpression{" +
                    "id=" + id +
                    ", expression='" + expression + '\'' +
                    '}';
        }
    }
}
