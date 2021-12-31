package plus.wcj.heifer.common.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/27
 */
public class HeiferDialect extends PageHelper {

    private final Map<String, Class<?>> returnTypes = new ConcurrentHashMap<>();

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
        Class<?> returnType = this.returnTypes.get(msId);
        if (returnType != null) {
            return returnType;
        }
        int indexOf = msId.lastIndexOf('.');
        String className = msId.substring(0, indexOf);
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException: " + className);
        }
        String msMethod = msId.substring(indexOf + 1);
        Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(clazz);
        Method method = null;
        for (Method declaredMethods : allDeclaredMethods) {
            if (msMethod.equals(declaredMethods.getName())) {
                returnType = declaredMethods.getReturnType();
                break;
            }
        }
        this.returnTypes.put(msId, returnType);
        return method.getReturnType();
    }
}
