package ray.avi.example.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import feign.Response;
import feign.codec.ErrorDecoder;
import ray.avi.common.exception.GeneralRuntimeException;
import ray.avi.common.util.UtilMethods;

public class AppFeignDecoder implements ErrorDecoder {
	private static final Logger logger = LoggerFactory.getLogger(AppFeignDecoder.class);
	@Override
	public Exception decode(String methodKey, Response response) {
		if(response.body() != null) {
			try {
				String jsonString = new BufferedReader(new InputStreamReader(response.body().asInputStream())).lines().collect(Collectors.joining("\n"));
				Map<?,?> responseMap = UtilMethods.JSONStringToObjectIgnoreUnknownProperties(jsonString, HashMap.class);
				return new GeneralRuntimeException(responseMap.get("message") != null ? responseMap.get("message").toString() : null, responseMap.get("code") != null ? responseMap.get("code").toString(): null);
			} catch(Exception ex) {
				logger.error("{} | exception occured while converting feign response to jsonString", ex.getMessage());
				return new ErrorDecoder.Default().decode(methodKey, response);
			}
		}
		return new ErrorDecoder.Default().decode(methodKey, response);
	}
}