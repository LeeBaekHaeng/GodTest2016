package godsoft.com.cmm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface CmmUseMapper {

	List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo);

}
