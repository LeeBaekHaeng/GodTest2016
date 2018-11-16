package egovframework.com.cmm.web;

import java.util.List;
import java.util.Map;

import egovframework.bopr.bam.service.BatchDlbrtVO;
import egovframework.bopr.bam.service.EgovBatchDlbrtService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.25  이삼섭          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovMultiFileMngController {
	
	@Resource(name = "egovBatchDlbrtService")
	private EgovBatchDlbrtService egovBatchDlbrtService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    /**
     * 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/com/cmm/fms/selectMultiFileInfs.do")
    public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @CommandMap Map<String, Object> commandMap, ModelMap model) throws Exception {
	String atchFileId = (String)commandMap.get("param_atchFileId");
	String atchSeCode = (String)commandMap.get("param_atchSeCode");
	String batchPath = (String)commandMap.get("param_batchPath");
	String atchProcessSeCode = (String)commandMap.get("param_atchProcessSeCode");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectFileInfs(fileVO);

	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "N");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	model.addAttribute("atchSeCode", atchSeCode);
	model.addAttribute("batchPath", batchPath);
	model.addAttribute("atchProcessSeCode", atchProcessSeCode);
	
	return "egovframework/com/cmm/fms/EgovMultiFileList";
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/com/cmm/fms/selectMultiFileInfsForUpdate.do")
    public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, @CommandMap Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("param_atchFileId");
	String atchSeCode = (String)commandMap.get("param_atchSeCode");
	String batchPath = (String)commandMap.get("param_batchPath");
	String atchProcessSeCode = (String)commandMap.get("param_atchProcessSeCode");

	fileVO.setAtchFileId(atchFileId);

	List<FileVO> result = fileService.selectFileInfs(fileVO);
	
	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "Y");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	model.addAttribute("atchSeCode", atchSeCode);
	model.addAttribute("batchPath", batchPath);
	model.addAttribute("atchProcessSeCode", atchProcessSeCode);
	
	return "egovframework/com/cmm/fms/EgovMultiFileList";
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     * 
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/com/cmm/fms/deleteMultiFileInfs.do")
    public String deleteFileInf(@RequestParam("delAtchFileId") String delAtchFileId,
    		                    @RequestParam("delFileSn") String delFileSn,
    		                    @RequestParam("returnUrl") String returnUrl,
	    //SessionVO sessionVO,
	    HttpServletRequest request,
	    ModelMap model) throws Exception {

	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
	FileVO fileVO = new FileVO();
	BatchDlbrtVO batchDlbrtVO = new BatchDlbrtVO();
	
	fileVO.setAtchFileId(delAtchFileId);
	fileVO.setFileSn(delFileSn);
	batchDlbrtVO.setAtchFileId(delAtchFileId);
	if (isAuthenticated) {
	    fileService.deleteFileInf(fileVO);
	    egovBatchDlbrtService.deleteBatchDlbrtAtch(batchDlbrtVO);
	}
	 
	//--------------------------------------------
	// contextRoot가 있는 경우 제외 시켜야 함
	//--------------------------------------------
	////return "forward:/com/cmm/fms/selectFileInfs.do";
	//return "forward:" + returnUrl;
	
	if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
	    return "forward:" + returnUrl;
	}
	
	if (returnUrl.startsWith(request.getContextPath())) {
	    return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
	} else {
	    return "forward:" + returnUrl;
	}
	////------------------------------------------
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/com/cmm/fms/selectMultiImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, @CommandMap Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectImageFileList(fileVO);
	
	model.addAttribute("fileList", result);

	return "egovframework/com/cmm/fms/EgovImgFileList";
    }
}
