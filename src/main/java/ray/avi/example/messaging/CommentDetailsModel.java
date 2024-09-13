package ray.avi.example.messaging;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class representing the Comment Details
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_EMPTY)
public class CommentDetailsModel {

	/**
	 * The Comment Category
	 */
	private String category;
	
	/**
	 * The City
	 */
	private String city;

	/**
	 * The Comment Text
	 */
	private String comment;

	/**
	 * The Document Id of the document being commented on.
	 */
	private String commentOnDocumentId;

	/**
	 * The Comment Tracking Number
	 */
	private String commentTrackingNumber;

	/**
	 * The country
	 */
	private String country;

	/**
	 * The Email address associated with the comment
	 */
	private String email;

	/**
	 * The List of Files uploaded
	 */
	private List<String> files;

	/**
	 * The First Name of the submitter
	 */
	private String firstName;

	/**
	 * The Last Name of the submitter
	 */
	private String lastName;
	
	/**
	 * The Number of Items Received
	 */
	private int numItemsReceived;	

	/**
	 * The Organization, Company or Government Agency
	 */
	private String organization;

	/**
	 * The Organization or Government Agency Type
	 */
	private String organizationType;

	/**
	 * The phone number
	 */
	private String phone;

	/**
	 * The Date and Time the Comment was Received
	 */
	private Date receiveDate;

	/**
	 * Boolean flag indicating whether or not an email receipt should be sent.
	 */
	private boolean sendEmailReceipt;
	
	/**
	 * The State, Province or Region
	 */
	private String stateProvinceRegion;
	
	/**
	 * The submission key associated with this comment submission
	 */
	private String submissionKey;

	/**
	 * The Submitter Rep
	 */
	private String submitterRep;

	/**
	 * The zip code
	 */
	private String zip;
	
	/**
	 * The userId associated with the API key submitting the comment
	 */
	private String userId;
	
	/**
	 * The requestId associated with the API key submitting the comment
	 */
	private String requestId;
	
	/**
	 * The sourceIP associated with the API key submitting the comment
	 */
	private String sourceIp;

}
