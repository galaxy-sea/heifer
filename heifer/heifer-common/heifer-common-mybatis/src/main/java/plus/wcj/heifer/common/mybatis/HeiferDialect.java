package plus.wcj.heifer.common.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/27
 */
public class HeiferDialect extends PageHelper {

    private final Map<String, Class<?>> returnType = new ConcurrentHashMap<>();

    @Override
    public boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
        if (!(rowBounds instanceof PageRowBounds)) {
            throw new RuntimeException("Prohibit the use of org.apache.ibatis.session.RowBounds. Using the com.github.pagehelper.PageRowBounds");
        }
        if (Page.class.isAssignableFrom(this.getReturnType(ms))) {
            ((PageRowBounds) rowBounds).setCount(true);
            return super.skip(ms, parameterObject, rowBounds);
        }
        return true;
    }

    private Class<?> getReturnType(MappedStatement ms) {
        String msId = ms.getId();
        Class<?> returnType = this.returnType.get(msId);
        if (returnType != null) {
            return returnType;
        }
        int indexOf = msId.lastIndexOf('.');
        String className = msId.substring(0, indexOf);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            new RuntimeException("ClassNotFoundException: " + className);
        }
        String msMethod = msId.substring(indexOf + 1);
        Method[] clazzMethods = clazz.getMethods();
        Method method = null;
        for (Method clazzMethod : clazzMethods) {
            if (msMethod.equals(clazzMethod.getName())) {
                method = clazzMethod;
                break;
            }
        }
        returnType = method.getReturnType();
        this.returnType.put(msId, returnType);
        return returnType;
    }
}
