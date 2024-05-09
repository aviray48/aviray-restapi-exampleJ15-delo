package ray.avi.example.aspect;

import java.text.MessageFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import ray.avi.common.util.UtilMethods;

@Configuration
@Aspect
public class ExampleAppAspect {

	Logger logger = LoggerFactory.getLogger(ExampleAppAspect.class);

	@Around("execution(* ray.avi.example.controller.*.*(..)) || execution(* ray.avi.example.service.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long methodStartTime = System.currentTimeMillis();
		String methodName = null;
		String simpleClassName = null;
		try {
			methodName = joinPoint.getSignature().getName();
			simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
			return joinPoint.proceed();
		}catch(Throwable b) {
			logger.error(MessageFormat.format("Error while executing method: {0}, {1}", simpleClassName + "." + methodName, UtilMethods.generateShortErrorMessage(b)));
			throw b;
		}
		finally {
			logger.info("{}.{} | {} | Method completed", simpleClassName,methodName,(System.currentTimeMillis() - methodStartTime) + "ms");
		}
	}
}
