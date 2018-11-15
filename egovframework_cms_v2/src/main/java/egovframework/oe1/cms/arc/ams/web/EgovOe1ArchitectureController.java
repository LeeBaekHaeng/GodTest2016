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
package egovframework.oe1.cms.arc.ams.web;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcListItemVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ClassService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ClassVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ComponentService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ComponentVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodCallingService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodCallingVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ParameterService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ParameterVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1QueryService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1QueryVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 아키텍쳐 정보를 관리하는 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김연수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ArchitectureController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "componentService")
	protected EgovOe1ComponentService componentService;
	
	@Resource(name = "classService")
	protected EgovOe1ClassService classService;
	
	@Resource(name = "methodService")
	protected EgovOe1MethodService methodService;
	
	@Resource(name = "parameterService")
	protected EgovOe1ParameterService parameterService;
	
	@Resource(name = "queryService")
	protected EgovOe1QueryService queryService;
	
	@Resource(name ="methodCallingService")
	protected EgovOe1MethodCallingService methodCallingService;
	
	@Resource(name = "arcListService")
	protected EgovOe1ArcDefaultService listService;
	
	/**
	 * 아키텍쳐정보 컴포넌트/클래스 트리 조회
	 * @param 검색조건이 담긴 EgovOe1ComDefaultVO 
	 * @return "/cms/arc/ams/EgovMethodStructureAll"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getObjectCompnPkgTree.do")
	public String insertUIAdaptorInfoViewAll(@ModelAttribute("vo") EgovOe1ComDefaultVO searchVo, ModelMap model)throws Exception  {
		
		return  "/cms/arc/ams/EgovMethodStructureAll";
	}

	/**
	 * 아키텍쳐정보 트리 팝업 조회
	 * @param 검색조건이 담긴 EgovOe1ComDefaultVO 
	 * @return "/cms/arc/ams/EgovMethodStructurePopup"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectMethodStructurePopup.do")
	public String selectMethodStructurePopup(@ModelAttribute("vo") EgovOe1ComDefaultVO searchVo, 
			@RequestParam(value="objectType") String objectType, 
			@RequestParam(value="upperObjectId") String upperObjectId, 
			ModelMap model)throws Exception  {
		
		model.addAttribute("objectType", objectType);
		model.addAttribute("upperObjectId", upperObjectId);
		
		return  "/cms/arc/ams/EgovMethodStructurePopup";
	}

	/**
	 * 아키텍쳐정보 트리 조회
	 * @param 검색조건이 담긴 EgovOe1ComDefaultVO, condition, keyword
	 * @return "/cms/arc/ams/EgovJson"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/seletcStructureTreeAll.do")
	public String getObjectCompnPkgTree(@ModelAttribute("comVO") EgovOe1ComponentVO comVO, 
			@RequestParam(value="condition",required= false) String condition, 
			@RequestParam(value = "searchKeyword", required = false) String keyword,
			ModelMap model) throws Exception {
		StringBuffer page = new StringBuffer();

		page.append("<UL id='treeview' class='filetree'>");
		
		List<EgovOe1ComponentVO> list = componentService.selectCompPkgList(comVO);
		
		String prevCompnPkg = "";
		String prevCompnNm = "";
		
		for (EgovOe1ComponentVO vo : list) {
			String getCompnPkg = vo.getCompnPckage();
			String getCompnNm = vo.getCompnNm();
			String getCompnId = vo.getCompnId();
			
			if (!getCompnPkg.equals(prevCompnPkg)) {
				if (prevCompnPkg.length() != 0) {
					page.append("</UL></LI></UL></LI>");
				}
				//page.append("<LI><img src='/oe1/images/egovframework/oe1/cms/arc/compnpkg.gif' >CompnPkg : " + URLEncoder.encode(getCompnPkg, "utf-8"));
				page.append("<LI><img src='/oe1/images/egovframework/oe1/cms/arc/compnpkg.gif' >CompnPkg : ");
				page.append(getCompnPkg);
				page.append("<UL id='nodeComp' class='nodeComp'>");
				
				if (!getCompnNm.equals(prevCompnNm)) {
					page.append("<LI val="+getCompnId+"><img src='/oe1/images/egovframework/oe1/cms/arc/component.gif' >CompnNm : "+getCompnNm);
					page.append("<UL>");
				}
				prevCompnPkg = vo.getCompnPckage();
				prevCompnNm = vo.getCompnNm();
				continue;
			}
			if (!getCompnNm.equals(prevCompnNm)) {
				if (getCompnNm.length() != 0)
					page.append("</UL></LI>");
				page.append("<LI val="+getCompnId+"><img src='/oe1/images/egovframework/oe1/cms/arc/component.gif' >CompnNm : "+getCompnNm);
				page.append("<UL>");
				prevCompnNm = getCompnNm;
			}
		}
		
		page.append("</UL>");
		
		model.addAttribute("jsonString", page.toString());
		
		return "cms/arc/ams/EgovJson";
	}

	/**
	 * 아키텍쳐정보 트리 전체/검색 조회
	 * @param 검색조건이 담긴 objectType, condition, keyword, upperObjectId
	 * @return "/cms/arc/ams/EgovJson"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getObjectTree.do")
	public String getObjectTree(@RequestParam(value="upperObjectId",required= false) String upperObjectId, 
			@RequestParam(value="objectType", required=false) String objectType, 
			@RequestParam(value="condition",required= false) String condition, 
			@RequestParam(value = "searchKeyword", required = false) String keyword, ModelMap model) throws Exception {
		
		/**
		 * http://localhost:8080/oe1//cms/arc/ams/getObjectTreeJSON.do?
		 * upperObjectId=CLSS-000000000000001&objectType=4
		 * 
		 * [{"name":"selectCmmCodeDetail(ComDefaultCodeVO vo)","id":
		 * "MTHD-000000000000003"
		 * },{"name":"selectCmmCodeDetails(List voList)","id"
		 * :"MTHD-000000000000004"
		 * },{"name":"selectOgrnztIdDetail(ComDefaultCodeVO vo)"
		 * ,"id":"MTHD-000000000000005"
		 * },{"name":"selectGroupIdDetail(ComDefaultCodeVO vo)"
		 * ,"id":"MTHD-000000000000006"}]
		 */
		String objectType_temp = "";
		
		List<JSONObject> resultArray = new ArrayList<JSONObject>();
		EgovOe1ArcDefaultVO vo = new EgovOe1ComponentVO();	// Class를 찾는게 아니라 구현 클래스 중 아무거나 하나를 Search VO로 사용하는 것 bad example of polymorphism
		
		if (condition!=null){
			vo.setSearchCondition(condition);
		}else{
			vo.setSearchCondition("1");
		}
		if (keyword != null){
			vo.setSearchKeyword(keyword);
			objectType_temp = condition;	  // --> 이커뭐지???????????????????
		}else{
			objectType_temp = objectType;
		}
		
		vo.setFirstIndex(0);
		vo.setRecordCountPerPage(Integer.MAX_VALUE);

		EgovOe1ArcDefaultService service = null;
		if(null == upperObjectId || "".equals(upperObjectId)){
			service = componentService;
		}else{
			vo.setSearchKeyword(upperObjectId);
			
			switch(Integer.parseInt(objectType_temp)) {
				case EgovOe1ArcDefaultVO.COMPONENT :
					service = componentService;
					break;
				case EgovOe1ArcDefaultVO.CLASS:
					service = classService;
					break;
				case EgovOe1ArcDefaultVO.METHOD:
					service = methodService;
					break;
				case EgovOe1ArcDefaultVO.PARAMETER:
					service = parameterService;
					break;
				case EgovOe1ArcDefaultVO.QUERY:
					service = queryService;
					break;
				default:
			}

		}
		
		if(8 == Integer.parseInt(objectType_temp) || "16".equals(objectType_temp)){
			objectType_temp = "8";
			service = parameterService;
			List<EgovOe1ArcDefaultVO> list = service.selectArcObjectList(vo);	// good example of polymorphism
			
			for (EgovOe1ArcDefaultVO arcVo : list) {
				JSONObject json = new JSONObject();
				json.put("name", makeURLString(arcVo.getId(), arcVo.getName(), objectType_temp));
				json.put("id",arcVo.getId());
				resultArray.add(json);
			}
			list = null;
			objectType_temp = "16";
			service = queryService;
			list = service.selectArcObjectList(vo);	// good example of polymorphism
			
			for (EgovOe1ArcDefaultVO arcVo : list) {
				JSONObject json = new JSONObject();
				json.put("name", makeURLString(arcVo.getId(), arcVo.getName(), objectType_temp));
				json.put("id",arcVo.getId());
				resultArray.add(json);
			}
		}else{
			if (service != null) {
			List<EgovOe1ArcDefaultVO> list = service.selectArcObjectList(vo);	// good example of polymorphism
			
			
			for (EgovOe1ArcDefaultVO arcVo : list) {
				JSONObject json = new JSONObject();
				json.put("name", makeURLString(arcVo.getId(), arcVo.getName(), objectType_temp));
				json.put("id",arcVo.getId());
				resultArray.add(json);
			}
			}
			
		}
		
		if(resultArray.isEmpty()){
			JSONObject json = new JSONObject();
			json.put("name", "");
			json.put("id", "");
			resultArray.add(json);
		}
		
		model.addAttribute("jsonString", makeJSONString(resultArray));
		return "cms/arc/ams/EgovJson";
	}

	/**
	 * 아키텍쳐정보 트리 조회
	 * @param 검색조건이 담긴 compId, comVO, condition, keyword
	 * @return "/cms/arc/ams/EgovJson"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getObjectCompnTree.do")
	public String getObjectCompnTree(@RequestParam("compId") String compId, @ModelAttribute("comVO") EgovOe1ComponentVO comVO, 
			@RequestParam(value="condition",required= false) String condition, 
			@RequestParam(value = "searchKeyword", required = false) String keyword, ModelMap model) throws Exception {
		StringBuffer page = new StringBuffer();
		EgovOe1ComponentVO cvo = componentService.selectComponent(compId);
		cvo.setId(compId);
		
		if (condition!=null)
			cvo.setSearchCondition(condition);
		
		if (keyword != null)
			cvo.setSearchKeyword(keyword);
		
		page.append("<input type='hidden' name='compnId' value='"+compId+"'/>");
		page.append("<UL id='treeview' class='filetree'>");
		page.append("<LI>Comp : ");
		page.append(cvo.getCompnNm());
		page.append("<UL>");
		
		List<EgovOe1ArcListItemVO> list = listService.selectArcList(cvo);

		String prevCompn = "";
		String prevClass = "";
		String prevMethod = "";
		
		for (EgovOe1ArcListItemVO vo : list) {
			
			if (!vo.getClassId().equals(prevClass)) {
				if (prevClass.length() != 0) {
					page.append("</UL></LI></UL></LI>");
				}
				if ("0".equals(condition)) {
					page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+makeKeywordStronger(vo.getClassNm(),keyword)+"</A>");
				} else {
					page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+vo.getClassNm()+"</A>");
				}
				page.append("<UL>");
				
				if (!vo.getMethdId().equals(prevMethod)) {
					if ("1".equals(condition)) {
						page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + makeKeywordStronger(vo.getMethdNm(), keyword)+"</A>");
					} else {
						page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + vo.getMethdNm()+"</A>");
					}
					page.append("<UL>");
				}
				if ("2".equals(condition)){
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+makeKeywordStronger(vo.getParamtrNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+vo.getParamtrNm()+"</A></LI>");
				}
				if ("2".equals(condition)){
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+makeKeywordStronger(vo.getQuryNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+vo.getQuryNm()+"</A></LI>");
				}
				prevClass = vo.getClassId();
				prevMethod = vo.getMethdId();
				continue;
			}
			if (!vo.getMethdId().equals(prevMethod)) {
				if (prevMethod.length() != 0){
					page.append("</UL></LI>");
				}
				if ("1".equals(condition)) {
					page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + makeKeywordStronger(vo.getMethdNm(), keyword)+"</A>");
				} else {
					page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + vo.getMethdNm()+"</A>");
				}
				page.append("<UL>");
			}
			if(null != vo.getParamtrId()){
				if ("2".equals(condition)){
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+makeKeywordStronger(vo.getParamtrNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+vo.getParamtrNm()+"</A></LI>");
				}
			}
			if(null != vo.getQuryId()){
				if ("2".equals(condition)){
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+makeKeywordStronger(vo.getQuryNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+vo.getQuryNm()+"</A></LI>");
				}
			}
			
			prevMethod = vo.getMethdId();
		}
		
		page.append("</UL></LI>");
		page.append("</UL></LI>");
		page.append("</UL></LI>");
		page.append("</UL>");
		
		model.addAttribute("jsonString", page.toString());
		
		return "cms/arc/ams/EgovJson";
	}

	/**
	 * 아키텍쳐정보 트리 전체 조회
	 * @param 검색조건이 담긴 comVO, condition, keyword
	 * @return "/cms/arc/ams/EgovJson"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getObjectCompnTreeAll.do")
	public String getObjectCompnTreeAll(@ModelAttribute("comVO") EgovOe1ComponentVO comVO, 
			@RequestParam(value="condition",required= false) String condition, 
			@RequestParam(value = "searchKeyword", required = false) String keyword, ModelMap model) throws Exception {
		StringBuffer page = new StringBuffer();

		if (condition!=null)
			comVO.setSearchCondition(condition);
		
		if (keyword != null)
			comVO.setSearchKeyword(keyword);
		List<EgovOe1ArcListItemVO> cvoList = listService.selectArcList(comVO);

		String prevCompn = "";
		String prevClass = "";
		String prevMethod = "";

		page.append("<UL id='treeview' class='filetree'>");
		page.append("<LI>COMPONENT TREE MAP");
		page.append("<UL>");
		
		for (EgovOe1ArcListItemVO vo : cvoList) {
			if (!vo.getCompnId().equals(prevCompn)) {
				
				if (prevCompn.length() != 0) {
					page.append("</UL></LI></UL></LI></UL></LI>");

				}
				prevClass = "";
				prevMethod = "";
				
				page.append("<LI>Comp : ");
				page.append(vo.getCompnNm());
				page.append("<UL>");				
				if (!vo.getClassId().equals(prevClass)) {
					if ("0".equals(condition)) {
						page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+makeKeywordStronger(vo.getClassNm(),keyword)+"</A>");
					} else {
						page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+vo.getClassNm()+"</A>");
					}
					page.append("<UL>");
					
					if (!vo.getMethdId().equals(prevMethod)) {
						if ("1".equals(condition)) {
							page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + makeKeywordStronger(vo.getMethdNm(), keyword)+"</A>");
						} else {
							page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + vo.getMethdNm()+"</A>");
						}
						page.append("<UL>");
					}
					if(null != vo.getParamtrId()){
						if ("2".equals(condition)){
							page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+makeKeywordStronger(vo.getParamtrNm(),keyword)+"</A></LI>");
						} else {
							page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+vo.getParamtrNm()+"</A></LI>");
						}
					}
					if(null != vo.getQuryId()){
						if ("2".equals(condition)){
							page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+makeKeywordStronger(vo.getQuryNm(),keyword)+"</A></LI>");
						} else {
							page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+vo.getQuryNm()+"</A></LI>");
						}
					}
					
				}
				
				prevCompn = vo.getCompnId();
				prevClass = vo.getClassId();
				prevMethod = vo.getMethdId();
				continue;
			}
			if (!vo.getClassId().equals(prevClass)) {
				if (prevClass.length() != 0) {
					page.append("</UL></LI></UL></LI>");
				}
				if ("0".equals(condition)) {
					page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+makeKeywordStronger(vo.getClassNm(),keyword)+"</A>");
				} else {
					page.append("<LI>Class : <A href=\"javascript:fn_openClass('"+vo.getClassId()+"')\">"+vo.getClassNm()+"</A>");
				}
				page.append("<UL>");
				
				if (!vo.getMethdId().equals(prevMethod)) {
					if ("1".equals(condition)) {
						page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + makeKeywordStronger(vo.getMethdNm(), keyword)+"</A>");
					} else {
						page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + vo.getMethdNm()+"</A>");
					}
					page.append("<UL>");
				}
				if(null != vo.getParamtrId()){
					if ("2".equals(condition)){
						page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+makeKeywordStronger(vo.getParamtrNm(),keyword)+"</A></LI>");
					} else {
						page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+vo.getParamtrNm()+"</A></LI>");
					}
				}
				if(null != vo.getQuryId()){
					if ("2".equals(condition)){
						page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+makeKeywordStronger(vo.getQuryNm(),keyword)+"</A></LI>");
					} else {
						page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+vo.getQuryNm()+"</A></LI>");
					}
				}
				
				prevClass = vo.getClassId();
				prevMethod = vo.getMethdId();
				continue;
			}
			if (!vo.getMethdId().equals(prevMethod)) {
				if (prevMethod.length() != 0){
					page.append("</UL></LI>");
				}
				if ("1".equals(condition)) {
					page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + makeKeywordStronger(vo.getMethdNm(), keyword)+"</A>");
				} else {
					page.append("<LI>Method : <A href=\"javascript:fn_openMethod('"+vo.getMethdId()+"')\">" + vo.getMethdNm()+"</A>");
				}
				page.append("<UL>");
			}
			if(null != vo.getParamtrId()){
				if ("2".equals(condition)){
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+makeKeywordStronger(vo.getParamtrNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Param : <A href=\"javascript:fn_openParam('"+vo.getParamtrId()+"')\">"+vo.getParamtrNm()+"</A></LI>");
				}
			}
			if(null != vo.getQuryId()){
				if ("2".equals(condition)){
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+makeKeywordStronger(vo.getQuryNm(),keyword)+"</A></LI>");
				} else {
					page.append("<LI>Query : <A href=\"javascript:fn_openQuery('"+vo.getQuryId()+"')\">"+vo.getQuryNm()+"</A></LI>");
				}
			}
			
			prevMethod = vo.getMethdId();
		}
		
		page.append("</UL></LI>");
		page.append("</UL></LI>");
		page.append("</UL></LI>");
		page.append("</UL>");
		
		if(cvoList.isEmpty()){
			StringBuffer pageEmpty = new StringBuffer();
			
			pageEmpty.append("<UL id='treeview' class='filetree'>");
			pageEmpty.append("<LI>COMPONENT TREE MAP");
			pageEmpty.append("<UL>");
			pageEmpty.append("<LI>해당 데이터가 없습니다 :::<A href=\"javascript:fn_openInit()>전체검색 </A></LI>");
			pageEmpty.append("</LI></UL></LI></UL>");
			
			model.addAttribute("resultMsg", "해당 데이타가 없습니다.");
			//String stTemp = new String(pageEmpty.toString());
			model.addAttribute("jsonString", pageEmpty.toString());
		}else{
			model.addAttribute("jsonString", page.toString());
		}
		
		return "cms/arc/ams/EgovJson";
	}
	

	/**
	 * 클래스/메서드 정보 키워드
	 * @param 검색조건이 담긴 name, keyword
	 * @return name_temp.substring(0, startPoint)+"<strong>"+name_temp.substring(startPoint, endPoint)+"</strong>"+name_temp.substring(endPoint);
	 * 
	 */	
	private String makeKeywordStronger(String name, String keyword) {
		
		String name_temp = "";
		String keyword_temp = "";
		
		if("".equals(name) || null == name || "".equals(keyword) || null == keyword){
			name_temp = EgovStringUtil.isNullToString(name);
			keyword_temp = EgovStringUtil.isNullToString(keyword);
		}else{
			name_temp = name;
			keyword_temp = keyword;
		}
		int startPoint = name_temp.toLowerCase().indexOf(keyword_temp.toLowerCase());
		if (startPoint == -1)
			return name_temp;
		int endPoint = startPoint+keyword_temp.length();
		
		return name_temp.substring(0, startPoint)+"<strong>"+name_temp.substring(startPoint, endPoint)+"</strong>"+name_temp.substring(endPoint);
	}
	
	/**
	 * 컴포넌트 조회
	 * @param 검색조건이 담긴 id
	 * @return "cms/arc/ams/EgovComponentDetail"
	 * @exception Exception
	 */	
	@RequestMapping(value="/cms/arc/ams/getComponentDetail.do")
	public String getComponent(@RequestParam("id") String id, ModelMap model) throws Exception {
		EgovOe1ComponentVO vo = componentService.selectComponent(id);
		model.addAttribute("vo", vo);
		
		return "cms/arc/ams/EgovComponentDetail";	
		
	}
	
	/**
	 * 클래스 조회
	 * @param 검색조건이 담긴 id
	 * @return "cms/arc/ams/EgovClassDetail"
	 * @exception Exception
	 */	
	@RequestMapping(value="/cms/arc/ams/getClassDetail.do")
	public String getClass(@RequestParam("id") String id, ModelMap model) throws Exception {
		EgovOe1ClassVO vo = classService.selectClass(id);
		model.addAttribute("vo", vo);
		
		EgovOe1MethodVO searchVo = new EgovOe1MethodVO();
		
		searchVo.setSearchCondition("1");
		searchVo.setSearchKeyword(id);
		
		List<EgovOe1MethodVO> methodList = methodService.selectMethodList(searchVo);
		model.addAttribute("methdList", methodList);
		
		return "cms/arc/ams/EgovClassDetail";	
		
	}

	/**
	 * 메서드 조회
	 * @param 검색조건이 담긴 id
	 * @return "cms/arc/ams/EgovMethodDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getMethodDetail.do")
	public String getMethod(@RequestParam("id") String id, ModelMap model) throws Exception {
		EgovOe1MethodVO vo = methodService.selectMethod(id);
		model.addAttribute("vo", vo);
		
		EgovOe1ParameterVO searchParamVo = new EgovOe1ParameterVO();
		
		searchParamVo.setSearchCondition("1");
		searchParamVo.setSearchKeyword(id);
		
		List<EgovOe1ParameterVO> paramList = parameterService.selectParameterList(searchParamVo);
		model.addAttribute("paramList", paramList);
		
		EgovOe1QueryVO searchQueryVo = new EgovOe1QueryVO();
		
		searchQueryVo.setSearchCondition("1");
		searchQueryVo.setSearchKeyword(id);
		
		List<EgovOe1QueryVO> queryList = queryService.selectQueryList(searchQueryVo);
		model.addAttribute("queryList", queryList);
		
		EgovOe1MethodCallingVO searchCallVo = new EgovOe1MethodCallingVO();
		
		searchCallVo.setSearchCondition("3");
		searchCallVo.setSearchKeyword(id);
		
		List<EgovOe1MethodCallingVO> callList = methodCallingService.selectMethodCallingList(searchCallVo);
		model.addAttribute("callList", callList);
		
		EgovOe1MethodCallingVO searchCalledVo = new EgovOe1MethodCallingVO();
		
		searchCalledVo.setSearchCondition("1");
		searchCalledVo.setSearchKeyword(id);
		
		List<EgovOe1MethodCallingVO> calledList = methodCallingService.selectMethodCallingList(searchCalledVo);
		model.addAttribute("calledList", calledList);
		
		return "cms/arc/ams/EgovMethodDetail";	
		
	}

	/**
	 * 파라미터 조회
	 * @param 검색조건이 담긴 id
	 * @return "cms/arc/ams/EgovParameterDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getParameterDetail.do")
	public String getParam(@RequestParam("id") String id, ModelMap model) throws Exception {
		EgovOe1ParameterVO vo = parameterService.selectParameter(id);
		model.addAttribute("vo", vo);
		
		return "cms/arc/ams/EgovParameterDetail";	
	}

	/**
	 * 쿼리 조회
	 * @param 검색조건이 담긴 id
	 * @return "cms/arc/ams/EgovQueryDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getQueryDetail.do")
	public String getQuery(@RequestParam("id") String id, ModelMap model) throws Exception {
		EgovOe1QueryVO vo = queryService.selectQuery(id);
		model.addAttribute("vo", vo);
		
		return "cms/arc/ams/EgovQueryDetail";	
	}

	/**
	 * JSON 데이터 처리
	 * @param ModelMap
	 * @return "cms/arc/ams/EgovJson"
	 * @exception Exception
	 */
	@RequestMapping(value="/testJSON.do")
	public String submitJSON(ModelMap model) throws Exception {
		
		return "cms/arc/ams/EgovJson";
	}
	
	/**
	 * JSON 데이터 URL 처리
	 * @param id, name, objectType
	 * @return sb.toString();
	 * @exception Exception
	 */
	private String makeURLString(String id, String name, String objectType) {
		
		StringBuffer sb = new StringBuffer();
		String popupType = "";
		String popupName = "";
		String divClass = "";
		if("1".equals(objectType) || "".equals(objectType) || null == objectType){
			popupType = "fn_openCompo";
			divClass = "nodeComp";
			sb.append(name);
		}else{ 
			if("2".equals(objectType)){
				popupType = "fn_openClass";
				popupName = "Class";
				divClass = "nodeClass";
			}else if("4".equals(objectType)){
				popupType = "fn_openMethod";
				popupName = "Method";
				divClass = "nodeMethod";
			}else if("8".equals(objectType)){
				popupType = "fn_openParam";
				popupName = "Param";
				divClass = "nodeParam";
			}else if("16".equals(objectType)){
				popupType = "fn_openQuery";
				popupName = "Query";
				divClass = "nodeQuery";
			}
			sb.append(popupName+ ": <A href='#Link' onclick="+popupType+"('"+id+"');>"+name+"</A>");
			/*sb.append("<div class='hitarea expandable-hitarea-"+divClass+" lastExpandable-hitarea'></div>" + popupName+" : <A href=javascript:"
					+ popupType+"('"+id+"') onclick='javascrpit:"+popupType+"('"+id+"');>"+name+"</A>");*/
			//sb.append(popupName+" : <A href=javascript:"+ popupType+"('"+id+"')>"+name+"</A>");
			/*sb.append("<div class='hitarea expandable-hitarea lastExpandable-hitarea'></div>" + popupName+" : <A href=javascript:"
					+ popupType+"('"+id+"') onclick='javascrpit:"+popupType+"('"+id+"');>"+name+"</A>");*/
		}
		
		//sb.append("<A href=\"javascript:"+ popupType + "('"+vo  + "')\">"+vo.getParamNm()+"</A></LI>");
		return sb.toString();
	}
	/*
	 * { "realFileName": "<%= URLEncoder.encode(fileName, "UTF-8") %>" }
	 */
	
	/**
	 * JSON 데이터 처리
	 * @param jsonArray
	 * @return sb.toString();
	 * @exception Exception
	 */
	private String makeJSONString(List<JSONObject> jsonArray) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (JSONObject o : jsonArray) {
			sb.append("{");
			for (Object obj : o.keySet()) {
				String value = (String)o.get(obj);
				sb.append("\""+obj.toString()+"\":\""+value+"\",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			sb.append("},");
		}
		sb.deleteCharAt(sb.length()-1);	
		sb.append("]");
		return sb.toString();
	}
	

}