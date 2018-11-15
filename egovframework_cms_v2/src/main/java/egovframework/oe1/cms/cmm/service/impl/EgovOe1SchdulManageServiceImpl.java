/*
 * Copyright 2010 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.oe1.cms.cmm.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;
import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageService;
import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import java.util.List;
import java.util.Map;
/**
 * 전체일정관리를 처리하는 ServiceImpl Class 구현
 * @author 운영환경1팀 김민수
 * @since 2009.08.16
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.16  김민수          최초 생성
 *
 * </pre>
 */
@Service("egovDeptSchdulManageService")
public class EgovOe1SchdulManageServiceImpl extends AbstractServiceImpl implements EgovOe1SchdulManageService{

	//final private Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="deptSchdulManageDao")
	private EgovOe1SchdulManageDao dao;

	
	@Resource(name="schdulManageIdGnrService")
	private EgovIdGnrService idgenService;

    /**
	 * 메인 전체일정조회
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectMainSchdulList(Map map) throws Exception{
		return (List)dao.selectMainSchdulList(map);
	}
	
	/**
	 * 전체일정관리조회
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectDeptSchdulManageMainList(Map map) throws Exception{
		return (List)dao.selectDeptSchdulManageMainList(map);
	}
	
    /**
	 * 부서일정 목록을 Map(map)형식으로 조회한다. 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception{
		return (List)dao.selectDeptSchdulManageRetrieve(map);
	}
	
    /**
	 * 부서일정 목록을 VO(model)형식으로 조회한다. 
	 * @param EgovOe1SchdulManageVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetailVO(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return (EgovOe1SchdulManageVO)dao.selectDeptSchdulManageDetailVO(deptSchdulManageVO);
	}
	
    /**
	 * 부서일정 목록을 조회한다. 
	 * @param EgovOe1ComDefaultVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectDeptSchdulManageList(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		

		return (List)dao.selectDeptSchdulManageList(deptSchdulManageVO);
	}
	
    /**
	 * 부서일정를(을) 상세조회 한다.
	 * @param EgovOe1SchdulManageVO -  일정정보가 담김 VO
	 * @return EgovOe1SchdulManageVO
	 * @exception Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetail(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return dao.selectDeptSchdulManageDetail(deptSchdulManageVO);
	}
	
    /**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param EgovOe1ComDefaultVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectDeptSchdulManageListCnt(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		

		return (Integer)dao.selectDeptSchdulManageListCnt(deptSchdulManageVO);
	}

    /**
	 * 부서일정를(을) 등록한다.
	 * @param EgovOe1SchdulManageVO - 조회할 정보가 담긴 VO
	 * @exception Exception
	 */
	public void insertDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();
		deptSchdulManageVO.setSchdulId(sMakeId);

		dao.insertDeptSchdulManage(deptSchdulManageVO);
	}
	
    /**
	 * 부서일정를(을) 수정한다.
	 * @param EgovOe1SchdulManageVO - 조회할 정보가 담긴 VO
	 * @exception Exception
	 */
	public void updateDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		dao.updateDeptSchdulManage(deptSchdulManageVO);
	}
	
    /**
	 * 부서일정를(을) 삭제한다.
	 * @param EgovOe1SchdulManageVO - 조회할 정보가 담긴 VO
	 * @exception Exception
	 */
	public void deleteDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		dao.deleteDeptSchdulManage(deptSchdulManageVO);
	}
}
