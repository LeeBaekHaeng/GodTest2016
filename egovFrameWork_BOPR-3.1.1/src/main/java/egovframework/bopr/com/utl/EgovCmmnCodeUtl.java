package egovframework.bopr.com.utl;

import java.util.ArrayList;
import java.util.List;

import egovframework.bopr.bam.web.EgovBatchDlbrtController;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("EgovCmmnCodeUtl")
public class EgovCmmnCodeUtl {

	/** 공통 코드 상세 관리 Service */
	@Resource( name = "EgovCmmUseService" )
	private EgovCmmUseService egovCmmUseService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
	
	public List<CmmnDetailCode> getCmmnDetailCodeList(String codeId)
	{
		try
		{
			ComDefaultCodeVO codeSearchVO = new ComDefaultCodeVO();
			codeSearchVO.setCodeId(codeId);
			
			if (egovCmmUseService == null)
			{
				LOGGER.debug("●●●●●●NULL!!!!");
			}
			else
			{
				LOGGER.debug("●●●●●●NOT NULL!!!!");
			}
			
			return egovCmmUseService.selectCmmCodeDetail(codeSearchVO);
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
			return new ArrayList<CmmnDetailCode>();
		}
	}
	
	public List<CmmnDetailCode> getCmmnDetailCodeListAddHead(String codeId, String code, String codeNm)
	{
		CmmnDetailCode headCode = new CmmnDetailCode();
		headCode.setCode(code);
		headCode.setCodeNm(codeNm);
		
		return getCmmnDetailCodeListAddHead(codeId, headCode);
	}
	
	public List<CmmnDetailCode> getCmmnDetailCodeListAddHead(String codeId, CmmnDetailCode headCode)
	{
		List<CmmnDetailCode> headList = new ArrayList<CmmnDetailCode>();
		headList.add(headCode);
		
		return getCmmnDetailCodeListAddHeads(codeId, headList);
	}
	
	public List<CmmnDetailCode> getCmmnDetailCodeListAddHeads(String codeId, List<CmmnDetailCode> headList)
	{
		List<CmmnDetailCode> returnList = new ArrayList<CmmnDetailCode>();
		
		if (!CollectionUtils.isEmpty(headList))
		{
			returnList.addAll(headList);
		}
		
		returnList.addAll(getCmmnDetailCodeList(codeId));

		return returnList;
	}
	
	public List<CmmnDetailCode> getCmmnDetailCodeListExcept(String codeId, String removeCode)
	{
		List<String> removeCodes = new ArrayList<String>();
		removeCodes.add(removeCode);
		
		return getCmmnDetailCodeListExcept(codeId, removeCodes);
	}
	
	public List<CmmnDetailCode> getCmmnDetailCodeListExcept(String codeId, List<String> removeCodes)
	{
		List<CmmnDetailCode> returnList = getCmmnDetailCodeList(codeId);
		
		for (String removeCode : removeCodes)
		{
			for (int index = returnList.size() - 1; index >= 0; index--)
			{
				if (removeCode.equals(returnList.get(index).getCode()))
				{
					returnList.remove(index);
				}
			}
		}
		
		return returnList;
	}
}
