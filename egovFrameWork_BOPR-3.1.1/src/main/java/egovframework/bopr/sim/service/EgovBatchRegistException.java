package egovframework.bopr.sim.service;

public class EgovBatchRegistException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = -8298675350806018716L;

	/** serialVersionUID setter method */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public EgovBatchRegistException()
	{
		super();
	}
	
	public EgovBatchRegistException(String mssage)
	{
		super(mssage);
	}
	
	public EgovBatchRegistException(String mssage, Throwable cause)
	{
		super(mssage, cause);
	}
	
	public EgovBatchRegistException(Throwable cause)
	{
		super(cause);
	}

}
