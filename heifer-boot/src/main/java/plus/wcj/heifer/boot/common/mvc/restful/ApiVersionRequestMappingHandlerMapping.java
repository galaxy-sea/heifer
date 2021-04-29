// package plus.wcj.heifer.boot.common.mvc.restful;
//
// import org.springframework.core.annotation.AnnotationUtils;
// import org.springframework.web.servlet.mvc.condition.RequestCondition;
// import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
// import java.lang.reflect.Method;
//
// /**
//  * @author changjin wei(魏昌进)
//  * @date 2021/4/29
//  */
// public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
//
//     @Override
//     protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
//         ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
//         return createCondition(apiVersion);
//     }
//
//     @Override
//     protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
//         ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
//         return createCondition(apiVersion);
//     }
//
//     private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
//         return apiVersion == null ? new ApiVersionCondition() : new ApiVersionCondition(apiVersion.value());
//     }
//
// }
