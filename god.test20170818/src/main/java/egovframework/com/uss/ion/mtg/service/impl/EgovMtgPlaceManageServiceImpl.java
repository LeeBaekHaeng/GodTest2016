/**
 * 개요
 * - 회의실관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

package egovframework.com.uss.ion.mtg.service.impl;

import java.util.ArrayList;
import java.util.List;

import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceFxtrs;
import egovframework.com.uss.ion.mtg.service.MtgPlaceFxtrsVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManage;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResve;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("egovMtgPlaceManageService")
public class EgovMtgPlaceManageServiceImpl extends EgovAbstractServiceImpl implements EgovMtgPlaceManageService {

	@Resource(name="mtgPlaceManageDAO")
    private MtgPlaceManageDAO mtgPlaceManageDAO;

    /** ID Generation */
	@Resource(name="egovMtgPlaceManageIdGnrService")
	private EgovIdGnrService idgenService;

    /** ID Generation */
	@Resource(name="egovMtgPlaceResveIdGnrService")
	private EgovIdGnrService idgenResveService;

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	@Override
	public List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception{
		return mtgPlaceManageDAO.selectMtgPlaceManageList(mtgPlaceManageVO);
	}

	/**
	 * 회의실관리목록 총 갯수를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 회의실관리 카운트 수
	 */
	@Override
	public int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceManageListTotCnt(mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	@Override
	public MtgPlaceManage selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceManage(mtgPlaceManageVO);
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * @param mtgPlaceManage   - 회의실관리 model
	 * @param String           - 회의실비품정보
	 * @param MtgPlaceManageVO - 회의실관리VOl
	 */
	@Override
	@SuppressWarnings("unused")
	public void insertMtgPlaceManage(MtgPlaceManage mtgPlaceManage, String checkedMtgPlacesForInsert,
			                         MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		String	mtgPlaceId = idgenService.getNextStringId();
		mtgPlaceManage.setMtgPlaceId(mtgPlaceId);

		mtgPlaceManageDAO.insertMtgPlaceManage(mtgPlaceManage);
		MtgPlaceFxtrs mtgPlaceFxtrs;
		int insertCnt    = 0;
		String [] mtgPlacesValues = checkedMtgPlacesForInsert.split("[$]");
		String [] sTempMtgPlaces;
		String    sTemp=null;

		if(checkedMtgPlacesForInsert != null && !checkedMtgPlacesForInsert.equals("")){
			for (int i=0; i<mtgPlacesValues.length ; i++){
				mtgPlaceFxtrs = new MtgPlaceFxtrs();
				sTemp = mtgPlacesValues[i];
				sTempMtgPlaces = sTemp.split(",");
				mtgPlaceFxtrs.setMtgPlaceId(mtgPlaceId);
				mtgPlaceFxtrs.setFxtrsCd(sTempMtgPlaces[0]);
				mtgPlaceFxtrs.setQuantity(Integer.parseInt(sTempMtgPlaces[1]));
				mtgPlaceManageDAO.insertMtgPlaceFxtrs(mtgPlaceFxtrs);
			}
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 */
	@Override
	public void updtMtgPlaceManage(MtgPlaceManage mtgPlaceManage, String checkedMtgPlacesForInsert,
            					   MtgPlaceManageVO mtgPlaceManageVO) throws Exception {

		mtgPlaceManageDAO.updtMtgPlaceManage(mtgPlaceManage);
		String sMtgPlaceId = mtgPlaceManage.getMtgPlaceId();

		MtgPlaceFxtrs mtgPlaceFxtrsDel = new MtgPlaceFxtrs();
		mtgPlaceFxtrsDel.setMtgPlaceId(sMtgPlaceId);
		mtgPlaceManageDAO.deleteMtgPlaceFxtrs(mtgPlaceFxtrsDel);

		MtgPlaceFxtrs mtgPlaceFxtrs;
		String [] mtgPlacesValues = checkedMtgPlacesForInsert.split("[$]");
		String [] sTempMtgPlaces;
		String    sTemp=null;
		if(checkedMtgPlacesForInsert != null && !checkedMtgPlacesForInsert.equals("")){
			for (int i=0; i<mtgPlacesValues.length ; i++){
				mtgPlaceFxtrs = new MtgPlaceFxtrs();
				sTemp = mtgPlacesValues[i];
				sTempMtgPlaces = sTemp.split(",");
				mtgPlaceFxtrs.setMtgPlaceId(sMtgPlaceId);
				mtgPlaceFxtrs.setFxtrsCd(sTempMtgPlaces[0]);
				mtgPlaceFxtrs.setQuantity(Integer.parseInt(sTempMtgPlaces[1]));
				mtgPlaceManageDAO.insertMtgPlaceFxtrs(mtgPlaceFxtrs);
			}
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 */
	@Override
	public void deleteMtgPlaceManage(MtgPlaceManage mtgPlaceManage) throws Exception {
		String sMtgPlaceId = mtgPlaceManage.getMtgPlaceId();
		MtgPlaceFxtrs mtgPlaceFxtrsDel = new MtgPlaceFxtrs();
		mtgPlaceFxtrsDel.setMtgPlaceId(sMtgPlaceId);
		mtgPlaceManageDAO.deleteMtgPlaceFxtrs(mtgPlaceFxtrsDel);
		mtgPlaceManageDAO.deleteMtgPlaceManage(mtgPlaceManage);
	}

	/******** 회의실 예약 관리 *************/

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	@Override
	public List<MtgPlaceManageVO> selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception{

		List<MtgPlaceManageVO> result = mtgPlaceManageDAO.selectMtgPlaceIDList(mtgPlaceManageVO);
		List<MtgPlaceManageVO> list = new ArrayList<MtgPlaceManageVO>();
		String tempResveDe = EgovStringUtil.removeMinusChar(mtgPlaceManageVO.getResveDe());
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	MtgPlaceManageVO mtgPlaceManageVO1 = result.get(i);
	    	mtgPlaceManageVO1.setResveDe(tempResveDe);
	        list.add(mtgPlaceManageDAO.selectMtgPlaceResveManageList(mtgPlaceManageVO1));
	    }

		return list;
	}

	/**
	 * 회의실예약 신청화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	@Override
	public MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceResve(mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	@Override
	public MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceResveDetail(mtgPlaceManageVO);
	}

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	@Override
	public void insertMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception {
		mtgPlaceResve.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResve.getResveDe()));
		String	sResveId = idgenResveService.getNextStringId();
		mtgPlaceResve.setResveId(sResveId);
		mtgPlaceManageDAO.insertMtgPlaceResve(mtgPlaceResve);
	}

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	@Override
	public void updtMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception {
		mtgPlaceResve.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResve.getResveDe()));
		mtgPlaceManageDAO.updtMtgPlaceResve(mtgPlaceResve);
	}

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	@Override
	public void deleteMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception {
		mtgPlaceResve.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResve.getResveDe()));
		mtgPlaceManageDAO.deleteMtgPlaceResve(mtgPlaceResve);
	}


	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	@Override
	public int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		mtgPlaceManageVO.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceManageVO.getResveDe()));
		return mtgPlaceManageDAO.mtgPlaceResveDplactCeck(mtgPlaceManageVO);
	}



	/******** 회의실 비품 관리 *************/
	/**
	 * 비품정보 목록을 조회한다
	 * @param fxtrsManageVO - 비품관리 VO
	 * @return List - 비품관리 목록
	 */
	@Override
	public List<MtgPlaceFxtrsVO> selectFxtrsManageList(MtgPlaceFxtrsVO mtgPlaceFxtrsVO) throws Exception{
		return mtgPlaceManageDAO.selectFxtrsManageList(mtgPlaceFxtrsVO);
	}
}
