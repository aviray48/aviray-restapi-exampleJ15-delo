package ray.avi.example.aspect;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import feign.FeignException;
import ray.avi.common.util.GeneralProcessingUtil;
import ray.avi.common.util.UtilMethods;
import ray.avi.common.exception.ApiError;
import ray.avi.common.exception.GeneralErrorObject;
import ray.avi.common.exception.GeneralException;
import ray.avi.common.exception.GeneralRuntimeException;

/**
 * Global Error handler for handling the exceptions and then provide user friendly error message
 * with correct HTTP error code to the caller. This helps eliminate the boiler plate code scattered
 * across all over the application and makes exception handling controlled and managed at one place.
 * Spring by default handles all the exception and sets the correct HTTP code in the response body,
 * However it does not set the body of Response message with meaningful error message. This handler
 * will make sure in case of exception it will set correct HTTP error code and will also set most
 * appropriate error message into response body.
 * Spring's default hander is found  {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler}
 * @since 1.0
 */
@ControllerAdvice
@Priority(0)
public class GlobalErrorAspect {

	private static final Logger logger = LoggerFactory.getLogger(GlobalErrorAspect.class);

	@ExceptionHandler
	protected ResponseEntity<Object> handleGeneralException(GeneralException ex) {
		logger.error("{}|Exception thrown GeneralException: {}", UtilMethods.getMethodName(), ex.getMessage());
		return new ResponseEntity<>(new GeneralErrorObject(processGeneralException(ex.getMessage(), "Exception thrown CustomerGeneralException"), processGeneralException(ex.getCode(), "-1")), ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	protected ResponseEntity<Object> handleGeneralRuntimeException(GeneralRuntimeException ex) {
		logger.error("{}|Exception thrown GeneralRuntimeException: {}", UtilMethods.getMethodName(), ex.getMessage());
		return new ResponseEntity<>(new GeneralErrorObject(processGeneralException(ex.getMessage(), "Exception thrown CustomerGeneralException"), processGeneralException(ex.getCode(), "-1")), ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST);
	}

	private String processGeneralException(String message, String defaultMessage) {
		return message != null && message.trim().length() > 0 ? message.trim() : defaultMessage;
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		GeneralProcessingUtil.logIsErrorEnabledWrapper(ex, MessageFormat.format("{0}|Error occurred: {1}", UtilMethods.getMethodName(), ex.getMessage()));
		String errorMessage = ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage();
		ApiError apiError; 
		Map<String, Object> httpMap = null;
		if((ex instanceof HttpStatusCodeException && ((HttpStatusCodeException)ex).getStatusCode().value() >= 400) || (ex instanceof FeignException && ((FeignException)ex).status() >= 400)){
			try{
				httpMap = handleAllInternal(ex);
			}
			catch(IllegalArgumentException iae){
				GeneralProcessingUtil.logIsErrorEnabledWrapper(iae, MessageFormat.format("{0}|Unknown Error Code: {1}, Error Message: {2}", UtilMethods.getMethodName(), (((HttpStatusCodeException)ex).getStatusCode().value()), ex.getMessage()));
			}
			catch(GeneralRuntimeException cge){
				GeneralProcessingUtil.logIsErrorEnabledWrapper(MessageFormat.format("{0}|Unknown Error, Error Message: {1}", UtilMethods.getMethodName(), ex.getMessage()));
			}
		}
		apiError = new ApiError(httpMap != null && httpMap.get(HttpStatus.class.getName()) != null ? (HttpStatus)httpMap.get(HttpStatus.class.getName()) : HttpStatus.BAD_REQUEST, "Error occurred", errorMessage);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	private Map<String, Object> handleAllInternal(Exception ex){
		Map<String, Object> httpMap = new HashMap<>();
		HttpStatus httpStatus = null;
		if(ex instanceof HttpStatusCodeException){
			httpStatus = HttpStatus.valueOf(((HttpStatusCodeException)ex).getStatusCode().value());
		}
		else if(ex instanceof FeignException){
			httpStatus = HttpStatus.valueOf(((FeignException)ex).status());
		}
		else {
			GeneralProcessingUtil.logIsErrorEnabledWrapper(ex, MessageFormat.format("{0}|Unknown Error Class: {1}, Error Message: {2}", UtilMethods.getMethodName(), ex.getClass().getName(), ex.getMessage()));
			throw new GeneralRuntimeException(MessageFormat.format("Unknown Error Class: {0}", ex.getClass().getName()));
		}
		httpMap.put(HttpStatus.class.getName(), httpStatus);
		return httpMap;
	}

}