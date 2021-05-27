package plus.wcj.heifer.boot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/5/25
 */
@Component
@Data
public class Test {

    @Value("${hahah.hahah}")
    private String hahah;
    // @Value("#{T(com.baomidou.mybatisplus.core.toolkit.AES).decrypt('AmNKrHnSb16mbMa/kDRgIQ==','thisForYinjuanLi')}")
    // private String hahah2;


    @PostConstruct
    public void init(){
        // AES.decrypt("AmNKrHnSb16mbMa/kDRgIQ==", "thisForYinjuanLi");
        System.out.println(hahah);
        // System.out.println(hahah2);
    }
}
