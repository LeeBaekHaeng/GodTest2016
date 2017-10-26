package egovframework.com.cop.smt.lsm.service.impl;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.smt.lsm.service.EgovLeaderSchdulService;
import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdul;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 간부일정에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 */
@Service("EgovLeaderSchdulService")
public class EgovLeaderSchdulServiceImpl extends EgovAbstractServiceImpl implements EgovLeaderSchdulService {
	
	@Resource(name = "LeaderSchdulDAO")
    private LeaderSchdulDAO leaderSchdulDAO;
	
	@Resource(name="egovLeaderSchdulIdGnrService")
    private EgovIdGnrService idgenService;
	
	/**
	 * 사용자 목록을 조회한다.
	 * @param EmplyrVO
	 * @return  Map<String, Object>
	 * 
	 * @param emplyrVO
	 */
	public Map<String, Object> selectEmplyrList(EmplyrVO emplyrVO) throws Exception{
		List<EmplyrVO> result = leaderSchdulDAO.selectEmplyrList(emplyrVO);
		int cnt = leaderSchdulDAO.selectEmplyrListCnt(emplyrVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 월별 간부일정 목록을 조회한다.
	 * @param LeaderSchdulVO
	 * @return  List
	 * 
	 * @param leaderSchdulVo
	 */
	public List<LeaderSchdulVO> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVo) throws Exception{
		return (List<LeaderSchdulVO>)leaderSchdulDAO.selectLeaderSchdulList(leaderSchdulVo);
	}


	/**
	 * 간부일정 정보를 조회한다.
	 * @param LeaderSchdulVO
	 * @return  LeaderSchdulVO
	 * 
	 * @param leaderSchdulVO
	 */
	public LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO) throws Exception{
		return leaderSchdulDAO.selectLeaderSchdul(leaderSchdulVO);
	}

	/**
	 * 간부일정 정보를 수정한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void updateLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		leaderSchdulDAO.updateLeaderSchdul(leaderSchdul);
		leaderSchdulDAO.deleteLeaderSchdulDe(leaderSchdul);
		
		insertLeaderSchdulDe(leaderSchdul);
	}

	/**
	 * 간부일정 정보를 등록한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void insertLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		String schdulID = idgenService.getNextStringId();
		leaderSchdul.setSchdulId(schdulID);
		
		leaderSchdulDAO.insertLeaderSchdul(leaderSchdul);
		
		insertLeaderSchdulDe(leaderSchdul);
	}
	
	/**
	 * 간부일정일자 정보를 등록한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	private void insertLeaderSchdulDe(LeaderSchdul leaderSchdul) throws Exception{
		leaderSchdul.setSchdulDe(leaderSchdul.getSchdulBgndeYYYMMDD().replaceAll("-", ""));
		// SCHEDUL_DE 설정
		if(leaderSchdul.getSchdulBgndeYYYMMDD().equals(leaderSchdul.getSchdulEnddeYYYMMDD()) || leaderSchdul.getReptitSeCode().equals("1")){
			leaderSchdulDAO.insertLeaderSchdulDe(leaderSchdul);
		}else{
			String sBgnDe = leaderSchdul.getSchdulBgndeYYYMMDD().replaceAll("-", "");
			String sEndDe = leaderSchdul.getSchdulEnddeYYYMMDD().replaceAll("-", "");
			int iBgnDe = Integer.valueOf(sBgnDe);
			int iEndDe = Integer.valueOf(sEndDe);
			
			int iNowDe = iBgnDe;
			int iNowYear = 0;
			int iNowMonth = 0;
			int iNowDay = 0;
			int iEndDay = 0;
			
			String sNowYear = "";
			String sNowMonth = "";
			String sNowDay = "";
			
			java.util.Calendar cal = java.util.Calendar.getInstance();
			
			LeaderSchdul leaderSchdulDe = null;
			if(leaderSchdul.getReptitSeCode().equals("2") || leaderSchdul.getReptitSeCode().equals("3") || leaderSchdul.getReptitSeCode().equals("4")){
				while(true){
					if(iNowDe != iBgnDe){	
						iNowYear = Integer.valueOf(String.valueOf(iNowDe).substring(0,4));
						iNowMonth = Integer.valueOf(String.valueOf(iNowDe).substring(4,6));
						iNowDay = Integer.valueOf(String.valueOf(iNowDe).substring(6,8));
						
						if(leaderSchdul.getReptitSeCode().equals("2") || leaderSchdul.getReptitSeCode().equals("3")){
							cal.set(iNowYear, iNowMonth -1, 1);
							iEndDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
							
							if(iEndDay < iNowDay){
								iNowMonth = iNowMonth + 1;
								iNowDay = 1;
								if(iNowMonth > 12){
									iNowMonth = 1;
									iNowYear = iNowYear + 1;
								}
							}
						}else{
							if(iNowMonth > 12){
								iNowMonth = 1;
								iNowYear = iNowYear + 1;
							}
						}
						
						sNowYear = String.valueOf(iNowYear);
						sNowMonth = String.valueOf(iNowMonth);
						sNowDay = String.valueOf(iNowDay);
						
						if(sNowMonth.length() == 1)	sNowMonth = "0" + sNowMonth;
						if(sNowDay.length() == 1) sNowDay = "0" + sNowDay;
						
						iNowDe = Integer.valueOf(sNowYear + sNowMonth + sNowDay);
					}
					
					if( iNowDe > iEndDe) break;
					
					leaderSchdulDe = new LeaderSchdul();
					leaderSchdulDe.setSchdulId(leaderSchdul.getSchdulId());
					leaderSchdulDe.setSchdulDe(String.valueOf(iNowDe));
					
					leaderSchdulDAO.insertLeaderSchdulDe(leaderSchdulDe);
					
					if(leaderSchdul.getReptitSeCode().equals("2")){
						iNowDe = iNowDe + 1;
					}else if(leaderSchdul.getReptitSeCode().equals("3")){
						iNowDe = iNowDe + 7;
					}else if(leaderSchdul.getReptitSeCode().equals("4")){
						iNowDe = iNowDe + 100;
					}
				}
			}
		}
	}
	
	/**
	 * 간부일정 정보를 삭제한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void deleteLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		leaderSchdulDAO.deleteLeaderSchdulDe(leaderSchdul);
		leaderSchdulDAO.deleteLeaderSchdul(leaderSchdul);
	}
	
	/**
	 * 간부상태 목록을 조회한다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return  Map<String, Object>
	 * 
	 * @param leaderSttusVO
	 */
	public Map<String, Object> selectLeaderSttusList(LeaderSttusVO leaderSttusVO) throws Exception{
		List<LeaderSttusVO> result = leaderSchdulDAO.selectLeaderSttusList(leaderSttusVO);
		int cnt = leaderSchdulDAO.selectLeaderSttusListCnt(leaderSttusVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 간부상태 정보를 조회한다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return  LeaderSttusVO - 간부상태 VO
	 * 
	 * @param leaderSttusVO
	 */
	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO) throws Exception{
		return leaderSchdulDAO.selectLeaderSttus(leaderSttusVO);
	}

	/**
	 * 간부상태 정보를 수정한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void updateLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		leaderSchdulDAO.updateLeaderSttus(leaderSttus);
	}

	/**
	 * 간부상태 정보를 등록한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void insertLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		leaderSchdulDAO.insertLeaderSttus(leaderSttus);
	}
	
	/**
	 * 간부상태를 등록하기 위한 중복 조회를 수행한다.
	 * @param LeaderSttus - 간부상태 model
	 * @return  int 
	 * 
	 * @param leaderSttus
	 */
	public int selectLeaderSttusCheck(LeaderSttus leaderSttus) throws Exception{
		return leaderSchdulDAO.selectLeaderSttusCheck(leaderSttus);
	}
	
	/**
	 * 간부상태 정보를 삭제한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void deleteLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		leaderSchdulDAO.deleteLeaderSttus(leaderSttus);
	}

}