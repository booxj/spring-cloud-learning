package cloud.mysql.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 10:32
 * @since
 */
@SpringBootApplication
@EnableEurekaServer
public class MysqlEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlEurekaServerApplication.class, args);
    }
}
