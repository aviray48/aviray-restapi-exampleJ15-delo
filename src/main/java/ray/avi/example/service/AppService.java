package ray.avi.example.service;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ray.avi.common.vo.GeneralBase;
import ray.avi.example.feign.AppFeignProxy;
import ray.avi.common.util.UtilMethods;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AppService {

	@Autowired
	AppFeignProxy appFeignProxy;

	public List<GeneralBase> getObjectList() {
		log.error("{}|start", UtilMethods.getMethodName());
		GeneralBase gbOne = new GeneralBase();
		gbOne.setGeneralId(12L);
		gbOne.setGeneralNumber("twelve");
		gbOne.setOperationalCountryCode("CHUL");
		return Arrays.asList(gbOne, new GeneralBase());
	}
	
	public GeneralBase getFromSQLServer() {
		log.error("{}|start", UtilMethods.getMethodName());
		return new GeneralBase();
	}
	
	public List<GeneralBase> getDataFromFeignRequest(HttpHeaders headers) {
		log.error("{}|start", UtilMethods.getMethodName());
		return appFeignProxy.getObjectListFromFeign(headers);
	}
	
}
