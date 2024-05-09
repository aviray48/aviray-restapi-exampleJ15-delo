package ray.avi.example.batch.tasks;

import org.springframework.context.annotation.Profile;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.util.UtilMethods;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Profile("batchExample")
@Slf4j
public class MyTaskOne implements Tasklet {

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
	{
		log.info("{}|MyTaskOne start..", UtilMethods.getMethodName());

		// ... your code

		log.info("{}|MyTaskOne done..", UtilMethods.getMethodName());
		return RepeatStatus.FINISHED;
	}    
}
