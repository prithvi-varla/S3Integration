import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.batchTest"})
public class S3IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3IntegrationApplication.class, args);
	}
}
