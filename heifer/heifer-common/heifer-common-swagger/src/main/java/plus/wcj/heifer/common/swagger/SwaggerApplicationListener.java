// package plus.wcj.heifer.common.swagger;
//
// import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
// import org.springframework.context.ApplicationListener;
// import org.springframework.core.env.ConfigurableEnvironment;
//
// /**
//  * @author changjin wei(魏昌进)
//  * @since 2022/6/21
//  */
// public class SwaggerApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
//
//     @Override
//     public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
//         ConfigurableEnvironment environment = event.getEnvironment();
//
//         String oldValue = environment.getProperty("spring.mvc.pathmatch.matching-strategy");
//
//
//         System.out.println(oldValue);
//         System.out.println(environment.getProperty("spring.application.name"));
//
//         // System.out.println("old value:"+oldValue);
//         // TODO decrypt password here
//         // String newValue= "decrypted_password";
//         //
//         // Properties props = new Properties();
//         // props.put("spring.datasource.password", newValue);
//         // environment.getPropertySources().addFirst(new PropertiesPropertySource("decrypted_properties", props));
//     }
// }
