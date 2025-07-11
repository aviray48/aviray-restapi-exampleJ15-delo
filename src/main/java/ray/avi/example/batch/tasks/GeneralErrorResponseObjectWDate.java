package ray.avi.example.batch.tasks;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GeneralErrorResponseObjectWDate implements Serializable {

	protected int code;
	protected String message;
	protected String generalNumber;
	protected Date generalDate;
    
    public GeneralErrorResponseObjectWDate(){}
    
	public GeneralErrorResponseObjectWDate(int code, String message, String generalNumber, Date generalDate) {
        this.code = code;
        this.message = message;
        this.generalNumber = generalNumber;
        this.generalDate = generalDate;
    }
	
	public GeneralErrorResponseObjectWDate(GeneralErrorResponseObjectWDate generalErrorResponseObjectWDate) {
		this(generalErrorResponseObjectWDate.getCode(), generalErrorResponseObjectWDate.getMessage(), generalErrorResponseObjectWDate.getGeneralNumber(), generalErrorResponseObjectWDate.getGeneralDate());
    }
    
	public GeneralErrorResponseObjectWDate(int code, String message, String generalNumber) {
		this(code, message, generalNumber, null);
    }
	
	public GeneralErrorResponseObjectWDate(int code, String message) {
        this(code, message, null);
    }
}
