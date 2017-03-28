package kosii.test.sub.comtnemplyrinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kosii.test.curd.comtnemplyrinfo.service.impl.ComtnemplyrinfoDAO;
import kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO;
import kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoService;
// Vo 객체에 대한 import 구문
// import (vo package).ComtnemplyrinfoDefaultVO;
// import (vo package).ComtnemplyrinfoVO;
// Dao 객체에 대한 import 구문
// import (dao package).ComtnemplyrinfoDAO;
import kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : ComtnemplyrinfoServiceImpl.java
 * @Description : Comtnemplyrinfo Business Implement class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-03-12
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Service("kosii.test.sub.comtnemplyrinfo.service.impl.ComtnemplyrinfoServiceImpl")
public class ComtnemplyrinfoServiceImpl extends EgovAbstractServiceImpl
		implements ComtnemplyrinfoService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComtnemplyrinfoServiceImpl.class);

	@Resource(name = "comtnemplyrinfoDAO")
	private ComtnemplyrinfoDAO comtnemplyrinfoDAO;

	/** ID Generation */
	// @Resource(name="{egovComtnemplyrinfoIdGnrService}")
	// private EgovIdGnrService egovIdGnrService;

	/**
	 * COMTNEMPLYRINFO을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 ComtnemplyrinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public String insertComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception {
		LOGGER.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		LOGGER.debug(vo.toString());

		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO vo2 = (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO) BeanUtils
				.cloneBean(vo);

		comtnemplyrinfoDAO.insertComtnemplyrinfo(vo2);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}

	/**
	 * COMTNEMPLYRINFO을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 ComtnemplyrinfoVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception {
		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO vo2 = (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO) BeanUtils
				.cloneBean(vo);

		comtnemplyrinfoDAO.updateComtnemplyrinfo(vo2);
	}

	/**
	 * COMTNEMPLYRINFO을 삭제한다.
	 * 
	 * @param vo
	 *            - 삭제할 정보가 담긴 ComtnemplyrinfoVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception {
		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO vo2 = (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO) BeanUtils
				.cloneBean(vo);

		comtnemplyrinfoDAO.deleteComtnemplyrinfo(vo2);
	}

	/**
	 * COMTNEMPLYRINFO을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 ComtnemplyrinfoVO
	 * @return 조회한 COMTNEMPLYRINFO
	 * @exception Exception
	 */
	@Override
	public ComtnemplyrinfoVO selectComtnemplyrinfo(ComtnemplyrinfoVO vo)
			throws Exception {
		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO vo2 = (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO) BeanUtils
				.cloneBean(vo);

		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO resultVO2 = comtnemplyrinfoDAO
				.selectComtnemplyrinfo(vo2);

		ComtnemplyrinfoVO resultVO = (ComtnemplyrinfoVO) BeanUtils
				.cloneBean(resultVO2);

		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * COMTNEMPLYRINFO 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return COMTNEMPLYRINFO 목록
	 * @exception Exception
	 */
	// public List<?> selectComtnemplyrinfoList(ComtnemplyrinfoDefaultVO
	// searchVO)
	// throws Exception {
	@Override
	public List<?> selectComtnemplyrinfoList(ComtnemplyrinfoDefaultVO searchVO) {
		// return comtnemplyrinfoDAO.selectComtnemplyrinfoList(searchVO);
		List<?> items = null;
		try {
			// kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO
			// searchVO2 =
			// (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO)
			// BeanUtils
			// .cloneBean(searchVO);

			kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO searchVO2 = new kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO();

			BeanUtils.copyProperties(searchVO2, searchVO);

			items = comtnemplyrinfoDAO.selectComtnemplyrinfoList(searchVO2);
		} catch (Exception e) {
			egovLogger.debug(e.getMessage());
		}
		return items;
	}

	/**
	 * COMTNEMPLYRINFO 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return COMTNEMPLYRINFO 총 갯수
	 * @exception
	 */
	@Override
	public int selectComtnemplyrinfoListTotCnt(ComtnemplyrinfoDefaultVO searchVO)
			throws Exception {
		// kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO
		// searchVO2 =
		// (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO)
		// BeanUtils
		// .cloneBean(searchVO);

		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO searchVO2 = new kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO();

		BeanUtils.copyProperties(searchVO2, searchVO);

		return comtnemplyrinfoDAO.selectComtnemplyrinfoListTotCnt(searchVO2);
	}

	// @Override
	// public String insertComtnemplyrinfo(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO vo)
	// throws Exception {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void updateComtnemplyrinfo(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO vo)
	// throws Exception {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void deleteComtnemplyrinfo(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO vo)
	// throws Exception {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO
	// selectComtnemplyrinfo(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoVO vo)
	// throws Exception {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List selectComtnemplyrinfoList(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO searchVO)
	// throws Exception {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int selectComtnemplyrinfoListTotCnt(
	// kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO searchVO)
	// {
	// // TODO Auto-generated method stub
	// return 0;
	// }

}
