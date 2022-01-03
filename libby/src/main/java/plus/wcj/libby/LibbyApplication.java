package plus.wcj.libby;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
@EnableEncryptableProperties
public class LibbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibbyApplication.class, args);
    }

}
