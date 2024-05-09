package ray.avi.example.batch.config;

import ray.avi.example.batch.tasks.MyTask;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;

@Profile("batchExampleSimpleBatch")
@Configuration
public class BatchConfigSimpleBatch extends AbstractBatchConfig {

	@Bean
	protected Step stepOnly(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOnly", jobRepository).tasklet(new MyTask(), transactionManager).build();
	}

	@Bean(name = "simpleBatchJob")
	public Job job(JobRepository jobRepository, @Qualifier("stepOnly") Step stepOnly) {
		return new JobBuilder("simpleBatchJob", jobRepository).preventRestart().start(stepOnly).build();
	}

}