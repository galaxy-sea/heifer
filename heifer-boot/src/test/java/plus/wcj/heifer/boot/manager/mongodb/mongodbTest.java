// package plus.wcj.heifer.boot.manager.mongodb;
//
// import lombok.Data;
// import lombok.RequiredArgsConstructor;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.mapping.MongoId;
//
// /**
//  * @author changjin wei(魏昌进)
//  * @since 2021/7/28
//  */
// @SpringBootTest
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
// public class mongodbTest {
//
//     private final MongoTemplate mongoTemplate;
//
//     @Test
//     public void testD() {
//         Demo demo = new Demo();
//         demo.setId(1112);
//         demo.setName("xiapweio");
//
//         Demo insert = this.mongoTemplate.insert(demo);
//
//
//     }
//
//
//     @Data
//     class Demo {
//         @MongoId
//         private Integer id;
//         private String name;
//     }
//
//
// }
