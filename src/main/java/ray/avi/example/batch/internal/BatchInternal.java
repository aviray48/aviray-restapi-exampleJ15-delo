package ray.avi.example.batch.internal;

import org.springframework.context.annotation.Profile;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.util.UtilMethods;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Cipher;
import javax.xml.bind.JAXBElement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.mock.web.MockHttpServletRequest;
import lombok.NonNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;



@Profile("batchExampleSimpleBatch")
@Slf4j
public class BatchInternal {
	
	public BatchInternal(String[] args) {
		log.info("{}|BatchInternal Constructor", UtilMethods.getMethodName());
		executeBatch(args);
	}
	
	static DateFormat simpleDateFormatFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
	static java.time.format.DateTimeFormatter dateTimeFormatterFormatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");
	static Calendar simpleCalendar = Calendar.getInstance();
	static final String DATE_FORMAT = "MM/dd/yy HH:mm";
	static final ObjectMapper objectMapper = new ObjectMapper();
	static final Pattern EMPTY_SPACE_PATTERN = Pattern.compile("\\s");
	static final String customer0013447464JsonString =
			"{ \"id\": 0, \"createTimeStamp\": 1612887895075, \"createDBUserId\": \"CUSTRDBP\", \"createApplicationUserId\": \"cus-data-r\", \"createPrincipalName\": \"custdataRouterRUUSp\", \"createProgramName\": \"CustomerAccount-CreateCustomer\", \"lastUpdateTimeStamp\": 1633459645432, \"lastUpdateDBUserId\": \"CUSTACCP\", \"lastUpdateApplicationUserId\": \"COAST\", \"lastUpdatePrincipalName\": \"COAST\", \"lastUpdateProgramName\": \"CustomerAccount-Stratus-Update\", \"totalUpdateCount\": 7, \"replicationId\": 793549673, \"customerId\": 506475873, \"customerNumber\": \"0013447464\", \"operationalCountryCode\": \"US\", \"languageCode\": null, \"applicationCode\": null, \"applicationVersion\": null, \"mobilePhoneNumber\": null, \"homePhoneNumber\": \"6103248209\", \"workPhoneNumber\": null, \"insiderIndicator\": false, \"firstOrderTimeStamp\": null, \"firstOrderSalesDivision\": \"\", \"firstOrderApplicationCode\": null, \"firstOrderCategoryCode\": null, \"customerEstablishedTimeStamp\": 1612887894766, \"primaryCustomerStatusTypeCode\": \"DQ\", \"primaryCustomerStatusTypeLocalCode\": \"DQ\", \"primaryCustomerStatusTypeDescription\": \"Delinquency\", \"primaryCustomerStatusTimeStamp\": 1612887894766, \"customerStatuses\": [ { \"id\": 0, \"createTimeStamp\": null, \"createDBUserId\": null, \"createApplicationUserId\": null, \"createPrincipalName\": null, \"createProgramName\": null, \"lastUpdateTimeStamp\": null, \"lastUpdateDBUserId\": null, \"lastUpdateApplicationUserId\": null, \"lastUpdatePrincipalName\": null, \"lastUpdateProgramName\": null, \"totalUpdateCount\": 0, \"replicationId\": 0, \"customerId\": 0, \"customerNumber\": \"0013447464\", \"operationalCountryCode\": null, \"languageCode\": null, \"applicationCode\": null, \"applicationVersion\": null, \"statusCode\": \"DQ\", \"statusLocalCode\": null, \"statusDescription\": null, \"legacyPositionValue\": \"17\", \"rankNumber\": 4, \"closedIndicator\": false, \"statusEffectiveTimestamp\": 1612887894766, \"contactHistoryActionCode\": null, \"contactHistoryReasonCode\": 0, \"contactHistoryClarificationText\": null, \"acceptCreditCardOrdersIndicator\": true, \"acceptCheckOrdersIndicator\": true, \"returnPolicyCode\": \"N\", \"returnOverrideCode\": \"1\", \"deleteThisRecordDuringSave\": false, \"lastUpdateUserId\": null } ], \"lastTestimonialTimeStamp\": null, \"customerTypeCode\": \"00\", \"customerTypeLocalCode\": \"00\", \"customerTypeCodeDescription\": \"DEFAULT\", \"preferredLanguageCode\": \"en-us\", \"preferredLanguageCodeDescription\": \"en-us\", \"talkOnAirEligibleCode\": null, \"talkOnAirEligibleLocalCode\": null, \"talkOnAirEligibleCodeDescription\": null, \"preferredShipMethodCode\": null, \"preferredShipMethodCodeDescription\": null, \"determinedShipMethodCode\": null, \"determinedShipMethodCodeDescription\": null, \"creditCards\": null, \"customerAddressBookTypeCode\": null, \"fiftyPlusBuyerIndicator\": false, \"customerAddressBook\": null, \"permanentShipToAddressBookID\": 0, \"billingAddress\": { \"id\": 0, \"totalUpdateCount\": 0, \"replicationId\": 0, \"customerId\": 506475873, \"customerNumber\": \"0013447464\", \"operationalCountryCode\": \"US\", \"customerAddressId\": 0, \"firstName\": \"PLONI\", \"lastName\": \"ALMONI\", \"line1\": \"1200 WILSON DR\", \"cityName\": \"WEST CHESTER\", \"postalCode\": \"193804267\", \"countryCode\": \"US\", \"regionCode\": \"PA\", \"regionLocalCode\": \"Pennsylvania\", \"regionDescription\": \"Pennsylvania\", \"standardized\": false, \"poBoxIndicator\": false, \"creditCardVerificationRequired\": false, \"standardizationTimeStamp\": 1612846800000, \"standardizationActionTypeCode\": \"\" }, \"emailAddress\": null, \"preventMaintenanceIndicator\": false, \"permanentShipToAddress\": null, \"authentication\": null, \"eventToken\": null, \"closedIndicator\": false, \"birthDate\": null, \"ageOver18Indicator\": false, \"genderCode\": null, \"customerUpsellList\": null, \"statusCollection\": \"NNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNN\", \"lastShippedDate\": null, \"qvcCardCreditcardId\": 0, \"lastUsedCreditCardId\": 0, \"customerRanking\": null, \"purgedOrderReturnEligibilityIndicatory\": false, \"acceptCreditCardOrdersIndicator\": true, \"acceptCheckOrdersIndicator\": true, \"qvcCardCreditcardPreScreenIndicator\": false, \"restrictDelinquencyIndicator\": false, \"lastUpdateUserId\": \"COAST\" }"
			;

	static final String customerForCreateNoCustomerNumberJsonString =
			"{ \"id\": 0, \"createTimeStamp\": 1633463751677, \"createDBUserId\": \"CUSTRDBP\", \"createApplicationUserId\": \"00912175\", \"createPrincipalName\": \"custdataRouterRUUSp\", \"createProgramName\": \"CustomerAccount-CreateCustomer\", \"lastUpdateTimeStamp\": 1633463751677, \"lastUpdateDBUserId\": \"CUSTRDBP\", \"lastUpdateApplicationUserId\": \"00912175\", \"lastUpdatePrincipalName\": \"custdataRouterRUUSp\", \"lastUpdateProgramName\": \"CustomerAccount-CreateCustomer\", \"totalUpdateCount\": 0, \"replicationId\": 793562377, \"customerId\": 508192077, \"customerNumber\": \"\", \"operationalCountryCode\": \"us\", \"languageCode\": null, \"alternateIdTxt\": \"7ac46456-cf09-43d1-a0d6-982cff472d22\", \"alternateIdTypeCd\": \"EXTGUID\", \"lineOfBusiness\": \"qvc\", \"applicationCode\": null, \"applicationVersion\": null, \"mobilePhoneNumber\": null, \"homePhoneNumber\": \"6103248277\", \"workPhoneNumber\": null, \"insiderIndicator\": false, \"firstOrderTimeStamp\": null, \"firstOrderSalesDivision\": null, \"firstOrderApplicationCode\": null, \"firstOrderCategoryCode\": null, \"customerEstablishedTimeStamp\": 1633478151377, \"primaryCustomerStatusTypeCode\": \"\", \"primaryCustomerStatusTypeLocalCode\": \"\", \"primaryCustomerStatusTypeDescription\": \"\", \"primaryCustomerStatusTimeStamp\": null, \"customerStatuses\": [ { \"id\": 0, \"createTimeStamp\": null, \"createDBUserId\": null, \"createApplicationUserId\": null, \"createPrincipalName\": null, \"createProgramName\": null, \"lastUpdateTimeStamp\": null, \"lastUpdateDBUserId\": null, \"lastUpdateApplicationUserId\": null, \"lastUpdatePrincipalName\": null, \"lastUpdateProgramName\": null, \"totalUpdateCount\": 0, \"replicationId\": 0, \"customerId\": 0, \"customerNumber\": \"\", \"operationalCountryCode\": \"us\", \"lineOfBusiness\": \"qvc\", \"languageCode\": null, \"applicationCode\": null, \"applicationVersion\": null, \"legacyPositionValue\": \"0\", \"rankNumber\": 0, \"closedIndicator\": false, \"contactHistoryActionCode\": null, \"contactHistoryReasonCode\": 0, \"contactHistoryClarificationText\": null, \"acceptCreditCardOrdersIndicator\": true, \"acceptCheckOrdersIndicator\": true, \"lastUpdateUserId\": \"00912175\" } ], \"lastTestimonialTimeStamp\": null, \"customerTypeCode\": \"00\", \"customerTypeLocalCode\": \"00\", \"customerTypeCodeDescription\": \"DEFAULT\", \"preferredLanguageCode\": null, \"preferredLanguageCodeDescription\": null, \"talkOnAirEligibleCode\": null, \"talkOnAirEligibleLocalCode\": null, \"talkOnAirEligibleCodeDescription\": null, \"preferredShipMethodCode\": null, \"preferredShipMethodCodeDescription\": null, \"determinedShipMethodCode\": null, \"determinedShipMethodCodeDescription\": null, \"creditCards\": null, \"customerAddressBookTypeCode\": null, \"fiftyPlusBuyerIndicator\": false, \"customerAddressBook\": null, \"permanentShipToAddressBookID\": 0, \"billingAddress\": { \"id\": 0, \"totalUpdateCount\": 0, \"replicationId\": 0, \"customerId\": 508192077, \"customerNumber\": \"\", \"operationalCountryCode\": \"us\", \"customerAddressId\": 0, \"firstName\": \"Ploni\", \"lastName\": \"Almoni\", \"line1\": \"1277 Wilson Dr\", \"line2\": \"\", \"countyName\": \"\", \"cityName\": \"West Chester\", \"postalCode\": \"193804267\", \"countryCode\": \"US\", \"regionCode\": \"PA\", \"regionLocalCode\": \"Pennsylvania\", \"regionDescription\": \"Pennsylvania\", \"houseNumber\": \"1277\", \"unitNumber\": \"\", \"streetName\": \"Wilson\", \"standardized\": false, \"poBoxIndicator\": false, \"creditCardVerificationRequired\": false, \"standardizationTimeStamp\": 1633463751677 }, \"emailAddress\": null, \"preventMaintenanceIndicator\": false, \"permanentShipToAddress\": null, \"authentication\": null, \"eventToken\": null, \"closedIndicator\": false, \"birthDate\": null, \"ageOver18Indicator\": false, \"genderCode\": null, \"customerUpsellList\": null, \"statusCollection\": \"NNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNN\", \"lastShippedDate\": null, \"qvcCardCreditcardId\": 0, \"lastUsedCreditCardId\": 0, \"customerRanking\": null, \"purgedOrderReturnEligibilityIndicatory\": false, \"acceptCreditCardOrdersIndicator\": true, \"acceptCheckOrdersIndicator\": true, \"qvcCardCreditcardPreScreenIndicator\": false, \"restrictDelinquencyIndicator\": false, \"lastUpdateUserId\": \"00912175\" }"
			;
	
	static final String emailAddressJsonString =
			"{ \"id\": 0, \"createTimeStamp\": \"2023-01-24T23:59:22.981+00:00\", \"createDBUserId\": \"ice_de\", \"createApplicationUserId\": \"ice_de\", \"createPrincipalName\": \"custdataJmsListRUDE\", \"createProgramName\": \"customer-data-email-edb\", \"lastUpdateTimeStamp\": \"2023-01-24T23:59:22.981+00:00\", \"lastUpdateDBUserId\": \"ice_de\", \"lastUpdateApplicationUserId\": \"ice_de\", \"lastUpdatePrincipalName\": \"custdataJmsListRUDE\", \"lastUpdateProgramName\": \"customer-data-email-edb\", \"totalUpdateCount\": 0, \"replicationId\": 0, \"customerId\": 0, \"customerNumber\": \"10039564\", \"operationalCountryCode\": \"de\", \"lineOfBusiness\": \"qvc\", \"memberNumber\": \"10039564\", \"emailAddress\": \"pa20230123@test.com\", \"bounceCounter\": 0, \"bouncedFlag\": \"N\", \"sendEmailIndicator\": false, \"emailGUIDText\": \"1e2f943f-db74-43f8-8ba2-32358d59805e\", \"lastUpdateUserId\": \"ice_de\" }"
			;
	
	static final String LCADD = "LCADD";
	static final String LOC_ADD_CARGO = "LOC_ADD_CARGO";
	static final String RESULTS_ITEM = "Results_item";
	static final String RESULTS_ITEM_DC_INDV = "Results_item_DC_INDV";
	//static final String Results_item_DC_LOCMA = "Results_item_DC_LOCMA";
	static final String RESULTS_COLL = "Results_coll";
	static final String CLIENTID = "clientId";
	static final String NHSEQNUM = "nHSequenceNum";
	static final String ADMISSION_DATE = "admissionDate";
	static final String ADMISSION_FROM_TO = "admissionFromTo";
	
	//The method annotated with the @PostConstruct annotation is never run here, this this class is never actually built into a bean.
	//In order for the class to be built into a bean, it would need to be annotated with @Configuration or @Component or something similar.
	//The only reason it is here is because I can never remember the exact name of the @PostConstruct annotation or how it works,
	//so I put it here, where I will (hopefully) be able to find it easily.
	//@PostConstruct
	public void simpleCheckIfClassInstanceIsActuallyCreated() {
		System.out.println("simpleCheckIfClassInstanceIsActuallyCreated: Did we get here?");
	}

	private static String formattedValue(final String value) {
		return EMPTY_SPACE_PATTERN.matcher(value).replaceAll(StringUtils.EMPTY).toLowerCase();
	}

	private static Date getSpecificDate(int year, int month, int date, int hour, int minute, int second, int millisecond) {
		simpleCalendar.set(Calendar.YEAR, year);
		simpleCalendar.set(Calendar.MONTH, month - 1);
		simpleCalendar.set(Calendar.DAY_OF_MONTH, date);
		simpleCalendar.set(Calendar.HOUR_OF_DAY, hour);
		simpleCalendar.set(Calendar.MINUTE, minute);
		simpleCalendar.set(Calendar.SECOND, second);
		simpleCalendar.set(Calendar.MILLISECOND, millisecond);
		Date specificDate = simpleCalendar.getTime();
		simpleCalendar = Calendar.getInstance();
		return specificDate;
	}

	private static LocalDateTime convertDateToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	private static Date convertLocalDateTimeToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String maskValue(String value) {
		if (StringUtils.isBlank(value)) return "";
		int maskLength = value.trim().length();
		StringBuilder sbMaskString = new StringBuilder(maskLength);
		for(int i = 0; i < maskLength; i++){
			sbMaskString.append("*");
		}
		return sbMaskString.toString();
	}

	public static String testMethod(String string){
		return string;
	}

	@SuppressWarnings("null")
	public static void nullTestIA() {
		InetAddress ia = null;
		ia.getHostAddress();
		System.out.println("No Errors");
	}

	@SuppressWarnings("null")
	public static void nullTestString1() {
		String nullString1 = null;
		nullString1.length();
	}
	
	public static String testThrowErrorMethod(Integer param){
		if(param%7 == 0) {
			throw new RuntimeException("Intentionally Thrown Error, Error because 'param' is divisible by 7");
		}
		return param.toString();
	}

	public static String generateShortErrorMessageOrig(Throwable b){
		String outputString = null;
		//System.out.println(MessageFormat.format("Error while executing method: {0}, {1}", simpleClassName + "." + methodName, (b.getClass().getName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : ""))));
		//b.getClass().getName() + 
		outputString = b.getClass().getName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : "");

		return outputString;
	}

	public static String generateShortErrorMessage(Throwable b){
		return b.getClass().getName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : "");
	}

	private static String getOutputMessage(String outputMessageTemplate, Object... values){
		return MessageFormat.format(outputMessageTemplate, values);
	}

	private static String switchTest(String switchValue){
		String switchSetValue = null;
		switch (switchValue) {
		case "valueOne"://case "valueOne" flows into case "valueTwo":
		case "valueTwo":
			switchSetValue = "setValueOneTwo";
			break;
		case "valueThree":
			switchSetValue = "setValueThree";
			break;
		case "ExtraValueOne":
		case "ExtraValueTwo":
			switchSetValue = "setValueExtraOneTwo";
			break;
		default:
			log.info("The switchValue {} is not found, switchValue will be set to the default value", switchValue);
			switchSetValue = "defaultValue";
		}
		return switchSetValue;
	}

	private static final Map<Integer, Integer> squares;

	static{
		squares =   new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(2, 4);
				put(3, 9);
				put(4, 16);
			}
		};
	}

	public enum EnumErrorCode {
		GENERIC("99999"),
		UNKNOWN_PATH("10000"),
		REQUIRED_PARAMETER_VALUE_MISSING("10002"),
		PARAMETER_INVALID("10003"),
		PRODUCT_NOT_FOUND("10004"),
		DATA_NOT_FOUND("10005"),
		AUTHENTICATION_FAILURE("10006"),
		OPERATION_TIMEOUT("10007"),
		DATA_NOT_MODIFIED("10008"),
		NO_CONTENT_FOUND("10009");


		private String code;

		private EnumErrorCode(final String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}

		public static EnumErrorCode from(final String errorCode) {
			return Stream.of(EnumErrorCode.values()).filter(ec -> ec.toString().equals(errorCode)).findFirst().orElse(GENERIC);
		}

	}

	public static String extractDateElements(String strDate) {
		String year = null;
		String month = null;
		String day = null; 
		try	{
			if(strDate != null &&  !"".equals(strDate)) {
				strDate = strDate.substring(0,10);
				if(strDate != null)	{
					java.util.StringTokenizer st = null;
					st = new StringTokenizer(strDate, "-");
					year = st.nextToken();
					month = st.nextToken();
					day = st.nextToken();
				}
			}
			if(year == null || month == null || day == null) {
				//It should never get here if things worked correctly, so if it does get here, throw an error.
				throw new RuntimeException("Bad date for extractDateElements");
			}
		}
		catch(Exception e){
			log.error("Error in extractDateElements" , e);
		}
		return month + "/" + day + "/" + year;
	}

	public static void executeBatch(String[] args)
	{
		System.out.println("");
		System.out.println(new Date() + ": MyTask SimpleBatch START");
		log.info("{}|MyTask SimpleBatch START", UtilMethods.getMethodName());

		StringBuffer batchStringBuffer = null;
		batchStringBuffer = new StringBuffer();
		
		try
		{
			String hostname = InetAddress.getLocalHost().getHostName();
			System.out.println("");System.out.println("hostname: " + hostname);
		}
		catch (UnknownHostException ex)
		{
			System.out.println("Hostname can not be resolved");
		}

		String inputToTest = "";
		String regexString = "";
		regexString = "[^A-Za-z0-9_]";
		regexString = "^A-Za-z0-9_";
		regexString = "\\W+";
		regexString = ".*\\W+.*";
		regexString = "^.*[^a-zA-Z0-9].*$";

		Pattern containsSpecialCharacters = Pattern.compile(regexString.trim());

		inputToTest = "CR87QHB7JTRSD";
		if(containsSpecialCharacters.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " contains special characters");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not contain special characters");
		}

		inputToTest = "CR87QHB7JTRSD_";
		if(containsSpecialCharacters.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " contains special characters");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not contain special characters");
		}

		inputToTest = "CR87QHB7JTRSD@";
		if(containsSpecialCharacters.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " contains special characters");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not contain special characters");
		}

		regexString = "";
		regexString = "^(v3)";

		Pattern equalsV3 = Pattern.compile(regexString.trim(), Pattern.CASE_INSENSITIVE);

		inputToTest = "v3";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "v4";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "v2";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "v1";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "v0";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "qxz";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "v3a";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		inputToTest = "av3";
		if(equalsV3.matcher(inputToTest).matches()){
			System.out.println("Input: " + inputToTest + " equals v3");
		}
		else{
			System.out.println("Input: " + inputToTest + " does not equal v3");
		}

		String simpleClassName = null;
		String methodName = null;
		try{
			simpleClassName = "CatchClass";
			methodName = "catchMethod";
			nullTestIA();
		}
		catch(Throwable b){
			//System.out.println(simpleClassName+"."+methodName+" | "+(1000L) + "ms | Error while executing method: " + b.getMessage());
			//System.out.println(MessageFormat.format("{0} | {1}ms | Error while executing method: {2}", simpleClassName + "." + methodName, Long.toString((1234L)), b.getClass().getSimpleName() + ": " + b.getMessage()));
			System.out.println(MessageFormat.format("Error while executing method: {0}, {1}", simpleClassName + "." + methodName, (b.getClass().getSimpleName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : ""))));
			System.out.println(MessageFormat.format("Error while executing method: {0}, {1}", simpleClassName + "." + methodName, (b.getClass().getName() + (b.getMessage() != null ? ": " + b.getMessage().trim() : ""))));
			System.out.println(MessageFormat.format("Error while executing method: {0}, {1}", simpleClassName + "." + methodName, generateShortErrorMessage(b)));
		}

		String POSTAL_CODE_PERMISSIBLE_CHARACTERS = null;
		String formattedAccountMatchInfoPostalCode = null;
		String trimmedAccountMatchInfoPostalCode = null;

		//POSTAL_CODE_PERMISSIBLE_CHARACTERS = "^[^a-zA-Z0-9]$";
		//POSTAL_CODE_PERMISSIBLE_CHARACTERS = "^.*[^a-zA-Z0-9]$";
		//POSTAL_CODE_PERMISSIBLE_CHARACTERS = "^[^a-zA-Z0-9].*$";
		//POSTAL_CODE_PERMISSIBLE_CHARACTERS = "^.*[^a-zA-Z0-9].*$";

		//POSTAL_CODE_PERMISSIBLE_CHARACTERS = "^[a-zA-Z0-9]$";

		POSTAL_CODE_PERMISSIBLE_CHARACTERS = "[^a-zA-Z0-9]+";


		Pattern containsSpecialCharactersPostalCode = Pattern.compile(POSTAL_CODE_PERMISSIBLE_CHARACTERS);


		trimmedAccountMatchInfoPostalCode = "45678 1960";
		//trimmedAccountMatchInfoPostalCode = "45678-1960";
		formattedAccountMatchInfoPostalCode = containsSpecialCharactersPostalCode.matcher(trimmedAccountMatchInfoPostalCode).replaceAll("");
		System.out.println("formattedAccountMatchInfoPostalCode: " + formattedAccountMatchInfoPostalCode);



		String outputMessageTemplate = null;
		Object[] stringArr = null;

		outputMessageTemplate = "Partial Match: PayerID and {0} Matched, but Account associated with the PayerId has Status {1}";
		Object[] stringArrOne = {"Email Address", "DQ"};
		stringArr = stringArrOne;
		System.out.println(getOutputMessage(outputMessageTemplate, stringArr));


		outputMessageTemplate = "Partial Match: Email address and Street Address Matched, but Account associated with the email address has Status {0}";
		Object[] stringArrTwo = {"DQ"};
		stringArr = stringArrTwo;
		System.out.println(getOutputMessage(outputMessageTemplate, stringArr));

		@SuppressWarnings("serial")
		Map<Integer, String> idToName = new HashMap<Integer, String>() { {
			put(101, "John");
			put(102, "John");
			put(103, "John");
		}};

		System.out.println(idToName);
		System.out.println(squares);

		String originalInput, encodedString, decodedString;
		byte[] decodedBytes;

		originalInput = "comRU:comRU";
		System.out.println("Original Input: " + originalInput);
		encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("Encoded String: " + encodedString);
		System.out.println("Encoded String w/ Basic Auth: Authorization:Basic " + encodedString);
		decodedBytes = Base64.getDecoder().decode(encodedString);
		decodedString = new String(decodedBytes);
		System.out.println("Decoded String: " + decodedString);

		originalInput = ":";
		System.out.println("Original Input: " + originalInput);
		encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("Encoded String: " + encodedString);
		System.out.println("Encoded String w/ Basic Auth: Authorization:Basic " + encodedString);
		decodedBytes = Base64.getDecoder().decode(encodedString);
		decodedString = new String(decodedBytes);
		System.out.println("Decoded String: " + decodedString);

		originalInput = "00912175:MY_ACTUAL_PASSWORD";
		System.out.println("Original Input: " + originalInput);
		encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("Encoded String: " + encodedString);
		System.out.println("Encoded String w/ Basic Auth: Authorization:Basic " + encodedString);
		decodedBytes = Base64.getDecoder().decode(encodedString);
		decodedString = new String(decodedBytes);
		System.out.println("Decoded String: " + decodedString);

		try {
			int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
			System.out.println("Max AES key length = " + maxKeyLen);
		} catch (Exception e){
			System.out.println("FAILED: No AES found!");
		}

		Map<String,String> headerMap = new HashMap<String, String>();
		Boolean bool = Boolean.valueOf(headerMap.get("save-credit-card"));
		System.out.println("Boolean Value of 'bool' = " + bool);

		Date d = new Date();
		//Next Line throws NullPointerException
		//d.compareTo(null);
		//Next Line throws NullPointerException
		//(new Date()).compareTo(null);

		try {
			d.compareTo(null);
		}
		catch(Exception e) {
			System.out.println("Intentionally Thrown Error occurred:" + e.getMessage() + "\n");
			System.out.println("e.printStackTrace(); :");
			e.printStackTrace();
			System.out.println("\n");
			System.out.println("System.out.println(e.getStackTrace()); :");
			System.out.println(e.getStackTrace());
			System.out.println("\n");
			StackTraceElement[] stea = e.getStackTrace();
			String s = stea.toString();
			System.out.println("StackTraceElement[] stea = e.getStackTrace(); String s = stea.toString(); System.out.println(s); :");
			System.out.println(s);
			System.out.println("\n");
			System.out.println("List<StackTraceElement> steList... :");
			System.out.println(e.getClass().toString());
			List<StackTraceElement> steList = Arrays.asList(e.getStackTrace());
			for(StackTraceElement ste : steList) {
				System.out.println(ste.toString());
			}
			System.out.println("\n");
			log.error("Intentionally Thrown Error occurred:" + e.getMessage() + "\n", e);
		}

		String inputKey = "0000001718";
		String operationalCountryCode = "us";
		String spaceKeyFormat = "Customer%-{0}-{1}%";
		String key = MessageFormat.format(spaceKeyFormat, inputKey, operationalCountryCode.toUpperCase());
		System.out.println("Value of 'key' = " + key);


		List<String> stringList = null;
		String stringListJoinedtoString = null;

		stringList = new ArrayList<>();
		stringListJoinedtoString = String.join(", ", stringList);
		System.out.println("stringListJoinedtoString: " + stringListJoinedtoString);

		stringList = Arrays.asList("Larry", "Moe", "Curly");
		stringListJoinedtoString = String.join(", ", stringList);
		System.out.println("stringListJoinedtoString: " + stringListJoinedtoString);

		System.out.println("JSON Stringing Test");
		String customerNumber = null;
		String emailAddressString = null;
		String jsonStringingTestOutput = null;
		customerNumber = "0004834911";
		emailAddressString = "p.4a.0040@test.com";
		jsonStringingTestOutput = MessageFormat.format(" '{' \"customerNumber\":\"{0}\",\"emailAddress\": '{' \"emailAddress\":\"{1}\" '}' '}'", customerNumber, emailAddressString);
		System.out.println("Results of JSON Stringing Test: " + jsonStringingTestOutput);

		String isNullcheck;

		isNullcheck = null;
		log.info("If this value is not null, print the value, otherwise print ISNULL: {}", isNullcheck != null ? isNullcheck : "ISNULL");
		isNullcheck = "non-null-value";
		log.info("If this value is not null, print the value, otherwise print ISNULL: {}", isNullcheck != null ? isNullcheck : "ISNULL");

		isNullcheck = null;
		log.info("If this value is not null, print the value, otherwise print ISNULL: {}", Objects.nonNull(isNullcheck) ? isNullcheck : "ISNULL");
		isNullcheck = "non-null-value";
		log.info("If this value is not null, print the value, otherwise print ISNULL: {}", Objects.nonNull(isNullcheck) ? isNullcheck : "ISNULL");

		log.info("Optional.empty().isPresent() evaluates to: {}", Optional.empty().isPresent());

		System.out.println("");System.out.println("HttpHeaders.CONTENT_TYPE.toLowerCase(Locale.US): " + HttpHeaders.CONTENT_TYPE.toLowerCase(Locale.US));System.out.println("");

		Optional<Integer> op = null;
		Optional<Integer> optionalOfNullableOp = null;

		op = Optional.of(9455);
		System.out.println("Optional: " + op);
		try {
			System.out.println("Value by orElseThrow(ArithmeticException::new) method: " + op.orElseThrow(ArithmeticException::new));
		}
		catch (Exception e) {
			System.out.println(e);
		}

		op = Optional.empty();
		System.out.println("Optional: " + op);
		try {
			System.out.println("Value by orElseThrow(ArithmeticException::new) method: " + op.orElseThrow(ArithmeticException::new));
		}
		catch (Exception e) {
			System.out.println(e);
		}

		op = Optional.of(9455);
		System.out.println("Optional: " + op);
		try {
			optionalOfNullableOp = Optional.ofNullable(op.orElseThrow(ArithmeticException::new));
			System.out.println("Value of optionalOfNullableOp: " + optionalOfNullableOp);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		op = Optional.empty();
		System.out.println("Optional: " + op);
		try {
			optionalOfNullableOp = Optional.ofNullable(op.orElseThrow(ArithmeticException::new));
			System.out.println("Value of optionalOfNullableOp: " + optionalOfNullableOp);
		}
		catch (Exception e) {
			System.out.println(e);		
		}

		op = Optional.of(10);
		op
		.map(opi -> {
			try {
				return testThrowErrorMethod(opi);
			}
			catch(Exception e) {
				return null;
			}
		})
		.map(opii -> {
			log.info("Optional.of(opii) evaluates to: {}", Optional.of(opii));
			return Optional.of(opii);
		})
		.orElseGet(() -> {
			log.info("orElseGet evaluates to: {}", Optional.empty());
			return Optional.empty();
		})
		;

		op = Optional.of(14);
		op
		.map(opi -> {
			try {
				return testThrowErrorMethod(opi);
			}
			catch(Exception e) {
				return null;
			}
		})
		.map(opii -> {
			log.info("Optional.of(opii) evaluates to: {}", Optional.of(opii));
			return Optional.of(opii);
		})
		.orElseGet(() -> {
			log.info("orElseGet evaluates to: {}", Optional.empty());
			return Optional.empty();
		})
		;


		LinkedMultiValueMap<String, String> lmvm = null;
		lmvm = new LinkedMultiValueMap<String, String>();
		Map map = Collections.singletonMap("createEvenIfCustomerExists", Boolean.TRUE.toString());
		lmvm = new LinkedMultiValueMap<String, String>(map);
		Arrays.asList(Boolean.TRUE.toString());
		Map mapList = Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()));
		lmvm = new LinkedMultiValueMap<String, String>(mapList);
		HttpHeaders headers = new HttpHeaders();
		headers = new HttpHeaders();
		List<String> myList = Stream.of("a", "b").map(String::toUpperCase).collect(Collectors.toList());
		Stream<Map<?,?>> smmmm = Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).map(a -> new HashMap<>(a));
		Optional<Map<?,?>> mmm = smmmm.findFirst();
		new LinkedMultiValueMap<Object, Object>();
		Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).map(a -> new HashMap<>(a)).findFirst();
		Stream<LinkedMultiValueMap<?,?>> smmmmm = Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).map(a -> new LinkedMultiValueMap<>(a));
		Optional<LinkedMultiValueMap<?,?>> ommmm = smmmmm.findFirst();
		LinkedMultiValueMap<?,?> mmmm = ommmm.get();
		Map<Object, Object> mmum = (Map<Object, Object>) mmmm.toSingleValueMap();
		Stream<LinkedMultiValueMap<?,?>> szmmmmm = Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).map(a -> new LinkedMultiValueMap<>(a));
		Optional<LinkedMultiValueMap<?,?>> ozmmmm = szmmmmm.findFirst();
		LinkedMultiValueMap<?,?> zmmmm = ozmmmm.get();
		Map<Object, Object> zmmum = (Map<Object, Object>) zmmmm.toSingleValueMap();
		Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).map(a -> new LinkedMultiValueMap<>(a)).findFirst().get().toSingleValueMap();
		Stream<Map<String, List<String>>> omapZ = Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString())));
		Optional<Map<String, List<String>>> omap = Stream.of(Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))).findFirst();
		new LinkedMultiValueMap();
		Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()));
		new LinkedMultiValueMap(
				(Map<String,java.util.List<String>>)Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))
				);
		Map mvm = (
				new LinkedMultiValueMap(
						(Map<String,java.util.List<String>>)Collections.singletonMap("createEvenIfCustomerExists", Arrays.asList(Boolean.TRUE.toString()))
						)
				).toSingleValueMap();



		String genericEnum = null;
		genericEnum = EnumErrorCode.GENERIC.toString();
		log.info("genericEnum evaluates to: {}", genericEnum);


		String switchValue = null;
		switchValue = "valueOne";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));
		switchValue = "valueTwo";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));
		switchValue = "valueThree";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));
		switchValue = "ExtraValueOne";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));
		switchValue = "ExtraValueTwo";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));
		switchValue = "Other";
		log.info("switchTest({}): {}", switchValue, switchTest(switchValue));

		String zeroString = "0";
		String oneString = "1";
		boolean pbZero = Boolean.parseBoolean(zeroString);
		log.info("Boolean.parseBoolean(zeroString): {}", pbZero);
		boolean pbOne = Boolean.parseBoolean(oneString);
		log.info("Boolean.parseBoolean(oneString): {}", pbOne);

		int zero = 0;
		int one = 1;
		boolean pbZeroInt = Boolean.parseBoolean(Integer.toString(zero));
		log.info("Boolean.parseBoolean(Integer.toString(zero)): {}", pbZeroInt);
		boolean pbOneInt = Boolean.parseBoolean(Integer.toString(one));
		log.info("Boolean.parseBoolean(Integer.toString(one)): {}", pbOneInt);

		String blankString = "";
		boolean pbBlankString = Boolean.parseBoolean(blankString);
		log.info("Boolean.parseBoolean(blankString): {}", pbBlankString);

		String nullString = null;
		boolean pbNullString = Boolean.parseBoolean(nullString);
		log.info("Boolean.parseBoolean(nullString): {}", pbNullString);

		try {
			java.net.InetAddress inetHost = null;
			inetHost = java.net.InetAddress.getByName("google.com");
			System.out.println("The host name was: " + inetHost.getHostName());
			System.out.println("The hosts IP address is: " + inetHost.getHostAddress());
			System.out.println();
			//inetHost = java.net.InetAddress.getByName("_couchbases._tcp.cbeq01q.qvcdev.qvc.net");
			//inetHost = java.net.InetAddress.getByName("cbeq01q.qvcdev.qvc.net");
			inetHost = java.net.InetAddress.getByName("hcb001eq01q.qvcdev.qvc.net");
			System.out.println("The host name was: " + inetHost.getHostName());
			System.out.println("The hosts IP address is: " + inetHost.getHostAddress());
		}
		catch(java.net.UnknownHostException ex) {
			System.out.println("Unrecognized host");
		}

		String zipCodeTestValue = null;
		String zipCodePMValue = null;
		try {
			zipCodeTestValue = null;
			Matcher matcher = EMPTY_SPACE_PATTERN.matcher(zipCodeTestValue);
			System.out.println("The matcher Value is: " + matcher);
		}
		catch(Exception e) {
			log.error("Intentionally Thrown Error occurred:" + e.getMessage() + "\n", e);
			log.error("\n");
		}

		try {
			zipCodeTestValue = null;
			System.out.println("The Zip Code Value is: " + zipCodeTestValue);
			zipCodePMValue = EMPTY_SPACE_PATTERN.matcher(zipCodeTestValue).replaceAll(StringUtils.EMPTY).toLowerCase();
			System.out.println("The Zip Code PM Value is: " + zipCodePMValue);
		}
		catch(Exception e) {
			log.error("Intentionally Thrown Error occurred:" + e.getMessage() + "\n");
		}

		try {
			zipCodeTestValue = "123";
			System.out.println("The Zip Code Value is: " + zipCodeTestValue);
			zipCodePMValue = EMPTY_SPACE_PATTERN.matcher(zipCodeTestValue).replaceAll(StringUtils.EMPTY).toLowerCase();
			System.out.println("The Zip Code PM Value is: " + zipCodePMValue);
		}
		catch(Exception e) {
			log.error("Error occurred:" + e.getMessage() + "\n");
		}

		try {
			zipCodeTestValue = "4";
			System.out.println("The Zip Code Value is: " + zipCodeTestValue);
			zipCodePMValue = EMPTY_SPACE_PATTERN.matcher(zipCodeTestValue).replaceAll(StringUtils.EMPTY).toLowerCase();
			System.out.println("The Zip Code PM Value is: " + zipCodePMValue);
		}
		catch(Exception e) {
			log.error("Error occurred:" + e.getMessage() + "\n");
		}


		String postalCodeValue = null;
		int defaultBillingPostalCodeMin = 0;
		int defaultBillingPostalCodeMax = 5;
		String postalCodeFValue = null;
		String postalCodeFValueSubstring = null;

		try {
			postalCodeValue = "4";
			postalCodeFValue = null;
			postalCodeFValueSubstring = null;
			System.out.println("The Postal Code Value is: " + postalCodeValue);
			postalCodeFValue = formattedValue(postalCodeValue);
			postalCodeFValueSubstring = postalCodeFValue.substring(defaultBillingPostalCodeMin, defaultBillingPostalCodeMax);
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Intentionally Thrown Error, Invalid postalCode:" + e.getMessage() + "\n", e);
			log.error("\n");
		}
		System.out.println("The Postal Code Formatted Value is: " + postalCodeFValue);
		System.out.println("The Postal Code Formatted Value Substring is: " + postalCodeFValueSubstring);

		try {
			postalCodeValue = "D04";
			postalCodeFValue = null;
			postalCodeFValueSubstring = null;
			System.out.println("The Postal Code Value is: " + postalCodeValue);
			postalCodeFValue = formattedValue(postalCodeValue);
			postalCodeFValueSubstring = postalCodeFValue.substring(defaultBillingPostalCodeMin, defaultBillingPostalCodeMax);
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Intentionally Thrown Error, Invalid postalCode:" + e.getMessage() + "\n", e);
			log.error("\n");
		}
		System.out.println("The Postal Code Formatted Value is: " + postalCodeFValue);
		System.out.println("The Postal Code Formatted Value Substring is: " + postalCodeFValueSubstring);

		try {
			postalCodeValue = "D0412";
			postalCodeFValue = null;
			postalCodeFValueSubstring = null;
			System.out.println("The Postal Code Value is: " + postalCodeValue);
			postalCodeFValue = formattedValue(postalCodeValue);
			postalCodeFValueSubstring = postalCodeFValue.substring(defaultBillingPostalCodeMin, defaultBillingPostalCodeMax);
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Invalid postalCode:" + e.getMessage() + "\n", e);
			log.error("\n");
		}
		System.out.println("The Postal Code Formatted Value is: " + postalCodeFValue);
		System.out.println("The Postal Code Formatted Value Substring is: " + postalCodeFValueSubstring);

		try {
			postalCodeValue = "4_D04";
			postalCodeFValue = null;
			postalCodeFValueSubstring = null;
			System.out.println("The Postal Code Value is: " + postalCodeValue);
			postalCodeFValue = formattedValue(postalCodeValue);
			postalCodeFValueSubstring = postalCodeFValue.substring(defaultBillingPostalCodeMin, defaultBillingPostalCodeMax);
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Invalid postalCode:" + e.getMessage() + "\n", e);
			log.error("\n");
		}
		System.out.println("The Postal Code Formatted Value is: " + postalCodeFValue);
		System.out.println("The Postal Code Formatted Value Substring is: " + postalCodeFValueSubstring);

		Charset chst = StandardCharsets.UTF_8;
		String chstNm = chst.name();
		chstNm = StandardCharsets.UTF_8.name();

		log.info("Name of the 'StandardCharsets.UTF_8' Charset is: {}", chstNm);
		System.out.println("Name of the 'StandardCharsets.UTF_8' Charset is: " + chstNm);

		String valueToMask = null;

		try {
			valueToMask = null;
			System.out.println("The Masked Value is: " + maskValue(valueToMask));
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Erorr in Masking:" + e.getMessage() + "\n", e);
			log.error("\n");
		}

		try {
			valueToMask = "";
			System.out.println("The Masked Value is: " + maskValue(valueToMask));
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Erorr in Masking:" + e.getMessage() + "\n", e);
			log.error("\n");
		}

		try {
			valueToMask = "   ";
			System.out.println("The Masked Value is: " + maskValue(valueToMask));
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Erorr in Masking:" + e.getMessage() + "\n", e);
			log.error("\n");
		}

		try {
			valueToMask = " Some Value ";
			System.out.println("The Masked Value is: " + maskValue(valueToMask));
		}
		catch (Exception e) {
			if(postalCodeFValue == null) {
				postalCodeFValue = "ERROR";
			}
			if(postalCodeFValueSubstring == null) {
				postalCodeFValueSubstring = "ERROR";
			}
			log.error("Erorr in Masking:" + e.getMessage() + "\n", e);
			log.error("\n");
		}

		int intA;
		int intB;
		intA = 0;
		intB = 5;
		int minBetweenIntAnIntB = Math.min(intA, intB);
		log.info("The minimum value between {} and {} is: {}", intA, intB, minBetweenIntAnIntB);		

		String javaClassPath = System.getProperty("java.class.path");
		System.out.println("javaClassPath = " + javaClassPath);

		String javaVariablePassedInFromVMArgumentsInEclipse = System.getenv("VM_ARGUMENTS_VARIABLE");
		System.out.println("javaVariablePassedInFromVMArgumentsInEclipse VALUE: " + (javaVariablePassedInFromVMArgumentsInEclipse != null ? javaVariablePassedInFromVMArgumentsInEclipse : "VALUE IS NULL"));
		

		String javaVariablePassedInFromRunConfigurationEnvironmentInEclipse = System.getenv("ENVIRONMENT_VARIABLE");
		System.out.println("javaVariablePassedInFromRunConfigurationEnvironmentInEclipse VALUE: " + (javaVariablePassedInFromRunConfigurationEnvironmentInEclipse != null ? javaVariablePassedInFromRunConfigurationEnvironmentInEclipse : "VALUE IS NULL"));	
		
		String valMessage1 = null;
		String valMessage2 = null;
		String valMessage3 = null;

		valMessage1 = null;
		valMessage2 = null;
		valMessage3 = null;

		try {
			throw new RuntimeException("Thrown Intentionally");
		}
		catch(Exception e) {
			valMessage1 = "Message One";
			valMessage2 = "Message Two";
			valMessage3 = "Message Three";
			String messageOutput = MessageFormat.format("Could not retrieve a valid postal code, Error Messages: {0}, {1}, {2}", valMessage1, valMessage2, valMessage3);
			log.info(messageOutput);
			log.error(MessageFormat.format("Could not retrieve a valid postal code, Error Messages: {0}, {1}, {2}, {3}", e.getMessage(), valMessage1, valMessage2, valMessage3), e);
		}

		valMessage1 = null;
		valMessage2 = null;
		valMessage3 = null;

		try {
			testThrowErrorMethod(7);
		}
		catch(Exception e) {
			valMessage1 = "Message One";
			valMessage2 = "Message Two";
			valMessage3 = "Message Three";
			log.error(MessageFormat.format("Could not retrieve a valid postal code, Error Messages: {0}, {1}, {2}, {3}", e.getMessage(), valMessage1, valMessage2, valMessage3), e);
		}

		valMessage1 = null;
		valMessage2 = null;
		valMessage3 = null;

		try {
			nullTestString1();
		}
		catch(Exception e) {
			valMessage1 = "Message One";
			valMessage2 = "Message Two";
			valMessage3 = "Message Three";
			log.error(MessageFormat.format("Intentionally Thrown Error, Could not retrieve a valid postal code, Error Messages: {0}, {1}, {2}, {3}", e.getMessage(), valMessage1, valMessage2, valMessage3), e);
		}


		SimpleDateFormat dateFormatterOne = new SimpleDateFormat("mmddyy");
		Date dateUnderTestOne = null;
		String dateUnderTestOneFormatted = null;
		try {
			dateUnderTestOne = new Date();
			log.info("dateUnderTestOne: " + dateUnderTestOne);
			dateUnderTestOneFormatted = dateFormatterOne.format(dateUnderTestOne);
			System.out.println("dateUnderTestOneFormatted: " + dateUnderTestOneFormatted);System.out.println();
		}
		catch(Exception e) {
			log.error("Could not generate a valid Date in String format for dateUnderTestOne: " + dateUnderTestOne );System.out.println();
		}
		try {
			dateUnderTestOne = null;
			log.info("dateUnderTestOne: " + dateUnderTestOne);
			dateUnderTestOneFormatted = dateFormatterOne.format(dateUnderTestOne);
			System.out.println("dateUnderTestOneFormatted: " + dateUnderTestOneFormatted);System.out.println();
		}
		catch(Exception e) {
			log.error("Could not generate a valid Date in String format for dateUnderTestOne: " + dateUnderTestOne );System.out.println();
		}


		Object objectUnderTest = null;
		final String customerNumberOne = "0094834911";
		try {
			objectUnderTest = squares;
			System.out.println("Optional.ofNullable(squares).isPresent(): " + Optional.ofNullable(objectUnderTest).isPresent());System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}
		try {
			objectUnderTest = squares.get(99);
			System.out.println("Optional.ofNullable(squares.get(99)).isPresent(): " + Optional.ofNullable(objectUnderTest).isPresent());System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}
		try {
			objectUnderTest = dateFormatterOne.format(squares.get(99));
			System.out.println("Optional.ofNullable(dateFormatterOne.format(squares.get(99))).isPresent(): " + Optional.ofNullable(objectUnderTest).orElse("DATE WAS NULL"));System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}
		try {
			objectUnderTest = squares.get(99);
			System.out.println("dateFormatterOne.format(Optional.ofNullable(squares.get(99)).orElse(EMPTY_STRING)): " + dateFormatterOne.format(Optional.ofNullable(objectUnderTest).orElse("DATE WAS NULL")));System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}
		try {
			objectUnderTest = squares.get(99);
			System.out.println("Optional.ofNullable(squares.get(99)).map(bdate -> dateFormatterOne.format(bdate)).orElse(EMPTY_STRING): " + Optional.ofNullable(squares.get(99)).map(bdate -> dateFormatterOne.format(bdate)).orElse("DATE WAS NULL"));System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}
		try {
			objectUnderTest = squares.get(99);
			//Optional.ofNullable(squares.get(99)).map(bdate -> dateFormatterOne.format(bdate)).orElseGet(() -> {log.info(MessageFormat.format("Birthdate for Customer Number: {0} is null or blank", customerNumberOne));return "NOT_A_DATE";});
			System.out.println("Optional.ofNullable... See Line Commented Out Above: " + Optional.ofNullable(squares.get(99)).map(bdate -> dateFormatterOne.format(bdate)).orElseGet(() -> {log.info(MessageFormat.format("Birthdate for Customer Number: {0} is null or blank", customerNumberOne));return "NOT_A_DATE";}));System.out.println();
		}
		catch(Exception e) {
			log.error("Error Occurred");System.out.println();
		}

		try {
			Optional.ofNullable(MessageFormat.format("Customer Number is: {0}, five character code is: {1}", customerNumber, "12345")).ifPresent(null);
			log.info("Worked");System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error Occurred for ifPresent, Error Message: {0}", e.getMessage()), e);System.out.println();
		}

		try {
			Optional.ofNullable(MessageFormat.format("Customer Number is: {0}, five character code is: {1}", customerNumber, "12345")).ifPresent(infoMessage -> log.info("{}", infoMessage));
			log.info("Worked");System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error Occurred for ifPresent, Error Message: {0}", e.getMessage()), e);System.out.println();
		}
		

		Arrays.asList(MessageFormat.format("Customer Number is: {0}, five character code is: {1}", customerNumber, "98765").split(System.getProperty("line.separator"))).stream().findFirst().ifPresent(infoMessage -> log.info("{}", infoMessage));System.out.println();
		
		try {
			Arrays.asList(MessageFormat.format("AVAST YE MATEYS, THAR BE A GRAVE MISHAP, ARRR!!! Customer Number is: {0}, five character code is: {1}", customerNumber, "53791").split(System.getProperty("line.separator"))).stream().findFirst()
				.ifPresent(infoMessage -> {
					log.error("{}", infoMessage);
					throw new RuntimeException("Intentionally Thrown Exception");
				});
			log.info("Should not ever get here.");System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error intentionally thrown, Error Message: {0}", e.getMessage()), e);System.out.println();
		}

		//Arrays.asList(TimeZone.getAvailableIDs()).stream().forEach(System.out::println);System.out.println();
		//Arrays.asList(TimeZone.getAvailableIDs()).stream().forEach(logger::info);System.out.println();
		TimeZone tz = TimeZone.getTimeZone("Pacific/Easter");
		//log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {}", tz.getID(), tz.getDisplayName(), tz.getRawOffset());System.out.println();
		//log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {}, Daylight: {}", tz.getID(), tz.getDisplayName(), tz.getRawOffset(), tz.getDSTSavings());System.out.println();
		//log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {} minutes", tz.getID(), tz.getDisplayName(), TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset() + (tz.inDaylightTime(new Date()) ? tz.getDSTSavings() : 0)));System.out.println();
		//log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {} hours", tz.getID(), tz.getDisplayName(), TimeUnit.MILLISECONDS.toHours(tz.getRawOffset() + (tz.inDaylightTime(new Date()) ? tz.getDSTSavings() : 0)));System.out.println();

		tz = TimeZone.getTimeZone("Etc/GMT");
		log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {} hours", tz.getID(), tz.getDisplayName(), TimeUnit.MILLISECONDS.toHours(tz.getRawOffset() + (tz.inDaylightTime(new Date()) ? tz.getDSTSavings() : 0)));System.out.println();
		tz = TimeZone.getTimeZone("Europe/Berlin");
		log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {} hours", tz.getID(), tz.getDisplayName(), TimeUnit.MILLISECONDS.toHours(tz.getRawOffset() + (tz.inDaylightTime(new Date()) ? tz.getDSTSavings() : 0)));System.out.println();
		tz = TimeZone.getTimeZone("US/Eastern");
		log.info("For Time-Zone: {}, Display Name is: {}, Offset is: {} hours", tz.getID(), tz.getDisplayName(), TimeUnit.MILLISECONDS.toHours(tz.getRawOffset() + (tz.inDaylightTime(new Date()) ? tz.getDSTSavings() : 0)));System.out.println();

		Date specificDate = getSpecificDate(2022, 9, 23, 16, 10, 10, 10);
		specificDate = getSpecificDate(2019, 12, 23, 15, 50, 50, 50);
		log.info("The specificDate is: {}", simpleDateFormatFormatter.format(specificDate));System.out.println();
		specificDate = getSpecificDate(2005, 10, 25, 23, 10, 10, 10);
		log.info("The specificDate is: {}", simpleDateFormatFormatter.format(specificDate));System.out.println();

		specificDate = getSpecificDate(2022, 9, 23, 16, 10, 10, 10);
		LocalDateTime specificLocalDateTime = convertDateToLocalDateTimeViaInstant(specificDate);
		LocalDateTime specificLocalDateTimeLessTwoSeconds = specificLocalDateTime.minusSeconds(2);
		Date specificDateLessTwoSeconds = convertLocalDateTimeToDateViaInstant(specificLocalDateTimeLessTwoSeconds);

		log.info("The specificDate is: {}", simpleDateFormatFormatter.format(specificDate));System.out.println();
		log.info("The specificLocalDateTime is: {}", specificLocalDateTime.format(dateTimeFormatterFormatter));System.out.println();
		log.info("The specificLocalDateTimeLessTwoSeconds is: {}", specificLocalDateTimeLessTwoSeconds.format(dateTimeFormatterFormatter));System.out.println();
		log.info("The specificLocalDateTimeLessTwoSeconds is before specificLocalDateTime: {}", specificLocalDateTimeLessTwoSeconds.isBefore(specificLocalDateTime));System.out.println();
		log.info("The specificDateLessTwoSeconds is: {}", simpleDateFormatFormatter.format(specificDateLessTwoSeconds));System.out.println();
		log.info("The specificDateLessTwoSeconds is before specificDate: {}", specificDateLessTwoSeconds.before(specificDate));System.out.println();

		Calendar currentDate = null;
		currentDate = new Calendar.Builder().setDate(2014, 0, 1).build();
		Calendar cal = null;
		cal = new Calendar.Builder().setDate(2014, 0, 1).build();
		cal = new Calendar.Builder().setInstant(new Date()).build();
		Date dd = null;
		dd = new Date();
		Instant inst = null;
		inst = new Date().toInstant().plus(1, java.time.temporal.ChronoUnit.DAYS);
		inst = Instant.now().plus(1, java.time.temporal.ChronoUnit.DAYS);
		dd = Date.from(inst);

		String java_p_class_p_path = System.getProperty("java.class.path");
		System.out.println("");
		System.out.println(new Date() + ": java.class.path: " + System.getProperty("line.separator") + java_p_class_p_path);
		System.out.println("");
		
		ServletRequest servletRequest = null;
		java.sql.Timestamp dateObject = null;
		String javaSqlTimestampString = null;
		String javaSqlTimestampStringAfterExtractData = null;
		try {
			servletRequest = new HttpServletRequestWrapper(null);
			log.info("Should not ever get here.");System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error intentionally thrown, Error Message: {0}", e.getMessage()), e);System.out.println();
		}
		
		servletRequest = new MockHttpServletRequest();
		((MockHttpServletRequest) servletRequest).setParameter("firstName", "Ploni");
		((MockHttpServletRequest) servletRequest).setParameter("lastName", "Almoni");
		dateObject = new java.sql.Timestamp(Long.parseLong(Long.toString(new Date().getTime())));
		((MockHttpServletRequest) servletRequest).setAttribute("dateReq", dateObject);
		javaSqlTimestampString = ((java.sql.Timestamp)servletRequest.getAttribute("dateReq")).toString();
		log.info("The value of javaSqlTimestampString is: {}", javaSqlTimestampString);System.out.println();
		javaSqlTimestampStringAfterExtractData = extractDateElements(javaSqlTimestampString);
		log.info("The value of javaSqlTimestampStringAfterExtractData is: {}", javaSqlTimestampStringAfterExtractData);System.out.println();
		
		String aYOrNStr = null;
		byte byteValue = -128;
		
		aYOrNStr = "Y";
		byteValue = (byte) ("Y".equalsIgnoreCase(aYOrNStr) ? 0 : 1);
		log.info("For value of aYOrNStr {}, the value of byteValue is: {}", aYOrNStr, byteValue);System.out.println();
		
		aYOrNStr = "N";
		byteValue = (byte) ("Y".equalsIgnoreCase(aYOrNStr) ? 0 : 1);
		log.info("For value of aYOrNStr {}, the value of byteValue is: {}", aYOrNStr, byteValue);System.out.println();
		
		String yesOrNoString;
		boolean yesOrNoStringIsY;
		String yesOrNoStringIsYOutput = null;
		yesOrNoString = null;
		yesOrNoStringIsY = false;
		yesOrNoStringIsY = "Y".equalsIgnoreCase(yesOrNoString);
		log.info("The value of yesOrNoStringIsY is now: {}", yesOrNoStringIsY);System.out.println();
		yesOrNoStringIsYOutput = "Y".equalsIgnoreCase(yesOrNoString) ? " disabled = 'disabled' " : "";
		log.info("The value of yesOrNoStringIsYOutput is now: {}", yesOrNoStringIsYOutput);System.out.println();
		yesOrNoString = "Y";
		yesOrNoStringIsYOutput = "Y".equalsIgnoreCase(yesOrNoString) ? " disabled = 'disabled' " : "";
		log.info("The value of yesOrNoStringIsYOutput is now: {}", yesOrNoStringIsYOutput);System.out.println();
		
		Map<String, Object> request = null;//(Map<String, Object>)
		request = new HashMap<String, Object>();
		Optional<String> opString = Optional.ofNullable(((String)request.get(ADMISSION_FROM_TO)));
		opString.isPresent();
		log.info("The value of opString.isPresent() is now: {}", opString.isPresent());System.out.println();
		opString.map(reqString -> String.valueOf(reqString)).orElse(null);
		String admissionFromToStringFromOp = opString.map(reqString -> String.valueOf(reqString)).orElse(null);
		log.info("The value of admissionFromToStringFromOp is now: {}", admissionFromToStringFromOp);System.out.println();
		Optional.ofNullable(((String)request.get(ADMISSION_FROM_TO))).map(reqString -> String.valueOf(reqString)).orElse(null);
		log.info("The value of OptionalofNullableEtc is now: {}", Optional.ofNullable(((String)request.get(ADMISSION_FROM_TO))).map(reqString -> String.valueOf(reqString)).orElse(null));System.out.println();
		request.put(ADMISSION_FROM_TO, "HOME");
		log.info("The value of OptionalofNullableEtc is now: {}", Optional.ofNullable(((String)request.get(ADMISSION_FROM_TO))).map(reqString -> String.valueOf(reqString)).orElse(null));System.out.println();
		
		String caseWorkerProcessedYESNOStrIsYOutputSubmitAndProcessButton = "zzzz";
		String buttonText = "ddddd";
		StringBuffer sb = null;
		sb = new StringBuffer(caseWorkerProcessedYESNOStrIsYOutputSubmitAndProcessButton
				+ "onclick=\"javascript:"
				+ "form1.SELECTED_INDV_ID.value='" + request.toString() + "';"
				+ "form1.NURSING_HOME_SEQUENCE_NUM.value='" + String.valueOf(request.toString()) + "';"
				+ "form1.PROCESSED_BY.value='" + admissionFromToStringFromOp + "';"
				+ "form1.ADDITIONAL_COMMENTS_TO_BE_PROCESSED.value='" + admissionFromToStringFromOp + "';"
				+ "form1.zzzzzzzzzzzz.value='" + admissionFromToStringFromOp + "';"
				+ "setActionFieldAndSubmit(document.form1,'" + buttonText + "','N');return false;\" ")
		;
		char genderChar = 0;// the char value of '\u0000' is equivalent to the char with value of zero
		String genderInputString = null;
		String genderOutputString = null;
		genderInputString = null;
		genderOutputString = Optional.ofNullable(genderInputString).filter(genderCharStringhl -> genderCharStringhl.length() > 0).map(genderCharString -> String.valueOf(genderCharString)).orElse("Unknown");
		log.info("The value of genderOutputString is now: {}", genderOutputString);System.out.println();
		genderInputString = "";
		genderOutputString = Optional.ofNullable(genderInputString).filter(genderCharStringhl -> genderCharStringhl.length() > 0).map(genderCharString -> String.valueOf(genderCharString)).orElse("Unknown");
		log.info("The value of genderOutputString is now: {}", genderOutputString);System.out.println();
		genderInputString = Character.toString(genderChar);
		genderInputString = genderChar != '\u0000' ? Character.toString(genderChar) : "";
		genderOutputString = Optional.ofNullable(genderInputString).filter(genderCharStringhl -> genderCharStringhl.length() > 0).map(genderCharString -> String.valueOf(genderCharString)).orElse("Unknown");
		log.info("The value of genderOutputString is now: {}", genderOutputString);System.out.println();
		genderChar = 'M';
		genderInputString = Character.toString(genderChar);
		genderInputString = genderChar != '\u0000' ? Character.toString(genderChar) : "";
		genderOutputString = Optional.ofNullable(genderInputString).filter(genderCharStringhl -> genderCharStringhl.length() > 0).map(genderCharString -> String.valueOf(genderCharString)).orElse("Unknown");
		log.info("The value of genderOutputString is now: {}", genderOutputString);System.out.println();
		
		log.info("The value of this byte is now: {}", Byte.parseByte("1"));System.out.println();
		
		log.info("The value of this byte is now: {}", new Byte((byte) 0));System.out.println();
		
		String[] stringArrOneTwoThree = {};
      	Object[] objArr = {"One", "Two", "Three"};
		stringArrOneTwoThree = new String[]{"One", "Two", "Three"};
		String stringValue = String.join(", ", stringArrOneTwoThree);
		log.info("The value of stringValue is now: {}", stringValue);System.out.println();
		
		String[] stringArray = null;
		List<String> stringListToFromArray = null;
		stringListToFromArray = new ArrayList<String>();
		stringListToFromArray.add("UNIX");
		stringListToFromArray.add("WINDOWS");
		stringArray = stringListToFromArray.toArray(new String[0]);
		log.info("The value of stringArray is now: {}", String.join(", ", stringArray));System.out.println();
		stringArray = new String[]{"One", "Two", "Three"};
		stringListToFromArray = Arrays.asList(stringArray);
		log.info("The value of stringListToFromArray is now: {}", stringListToFromArray);System.out.println();
		
		request = new HashMap<String, Object>();
		request.put("certainSeqNum", (long) 332211);
		long certainSeqNum = 0;
		try {
			certainSeqNum = Long.parseLong((String) request.get("certainSeqNum"));
			log.info("Worked for 'Long.parseLong((String) request.get(\"certainSeqNum\"))', value of certainSeqNum is: {}", certainSeqNum);System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error Occurred for 'Long.parseLong((String) request.get(\"certainSeqNum\"))', Error Message: {0}", e.getMessage()), e);System.out.println();
		}
		try {
			certainSeqNum = Long.parseLong(String.valueOf(request.get("certainSeqNum")));
			log.info("Worked for 'Long.parseLong(String.valueOf(request.get(\"certainSeqNum\")))', value of certainSeqNum is: {}", certainSeqNum);System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Error Occurred for 'Long.parseLong(String.valueOf(request.get(\"certainSeqNum\")))', Error Message: {0}", e.getMessage()), e);System.out.println();
		}
	    
		BigDecimal bigDecimal1 = new BigDecimal("124567890.0987654321");
		BigDecimal bigDecimal2 = new BigDecimal("987654321.123456789");
		  
		// Addition of two BigDecimals 
		bigDecimal1 = bigDecimal1.add(bigDecimal2); 
		System.out.println("bigDecimal1 = " + bigDecimal1); 

		// Multiplication of two BigDecimals 
		bigDecimal1 = bigDecimal1.multiply(bigDecimal2); 
		System.out.println("bigDecimal1 = " + bigDecimal1); 

		// Subtraction of two BigDecimals 
		bigDecimal1 = bigDecimal1.subtract(bigDecimal2); 
		System.out.println("bigDecimal1 = " + bigDecimal1); 

		// Division of two BigDecimals 
		bigDecimal1 = bigDecimal1.divide(bigDecimal2); 
		System.out.println("bigDecimal1 = " + bigDecimal1); 

		// BigDecima1 raised to the power of 2 
		bigDecimal1 = bigDecimal1.pow(2); 
		System.out.println("bigDecimal1 = " + bigDecimal1); 

		// Negate value of BigDecimal1 
		bigDecimal1 = bigDecimal1.negate(); 
		System.out.println("bigDecimal1 = " + bigDecimal1);
		

	    String decimalString01 = "4567.98";
	    String decimalString01a = "4567.90";
	    String decimalString02 = ".075";
	    
	    float floatDecimal01 = Float.valueOf(decimalString01);
	    float floatDecimal02 = Float.valueOf(decimalString02);
	    float floatDecimal03 = floatDecimal01 + (floatDecimal01 * floatDecimal02);
		System.out.println("floatDecimal03 = " + floatDecimal03);
	    
		BigDecimal bigDecimal01 = new BigDecimal(decimalString01);
		BigDecimal bigDecimal01a = new BigDecimal(decimalString01a);
		BigDecimal bigDecimal02 = new BigDecimal(decimalString02);
		BigDecimal bigDecimal03 = (bigDecimal01.multiply(bigDecimal02)).add(bigDecimal01);
		System.out.println("bigDecimal03 = " + bigDecimal03);
		BigDecimal bigDecimal04_HALF_EVEN = (bigDecimal01.multiply(bigDecimal02)).add(bigDecimal01).round(new MathContext(6, RoundingMode.HALF_EVEN));
		System.out.println("bigDecimal04_HALF_EVEN = " + bigDecimal04_HALF_EVEN);
		BigDecimal bigDecimal05_HALF_DOWN = (bigDecimal01.multiply(bigDecimal02)).add(bigDecimal01).round(new MathContext(6, RoundingMode.HALF_DOWN));
		System.out.println("bigDecimal05_HALF_DOWN = " + bigDecimal05_HALF_DOWN);
		BigDecimal bigDecimal06 = (bigDecimal01.multiply(bigDecimal02)).add(bigDecimal01).round(new MathContext(6, RoundingMode.FLOOR));
		System.out.println("bigDecimal06 = " + bigDecimal06);
		BigDecimal bigDecimal07 = (bigDecimal01a.round(new MathContext(6, RoundingMode.HALF_DOWN)).multiply(bigDecimal02)).add(bigDecimal01a);
		System.out.println("bigDecimal07 = " + bigDecimal07);
		
		
		System.out.println("BatchInternal.class.getName(): " + BatchInternal.class.getName());System.out.println();
		System.out.println("BatchInternal.class.getSimpleName(): " + BatchInternal.class.getSimpleName());System.out.println();
		
		Long valueOfTen = null;
		Long valueOfNull = null;
		

		try {
			valueOfTen = Long.valueOf(10);
			log.info("valueOfTen is: " + valueOfTen);System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Unexpected Error Occurred for 'Long.valueOf(10)', Error Message: {0}", e.getMessage()), e);System.out.println();
			log.info("valueOfTen cannot be determined due to error");System.out.println();
		}
		try {
			valueOfNull = Long.valueOf(null);
			log.info("valueOfNull is: " + valueOfNull);System.out.println();
		}
		catch(Exception e) {
			log.error(MessageFormat.format("Expected Error Occurred for 'Long.valueOf(null)', Error Message: {0}", e.getMessage()), e);System.out.println();
			log.info("valueOfNull cannot be determined due to error");System.out.println();
		}
		
		String stringWithQuotesForQuery = null;
		String dataCode = null;
		Long dataId = null;

		stringWithQuotesForQuery = " '9632-AB99', '9632-AB98', '9632-AB97', '9632-AB96', '9632-AB95' ";
		dataCode = "9632";
		dataId = Long.valueOf(198808);
		
		String SQL_UPDATE_WITH_PARAMETERS_FOR_BATCH_INTERNAL =
				" UPDATE TEST.DATA_TABLE_ONE SET VARCHAR_DATA_ONE = 'WQ' WHERE NUMBER_DATA_TWO = _dataIdStringParam_ AND VARCHAR_DATA_THREE = 'G' "
			+   " AND VARCHAR_DATA_FOUR NOT IN ('MN', 'BV', 'CX') AND (VARCHAR_DATA_FIVE = '_dataCodeParam_' OR VARCHAR_DATA_SIX = '_dataCodeParam_' ) "
			+   " AND VARCHAR_DATA_SVEN in ( _stringWithQuotesForQueryParam_ )";


		String updatedSQLString = SQL_UPDATE_WITH_PARAMETERS_FOR_BATCH_INTERNAL.replace("_dataIdStringParam_", dataId.toString()).replace("_dataCodeParam_", dataCode).replace("_stringWithQuotesForQueryParam_", stringWithQuotesForQuery);
		
		log.info("updatedSQL is: " + updatedSQLString);System.out.println();
		
		System.out.println("");
		System.out.println(new Date() + ": MyTask SimpleBatch DONE");
		log.info("{}|MyTask SimpleBatch DONE", UtilMethods.getMethodName());
		
	}    
}
