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
package egovframework.oe1.cms.com.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileDownVO;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 파일정보의 관리를 위한 구현 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("EgovFileMngService")
public class EgovOe1FileMngServiceImpl extends AbstractServiceImpl implements
        EgovOe1FileMngService {
    /** FileManageDAO */
    @Resource(name = "FileManageDAO")
    private EgovOe1FileManageDAO fileMngDAO;
    /** LOG */
    Logger log = Logger.getLogger(this.getClass());

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #deleteFileInfs(java.util.List)
     */
    public void deleteFileInfs(List fvoList) throws Exception {
        fileMngDAO.deleteFileInfs(fvoList);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * insertFileInf(egovframework.adt.cmm.service.FileVO
     * )
     */
    public String insertFileInf(EgovOe1FileVO fvo) throws Exception {
        String _atchFileId = fvo.getAtchFileId();
        fileMngDAO.insertFileInf(fvo);
        return _atchFileId;
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #insertFileInfs(java.util.List)
     */
    public String insertFileInfs(List fvoList) throws Exception {
        String _atchFileId = "";

        if (fvoList.size() != 0) {
            _atchFileId = fileMngDAO.insertFileInfs(fvoList);
        }
        return _atchFileId;
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * selectFileInfs(egovframework.adt.cmm.service.FileVO
     * )
     */
    public List selectFileInfs(EgovOe1FileVO fvo) throws Exception {
        return fileMngDAO.selectFileInfs(fvo);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #updateFileInfs(java.util.List)
     */
    public void updateFileInfs(List fvoList) throws Exception {
        // Delete & Insert
        fileMngDAO.updateFileInfs(fvoList);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * deleteFileInf(egovframework.adt.cmm.service.FileVO
     * )
     */
    public void deleteFileInf(EgovOe1FileVO fvo) throws Exception {
        deleteFile(fvo);
        fileMngDAO.deleteFileInf(fvo);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #물리적파일삭제
     * deleteFile(egovframework.adt.cmm.service.FileVO
     * )
     */
    public void deleteFile(EgovOe1FileVO fvo) throws Exception {
        EgovOe1FileVO fileVO = (EgovOe1FileVO) fileMngDAO.selectFileInf(fvo);
        File file =
            new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
        file.delete();
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * selectFileInf(egovframework.adt.cmm.service.FileVO
     * )
     */
    public EgovOe1FileVO selectFileInf(EgovOe1FileVO fvo) throws Exception {
        // TODO Auto-generated method stub
        return fileMngDAO.selectFileInf(fvo);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * getMaxFileSN(egovframework.adt.cmm.service.FileVO
     * )
     */
    public int getMaxFileSN(EgovOe1FileVO fvo) throws Exception {
        return fileMngDAO.getMaxFileSN(fvo);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #deleteAllFileInf(egovframework.adt.cmm.service.
     * FileVO)
     */
    public void deleteAllFileInf(EgovOe1FileVO fvo) throws Exception {
        List fileList = fileMngDAO.selectFileInfs(fvo);
        Iterator _iter = fileList.iterator();
        EgovOe1FileVO vo;
        while (_iter.hasNext()) {
            vo = (EgovOe1FileVO) _iter.next();
            deleteFile(vo);
        }
        fileMngDAO.deleteFileInfs(fileList);
        fileMngDAO.deleteAllFileInf(fvo);
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * selectFileListByFileNm(egovframework.adt.cmm.service
     * .FileVO)
     */
    public Map<String, Object> selectFileListByFileNm(EgovOe1FileVO fvo)
            throws Exception {

        List _result = fileMngDAO.selectFileListByFileNm(fvo);
        int _cnt = fileMngDAO.selectFileListCntByFileNm(fvo);

        log.debug(this.getClass().getName() + " _cnt " + _cnt);

        Map<String, Object> _map = new HashMap();
        _map.put("resultList", _result);
        _map.put("resultCnt", Integer.toString(_cnt));

        return _map;
    }

    /*
     * (non-Javadoc)
     * @see
     * egovframework.adt.cmm.service.EgovFileMngService
     * #
     * selectImageFileList(egovframework.adt.cmm.service
     * .FileVO)
     */
    public List selectImageFileList(EgovOe1FileVO vo) throws Exception {
        return fileMngDAO.selectImageFileList(vo);
    }

    /*
     * insertFileDown
     */
    public void insertFileDown(EgovOe1FileDownVO fvo) throws Exception {
        fileMngDAO.insertFileDown(fvo);
    }

}
