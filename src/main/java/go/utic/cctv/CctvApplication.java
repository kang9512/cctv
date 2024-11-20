package go.utic.cctv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackageClasses = CctvApplication.class)
@SpringBootApplication
public class CctvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CctvApplication.class, args);
	}

}
