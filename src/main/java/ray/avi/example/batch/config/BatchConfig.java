package ray.avi.example.batch.config;

import ray.avi.example.batch.tasks.MyTaskOne;
import ray.avi.example.batch.tasks.MyTaskTwo;
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

@Profile("batchExample")
@Configuration
public class BatchConfig extends AbstractBatchConfig {

	@Bean
	protected Step stepOne(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOne", jobRepository).tasklet(new MyTaskOne(), transactionManager).build();
	}

	@Bean
	protected Step stepTwo(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepTwo", jobRepository).tasklet(new MyTaskTwo(), transactionManager).build();
	}

	@Bean(name = "firstBatchJob")
	public Job job(JobRepository jobRepository, @Qualifier("stepOne") Step stepOne, @Qualifier("stepTwo") Step stepTwo) {
		return new JobBuilder("firstBatchJob", jobRepository).preventRestart().start(stepOne).next(stepTwo).build();
	}

}