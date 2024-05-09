package ray.avi.example.utility;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.BooleanUtils;
import ray.avi.common.util.UtilMethods;

@XmlRootElement(name="featureFlag")
public class FeatureFlag {
	private Long ffftNameId;
	private String ffftName;
	private String ffftDescription;
	private Long ffftValueId;
	private Object ffftValue;
	private List<String> availableStringValues = new ArrayList<String>();
	private Boolean booleanValue;
	private Integer boolCheck;
	private String operationalCountryCode;
	private String alternateIdTypeCd;
	private Timestamp createDttm;
	private String createDbUserId;
	private String createAppUserId;
	private String createPrincipal;
	private String createProgram;
	private Timestamp lastUpdateDttm;
	private String lastUpdateDbUserId;
	private String lastUpdateAppUserId;
	private String lastUpdatePrincipal;
	private String lastUpdateProgram;
	private Boolean updateSelectedOnly;

	public FeatureFlag(){}
	
	public FeatureFlag(String ffftName, Object ffftValue){
		this(null, ffftName, null, null, ffftValue, null, null);
	}
	
	public FeatureFlag(Long ffftNameId, String ffftName, Long ffftValueId, Object ffftValue){
		this(ffftNameId, ffftName, null, ffftValueId, ffftValue, null, null);
	}
	
	public FeatureFlag(String ffftName, String ffftDescription, Object ffftValue){
		this(null, ffftName, ffftDescription, null,  ffftValue, null, null);
	}
	
	public FeatureFlag(Long ffftNameId, String ffftName, String ffftDescription, Long ffftValueId, Object ffftValue){
		this(ffftNameId, ffftName, ffftDescription, ffftValueId,  ffftValue, null, null);
	}
	
	public FeatureFlag(Long ffftNameId, String ffftName, String ffftDescription, Long ffftValueId, Object ffftValue, List<String> availableStringValues, Integer boolCheck){
		this.ffftNameId = ffftNameId;
		this.ffftName = ffftName;
		this.setFfftDescription(ffftDescription);
		this.ffftValueId = ffftValueId;
		this.ffftValue = ffftValue;
		this.availableStringValues = availableStringValues;
		this.boolCheck = boolCheck;
	}
	
	public FeatureFlag(FeatureFlag featureFlag){
		this.ffftNameId = featureFlag.getFfftNameId();
		this.ffftName = featureFlag.getFfftName();
		this.setFfftDescription(featureFlag.getFfftDescription());
		this.ffftValueId = featureFlag.getFfftValueId();
		this.ffftValue = featureFlag.getFfftValue();
		this.availableStringValues = featureFlag.getAvailableStringValues();
		this.boolCheck = featureFlag.getBoolCheck();
		this.operationalCountryCode = featureFlag.getOperationalCountryCode();
		this.alternateIdTypeCd = featureFlag.getAlternateIdTypeCd();
		this.createAppUserId = featureFlag.getCreateAppUserId();
		this.createDbUserId = featureFlag.getCreateDbUserId();
		this.lastUpdateAppUserId = featureFlag.getLastUpdateAppUserId();
		this.lastUpdateDbUserId = featureFlag.getLastUpdateDbUserId();
		this.createProgram = featureFlag.getCreateProgram();
		this.lastUpdateProgram = featureFlag.getLastUpdateProgram();
		this.createPrincipal = featureFlag.getCreatePrincipal();
		this.lastUpdatePrincipal = featureFlag.getLastUpdatePrincipal();
		this.updateSelectedOnly = featureFlag.getUpdateSelectedOnly();
	}

	public boolean checkBooleanType() {
		return availableStringValues == null || availableStringValues.size() == 0;
	}
	
	public boolean checkStringType() {
		return !checkBooleanType();
	}
	
	public Boolean retrieveBooleanValue() {
		try{
			return BooleanUtils.toBooleanObject(ffftValue.toString().trim().toLowerCase(), "true", "false", "null");
		}
		catch(NullPointerException npe){
			throw new RuntimeException(MessageFormat.format("{0}|Cannot get Boolean value of flag name {1}; value of flag name {2} is null", UtilMethods.getMethodName(), ffftName, ffftName));
		}
		catch(IllegalArgumentException iae){
			throw new RuntimeException(MessageFormat.format("{0}|Cannot get Boolean value of flag name {1}; value of flag name {2} is {3}, which is not a Boolean value", UtilMethods.getMethodName(), ffftName, ffftName, ffftValue));
		}
	}
	
	public String retrieveStringValue() {
		try{
			return ffftValue.toString().trim();
		}
		catch(NullPointerException npe){
			throw new RuntimeException(MessageFormat.format("{0}|Cannot get String value of flag name {1}; value of flag name {2} is null", UtilMethods.getMethodName(), ffftName, ffftName));
		}
	}

	public Long getFfftNameId() {
		return ffftNameId;
	}

	public void setFfftNameId(Long ffftNameId) {
		this.ffftNameId = ffftNameId;
	}

	public String getFfftName() {
		return ffftName;
	}

	public void setFfftName(String ffftName) {
		this.ffftName = ffftName;
	}

	public String getFfftDescription() {
		return ffftDescription;
	}

	public void setFfftDescription(String ffftDescription) {
		this.ffftDescription = ffftDescription;
	}

	public Long getFfftValueId() {
		return ffftValueId;
	}

	public void setFfftValueId(Long ffftValueId) {
		this.ffftValueId = ffftValueId;
	}

	public Object getFfftValue() {
		return ffftValue;
	}

	public void setFfftValue(Object ffftValue) {
		this.ffftValue = ffftValue;
	}

	public List<String> getAvailableStringValues() {
		return availableStringValues;
	}

	public void setAvailableStringValues(List<String> availableStringValues) {
		this.availableStringValues = availableStringValues;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	public Integer getBoolCheck() {
		return boolCheck;
	}

	public void setBoolCheck(Integer boolCheck) {
		this.boolCheck = boolCheck;
	}

	public String getOperationalCountryCode() {
		return operationalCountryCode;
	}

	public void setOperationalCountryCode(String operationalCountryCode) {
		this.operationalCountryCode = operationalCountryCode;
	}

	public String getAlternateIdTypeCd() {
		return alternateIdTypeCd;
	}

	public void setAlternateIdTypeCd(String alternateIdTypeCd) {
		this.alternateIdTypeCd = alternateIdTypeCd;
	}

	public Timestamp getCreateDttm() {
		return createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}

	public String getCreateDbUserId() {
		return createDbUserId;
	}

	public void setCreateDbUserId(String createDbUserId) {
		this.createDbUserId = createDbUserId;
	}

	public String getCreateAppUserId() {
		return createAppUserId;
	}

	public void setCreateAppUserId(String createAppUserId) {
		this.createAppUserId = createAppUserId;
	}

	public String getCreatePrincipal() {
		return createPrincipal;
	}

	public void setCreatePrincipal(String createPrincipal) {
		this.createPrincipal = createPrincipal;
	}

	public String getCreateProgram() {
		return createProgram;
	}

	public void setCreateProgram(String createProgram) {
		this.createProgram = createProgram;
	}

	public Timestamp getLastUpdateDttm() {
		return lastUpdateDttm;
	}

	public void setLastUpdateDttm(Timestamp lastUpdateDttm) {
		this.lastUpdateDttm = lastUpdateDttm;
	}

	public String getLastUpdateDbUserId() {
		return lastUpdateDbUserId;
	}

	public void setLastUpdateDbUserId(String lastUpdateDbUserId) {
		this.lastUpdateDbUserId = lastUpdateDbUserId;
	}

	public String getLastUpdateAppUserId() {
		return lastUpdateAppUserId;
	}

	public void setLastUpdateAppUserId(String lastUpdateAppUserId) {
		this.lastUpdateAppUserId = lastUpdateAppUserId;
	}

	public String getLastUpdatePrincipal() {
		return lastUpdatePrincipal;
	}

	public void setLastUpdatePrincipal(String lastUpdatePrincipal) {
		this.lastUpdatePrincipal = lastUpdatePrincipal;
	}

	public String getLastUpdateProgram() {
		return lastUpdateProgram;
	}

	public void setLastUpdateProgram(String lastUpdateProgram) {
		this.lastUpdateProgram = lastUpdateProgram;
	}

	public Boolean getUpdateSelectedOnly() {
		return updateSelectedOnly;
	}

	public void setUpdateSelectedOnly(Boolean updateSelectedOnly) {
		this.updateSelectedOnly = updateSelectedOnly;
	}

}
