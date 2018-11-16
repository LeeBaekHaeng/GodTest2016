package egovframework.bopr.sim.service;

import egovframework.com.cmm.ComDefaultVO;

public class BatchBeanVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = -1198547688423086916L;

	/** beanId */
	private String beanId;
	
	/** batchId */
	private String batchId;
	
	public long getSerialVersionUID()
	{
		return serialVersionUID;
	}
	
	public void setBeanId(String beanId)
	{
		this.beanId = beanId;
	}
	
	public String getBeanId()
	{
		return beanId;
	}
	
	public void setBatchId(String batchId)
	{
		this.batchId = batchId;
	}
	
	public String getBatchId()
	{
		return batchId;
	}
	
	public String toString()
	{
		return "BatchBeanVO[" + "beanId=" + beanId + ", batchId=" + batchId + "]";
	}
	
}
