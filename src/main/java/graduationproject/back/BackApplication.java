package graduationproject.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/**
 * @MapperScan("") 指定要扫描的Mapper类的包的路径
 */
@MapperScan("graduationproject.back.mapper")
/**
 * @EnableTransactionManagement 开启事务
 */
@EnableTransactionManagement
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
