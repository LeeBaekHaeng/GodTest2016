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
package egovframework.oe1.cms.sys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1BBSAttributeManageService;
import egovframework.oe1.cms.sys.service.EgovOe1BBSManageService;
import egovframework.oe1.cms.sys.service.EgovOe1Board;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMaster;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.oe1.cms.sys.service.EgovOe1BoardVO;
import egovframework.oe1.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovOe1BBSManageController {

    // EgovOe1BBSManageService
    @Resource(name = "EgovBBSManageService")
    private EgovOe1BBSManageService bbsMngService;

    // EgovOe1BBSAttributeManageService
    @Resource(name = "BBSAttributeManageService")
    private EgovOe1BBSAttributeManageService bbsAttrbService;

    // EgovOe1FileMngService
    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileMngService;

    // EgovOe1FileMngUtil
    @Resource(name = "EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;

    // EgovPropertyService
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    // EgovOe1MessageSource
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    // beanValidator
    @Autowired
    private DefaultBeanValidator beanValidator;

    // Logger
    Logger log = Logger.getLogger(this.getClass());

    /**
     * XSS 방지 처리.
     * @param data
     * @return
     */
    protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }

        String ret = data;

        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;_script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/_script");

        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;_object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/_object");

        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;_applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/_applet");

        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;_embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;/_embed");

        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;_form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;/_form");

        ret = ret.replaceAll("<(I|i)(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;_iframe");
        ret = ret.replaceAll("</(I|i)(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;/_iframe");
        
        ret = ret.replaceAll("(J|j)(A|a)(V|v)(A|a)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "[java-script]");


        ret = ret.replaceAll("(O|o)(N|n)(A|a)(B|b)(O|o)(R|r)(T|t)"                      , "[on-abort]"      );
        ret = ret.replaceAll("(O|o)(N|n)(B|b)(L|l)(U|u)(R|r)"                           , "[on-blur]"       );
        ret = ret.replaceAll("(O|o)(N|n)(C|c)(H|h)(A|a)(N|n)(G|g)(E|e)"                 , "[on-change]"     );
        ret = ret.replaceAll("(O|o)(N|n)(C|c)(L|l)(I|i)(C|c)(K|k)"                      , "[on-click]"      );
        ret = ret.replaceAll("(O|o)(N|n)(D|d)(B|b)(L|l)(C|c)(L|l)(I|i)(C|c)(K|k)"       , "[on-dblclick]"   );
        ret = ret.replaceAll("(O|o)(N|n)(D|d)(R|r)(A|a)(G|g)(D|d)(R|r)(O|o)(P|p)"       , "[on-dragdrop]"   );
        ret = ret.replaceAll("(O|o)(N|n)(E|e)(R|r)(R|r)(O|o)(R|r)"                      , "[on-error]"      );
        ret = ret.replaceAll("(O|o)(N|n)(F|f)(O|o)(C|c)(U|u)(S|s)"                      , "[on-focus]"      );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)(M|m)(O|o)(V|v)(E|e)"  , "[on-mousemove]"  );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)(O|o)(U|u)(T|t)"       , "[on-mouseout]"   );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)(O|o)(V|v)(E|e)(R|r)"  , "[on-mouseover]"  );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)(U|u)(P|p)"            , "[on-mouseup]"    );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)(D|d)(O|o)(W|w)(N|n)"  , "[on-mousedown]"  );
        ret = ret.replaceAll("(O|o)(N|n)(M|m)(O|o)(V|v)(E|e)"                           , "[on-move]"       );
        ret = ret.replaceAll("(O|o)(N|n)(R|r)(E|e)(S|s)(E|e)(T|t)"                      , "[on-reset]"      );
        ret = ret.replaceAll("(O|o)(N|n)(R|r)(E|e)(S|s)(I|i)(Z|z)(E|e)"                 , "[on-resize]"     );
        ret = ret.replaceAll("(O|o)(N|n)(K|k)(E|e)(Y|y)(P|p)(R|r)(E|e)(S|s)(S|s)"       , "[on-keypress"    );
        ret = ret.replaceAll("(O|o)(N|n)(K|k)(E|e)(Y|y)(U|u)(P|p)"                      , "[on-keyup]"      );
        ret = ret.replaceAll("(O|o)(N|n)(K|k)(E|e)(Y|y)(D|d)(O|o)(W|w)(N|n)"            , "[on-keydown]"    );
        ret = ret.replaceAll("(O|o)(N|n)(L|l)(O|o)(A|a)(D|d)"                           , "[on-load]"       );
        ret = ret.replaceAll("(O|o)(N|n)(S|s)(E|e)(L|l)(E|e)(C|c)(T|t)"                 , "[on-select]"     );
        ret = ret.replaceAll("(O|o)(N|n)(S|s)(U|u)(B|b)(M|m)(I|i)(T|t)"                 , "[on-submit]"     );
        ret = ret.replaceAll("(O|o)(N|n)(U|u)(N|n)(L|l)(O|o)(A|a)(D|d)"                 , "[on-unload]"     );

        ret = ret.replaceAll("&#" , "[&-#]");

        
        return ret;
    }

    /**
     * 게시물에 대한 목록을 조회한다.
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/selectBoardList.do")
    public String selectBoardArticles(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO, ModelMap model)
            throws Exception {

        EgovOe1LoginVO user = new EgovOe1LoginVO();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            user =
                (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        }

        boardVO.setBbsId(boardVO.getBbsId());
        boardVO.setBbsNm(boardVO.getBbsNm());

        EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();

        vo.setBbsId(boardVO.getBbsId());
        vo.setMberId(user.getMberId());

        EgovOe1BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);

        boardVO.setPageUnit(propertyService.getInt("pageUnit"));
        boardVO.setPageSize(propertyService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
        paginationInfo.setPageSize(boardVO.getPageSize());

        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        Map<String, Object> map =
            bbsMngService.selectBoardArticles(boardVO, vo.getBbsAttrbCode());
        int totCnt = Integer.parseInt((String) map.get("resultCnt"));

        paginationInfo.setTotalRecordCount(totCnt);

        if (master.getTmplatCours() == null
            || master.getTmplatCours().equals("")) {
            master.setTmplatCours("/css/egovframework/oe1/cms/com/common.css");
        }

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultCnt", map.get("resultCnt"));
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("brdMstrVO", master);
        model.addAttribute("paginationInfo", paginationInfo);

        return "cms/cmm/EgovNoticeList";
    }

    /**
     * 게시물에 대한 상세 정보를 조회한다.
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/selectBoardArticle.do")
    public String selectBoardArticle(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO, ModelMap model)
            throws Exception {
        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        // 조회수 증가 여부 지정
        if(user.getMberId().equals(boardVO.getFrstRegisterId())){
        	boardVO.setPlusCount(false);
        }else{
        	boardVO.setPlusCount(true);
        }

        if (!boardVO.getSubPageIndex().equals("")) {
            boardVO.setPlusCount(false);
        }

        boardVO.setLastUpdusrId(user.getMberId());
        EgovOe1BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

        model.addAttribute("result", vo);

        model.addAttribute("sessionUniqId", user.getMberId());

        EgovOe1BoardMasterVO master = new EgovOe1BoardMasterVO();

        master.setBbsId(boardVO.getBbsId());
        master.setMberId(user.getMberId());

        EgovOe1BoardMasterVO masterVo =
            bbsAttrbService.selectBBSMasterInf(master);

        if (masterVo.getTmplatCours() == null
            || masterVo.getTmplatCours().equals("")) {
            masterVo
                .setTmplatCours("/css/egovframework/cop/bbs/egovBaseTemplate.css");
        }

        model.addAttribute("brdMstrVO", masterVo);
        model.addAttribute("searchVO", boardVO);

        return "cms/cmm/EgovNoticeInqire";
    }

    /**
     * 게시물 등록을 위한 등록페이지로 이동한다.
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/addBoardArticle.do")
    public String addBoardArticle(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO, ModelMap model)
            throws Exception {
        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        EgovOe1BoardMasterVO bdMstr = new EgovOe1BoardMasterVO();

        if (isAuthenticated) {

            EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();
            vo.setBbsId(boardVO.getBbsId());
            vo.setMberId(user.getMberId());

            bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
            model.addAttribute("bdMstr", bdMstr);
        }

        if (bdMstr.getTmplatCours() == null
            || bdMstr.getTmplatCours().equals("")) {
            bdMstr
                .setTmplatCours("/css/egovframework/cop/bbs/egovBaseTemplate.css");
        }

        model.addAttribute("brdMstrVO", bdMstr);

        return "cms/cmm/EgovNoticeRegist";
    }

    /**
     * 게시물을 등록한다.
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cms/cmm/insertBoardArticle.do")
    public String insertBoardArticle(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO,
            @ModelAttribute("bdMstr") EgovOe1BoardMaster bdMstr,
            @ModelAttribute("board") EgovOe1Board board,
            BindingResult bindingResult, SessionStatus status, ModelMap model)
            throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        beanValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {

            EgovOe1BoardMasterVO master = new EgovOe1BoardMasterVO();
            EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();

            vo.setBbsId(boardVO.getBbsId());
            vo.setMberId(user.getMberId());

            master = bbsAttrbService.selectBBSMasterInf(vo);

            model.addAttribute("bdMstr", master);

            model.addAttribute("brdMstrVO", master);

            return "cms/cmm/EgovNoticeRegist";
        }

        if (isAuthenticated) {
            List<EgovOe1FileVO> result = null;
            String atchFileId = "";

            final Map<String, MultipartFile> files = multiRequest.getFileMap();
            if (!files.isEmpty()) {
                result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
            }
            board.setAtchFileId(atchFileId);
            board.setFrstRegisterId(user.getMberId());
            board.setLastUpdusrId(user.getMberId());

            board.setBbsId(board.getBbsId());

            board.setNttCn(unscript(board.getNttCn())); // XSS
            // 방지

            bbsMngService.insertBoardArticle(board);
        }

        return "forward:/cms/cmm/selectBoardList.do";
    }

    /**
     * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/addReplyBoardArticle.do")
    public String addReplyBoardArticle(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO, ModelMap model)
            throws Exception {
        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        EgovOe1BoardMasterVO master = new EgovOe1BoardMasterVO();
        EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();

        vo.setBbsId(boardVO.getBbsId());
        vo.setMberId(user.getMberId());

        master = bbsAttrbService.selectBBSMasterInf(vo);

        model.addAttribute("bdMstr", master);
        model.addAttribute("result", boardVO);

        model.addAttribute("brdMstrVO", master);

        return "/cms/cmm/EgovNoticeReply";
    }

    /**
     * 게시물에 대한 답변을 등록한다.
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cms/cmm/replyBoardArticle.do")
    public String replyBoardArticle(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO,
            @ModelAttribute("bdMstr") EgovOe1BoardMaster bdMstr,
            @ModelAttribute("board") EgovOe1Board board,
            BindingResult bindingResult, ModelMap model, SessionStatus status)
            throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        beanValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            EgovOe1BoardMasterVO master = new EgovOe1BoardMasterVO();
            EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();

            vo.setBbsId(boardVO.getBbsId());
            vo.setMberId(user.getMberId());

            master = bbsAttrbService.selectBBSMasterInf(vo);

            model.addAttribute("bdMstr", master);
            model.addAttribute("result", boardVO);

            model.addAttribute("brdMstrVO", master);

            return "/cms/cmm/EgovNoticeReply";
        }

        if (isAuthenticated) {
            final Map<String, MultipartFile> files = multiRequest.getFileMap();
            String atchFileId = "";

            if (!files.isEmpty()) {
                List<EgovOe1FileVO> result =
                    fileUtil.parseFileInf(files, "BBS_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
            }

            board.setAtchFileId(atchFileId);
            board.setReplyAt("Y");
            board.setFrstRegisterId(user.getMberId());
            board.setLastUpdusrId(user.getMberId());

            board.setBbsId(board.getBbsId());
            board.setParnts(Long.toString(boardVO.getNttId()));
            board.setSortOrdr(boardVO.getSortOrdr());
            board.setReplyLc(Integer.toString(Integer.parseInt(boardVO
                .getReplyLc()) + 1));

            board.setNtcrNm(""); // dummy 오류 수정 (익명이 아닌
            // 경우 validator 처리를 위해
            // dummy로 지정됨)
            board.setPassword(""); // dummy 오류 수정 (익명이
            // 아닌 경우 validator
            // 처리를 위해 dummy로
            // 지정됨)

            board.setNttCn(unscript(board.getNttCn())); // XSS
            // 방지

            bbsMngService.insertBoardArticle(board);
        }

        return "forward:/cms/cmm/selectBoardList.do";
    }

    /**
     * 게시물 수정을 위한 수정페이지로 이동한다.
     * @param boardVO
     * @param vo
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/forUpdateBoardArticle.do")
    public String selectBoardArticleForUpdt(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO,
            @ModelAttribute("board") EgovOe1BoardVO vo, ModelMap model)
            throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        boardVO.setFrstRegisterId(user.getMberId());

        EgovOe1BoardMaster master = new EgovOe1BoardMaster();
        EgovOe1BoardMasterVO bmvo = new EgovOe1BoardMasterVO();
        EgovOe1BoardVO bdvo = new EgovOe1BoardVO();

        vo.setBbsId(boardVO.getBbsId());

        master.setBbsId(boardVO.getBbsId());

        if (isAuthenticated) {

            master.setMberId(user.getMberId());

            bmvo = bbsAttrbService.selectBBSMasterInf(master);
            bdvo = bbsMngService.selectBoardArticle(boardVO);
            
            if(!bdvo.getFrstRegisterId().equals(user.getMberId())){
                return "forward:/cms/cmm/selectBoardArticle.do";
            }
        }

        model.addAttribute("result", bdvo);
        model.addAttribute("bdMstr", bmvo);

        model.addAttribute("brdMstrVO", bmvo);

        return "cms/cmm/EgovNoticeUpdt";
    }

    /**
     * 게시물에 대한 내용을 수정한다.
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cms/cmm/updateBoardArticle.do")
    public String updateBoardArticle(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO,
            @ModelAttribute("bdMstr") EgovOe1BoardMaster bdMstr,
            @ModelAttribute("board") EgovOe1Board board,
            BindingResult bindingResult, ModelMap model, SessionStatus status)
            throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        String atchFileId = boardVO.getAtchFileId();

        beanValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {

            boardVO.setFrstRegisterId(board.getFrstRegisterId()); // 확인해야함

            EgovOe1BoardMaster master = new EgovOe1BoardMaster();
            EgovOe1BoardMasterVO bmvo = new EgovOe1BoardMasterVO();
            EgovOe1BoardVO bdvo = new EgovOe1BoardVO();

            master.setBbsId(boardVO.getBbsId());
            master.setMberId(user.getMberId());

            bmvo = bbsAttrbService.selectBBSMasterInf(master);
            bdvo = bbsMngService.selectBoardArticle(boardVO);

            model.addAttribute("result", bdvo);
            model.addAttribute("bdMstr", bmvo);

            return "cms/cmm/EgovNoticeUpdt";
        }

        if (isAuthenticated) {
            final Map<String, MultipartFile> files = multiRequest.getFileMap();
            if (!files.isEmpty()) {
                if ("".equals(atchFileId)) {
                    List<EgovOe1FileVO> result =
                        fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
                    atchFileId = fileMngService.insertFileInfs(result);
                    board.setAtchFileId(atchFileId);
                } else {
                    EgovOe1FileVO fvo = new EgovOe1FileVO();
                    fvo.setAtchFileId(atchFileId);
                    int cnt = fileMngService.getMaxFileSN(fvo);
                    List<EgovOe1FileVO> _result =
                        fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId,
                            "");

                    fileMngService.updateFileInfs(_result);
                }
            }

            board.setLastUpdusrId(user.getMberId());

            board.setNtcrNm(""); // dummy 오류 수정 (익명이 아닌
            // 경우 validator 처리를 위해
            // dummy로 지정됨)
            board.setPassword(""); // dummy 오류 수정 (익명이
            // 아닌 경우 validator
            // 처리를 위해 dummy로
            // 지정됨)

            board.setNttCn(unscript(board.getNttCn())); // XSS
            // 방지

            bbsMngService.updateBoardArticle(board);
        }

        return "forward:/cms/cmm/selectBoardList.do";
    }

    /**
     * 게시물에 대한 내용을 삭제한다.
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/deleteBoardArticle.do")
    public String deleteBoardArticle(
            @ModelAttribute("searchVO") EgovOe1BoardVO boardVO,
            @ModelAttribute("board") EgovOe1Board board,
            @ModelAttribute("bdMstr") EgovOe1BoardMaster bdMstr, ModelMap model)
            throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (isAuthenticated) {
            board.setLastUpdusrId(user.getMberId());

            bbsMngService.deleteBoardArticle(board);
        }

        return "forward:/cms/cmm/selectBoardList.do";
    }

    /**
     * 게시물에 대한 목록을 조회한다.
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/com/selectBoardMainList.do")
    public String selectBoardMainArticles(
            @ModelAttribute("boardVO1") EgovOe1BoardVO boardVO1,
            @ModelAttribute("boardVO2") EgovOe1BoardVO boardVO2,
            @ModelAttribute("boardVO3") EgovOe1BoardVO boardVO3, ModelMap model)
            throws Exception {
        // EgovOe1LoginVO user =
        // (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String bbsId = "";
        EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();

        boardVO1.setFirstIndex(0);
        boardVO1.setRecordCountPerPage(5);

        boardVO2.setFirstIndex(0);
        boardVO2.setRecordCountPerPage(5);

        boardVO3.setFirstIndex(0);
        boardVO3.setRecordCountPerPage(5);

        bbsId = "BBSMSTR_000000000092";
        boardVO1.setBbsId(bbsId);
        vo.setBbsId(boardVO1.getBbsId());
        // vo.setMberId(user.getMberId());
        Map<String, Object> map1 =
            bbsMngService.selectBoardArticles(boardVO1, vo.getBbsAttrbCode());
        // EgovOe1BoardMasterVO master1 =
        // bbsAttrbService.selectBBSMasterInf(vo);

        bbsId = "BBSMSTR_000000000093";
        boardVO2.setBbsId(bbsId);
        vo.setBbsId(boardVO2.getBbsId());
        // vo.setMberId(user.getMberId());
        Map<String, Object> map2 =
            bbsMngService.selectBoardArticles(boardVO2, vo.getBbsAttrbCode());
        // EgovOe1BoardMasterVO master2 =
        // bbsAttrbService.selectBBSMasterInf(vo);

        bbsId = "BBSMSTR_000000000094";
        boardVO3.setBbsId(bbsId);
        vo.setBbsId(boardVO3.getBbsId());
        // vo.setMberId(user.getMberId());
        Map<String, Object> map3 =
            bbsMngService.selectBoardArticles(boardVO3, vo.getBbsAttrbCode());
        // EgovOe1BoardMasterVO master3 =
        // bbsAttrbService.selectBBSMasterInf(vo);

        model.addAttribute("boardVO1", boardVO1);
        model.addAttribute("boardVO2", boardVO2);
        model.addAttribute("boardVO3", boardVO3);

        model.addAttribute("resultList1", map1.get("resultList"));
        model.addAttribute("resultList2", map2.get("resultList"));
        model.addAttribute("resultList3", map3.get("resultList"));

        return "/cms/com/EgovMainHome";
    }

}
