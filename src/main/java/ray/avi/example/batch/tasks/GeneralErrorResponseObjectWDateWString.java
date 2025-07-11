package ray.avi.example.batch.tasks;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GeneralErrorResponseObjectWDateWString extends GeneralErrorResponseObjectWDate implements Serializable {

	protected String generalString;
    
    public GeneralErrorResponseObjectWDateWString(){}
    
	public GeneralErrorResponseObjectWDateWString(int code, String message, String generalNumber, Date generalDate, String generalString) {
        this.code = code;
        this.message = message;
        this.generalNumber = generalNumber;
        this.generalDate = generalDate;
        this.generalString = generalString;
    }
	
	public GeneralErrorResponseObjectWDateWString(GeneralErrorResponseObjectWDateWString generalErrorResponseObjectWDateWString) {
		this(generalErrorResponseObjectWDateWString.getCode(), generalErrorResponseObjectWDateWString.getMessage(), generalErrorResponseObjectWDateWString.getGeneralNumber(), generalErrorResponseObjectWDateWString.getGeneralDate(), generalErrorResponseObjectWDateWString.getGeneralString());
    }
    
	public GeneralErrorResponseObjectWDateWString(GeneralErrorResponseObjectWDate generalErrorResponseObjectWDate, String generalString) {
		super(generalErrorResponseObjectWDate);
        this.code = generalErrorResponseObjectWDate.getCode();
        this.message = generalErrorResponseObjectWDate.getMessage();
        this.generalNumber = generalErrorResponseObjectWDate.getGeneralNumber();
        this.generalDate = generalErrorResponseObjectWDate.getGeneralDate();
        this.generalString = generalString;
    }
    
	public GeneralErrorResponseObjectWDateWString(int code, String message, String generalNumber, Date generalDate) {
		this(code, message, generalNumber, generalDate, null);
    }
    
	public GeneralErrorResponseObjectWDateWString(int code, String message, String generalNumber) {
		this(code, message, generalNumber, null);
    }
	
	public GeneralErrorResponseObjectWDateWString(int code, String message) {
        this(code, message, null);
    }
}
