package egovframework.bopr.mom.service;

public class EgovBatchExecutException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = -1414179870589000410L;
	
	/** serialVersionUID setter method */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public EgovBatchExecutException()
	{
		super();
	}
	
	public EgovBatchExecutException(String mssage)
	{
		super(mssage);
	}
	
	public EgovBatchExecutException(String mssage, Throwable cause)
	{
		super(mssage, cause);
	}
	
	public EgovBatchExecutException(Throwable cause)
	{
		super(cause);
	}

}
