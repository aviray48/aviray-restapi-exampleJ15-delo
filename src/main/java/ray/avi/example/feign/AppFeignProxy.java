package ray.avi.example.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import ray.avi.common.vo.GeneralBase;
import ray.avi.example.config.AppFeignDecoder;

@Component
@FeignClient(name = "called-by-feign", url = "${feign.microservice.calledByFeign}" , configuration = AppFeignDecoder.class)
public interface AppFeignProxy {
	
	@GetMapping("${feign.uri.getObjectListFromFeign}")
	List<GeneralBase> getObjectListFromFeign(@RequestHeader HttpHeaders headers);

	@GetMapping("${feign.uri.getObjectListFromFeignWithPathVariable}")
	List<GeneralBase> getObjectListFromFeignWithPathVariable(@PathVariable("feignPathVariable") String feignPathVariable, @RequestHeader HttpHeaders headers);


}
