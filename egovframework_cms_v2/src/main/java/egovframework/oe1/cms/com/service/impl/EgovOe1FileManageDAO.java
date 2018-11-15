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

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1FileDownVO;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 파일정보 관리를 위한 데이터 처리 클래스
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
@Repository("FileManageDAO")
public class EgovOe1FileManageDAO extends EgovAbstractDAO {
    /** LOG */
    Logger log = Logger.getLogger(this.getClass());

    /**
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List fileList) throws Exception {

        String _atchFileId = "";
        EgovOe1FileVO vo;
        vo = (EgovOe1FileVO) fileList.get(0);
        _atchFileId = vo.getAtchFileId();
        insert("FileManageDAO.insertFileMaster", vo);

        Iterator _iter = fileList.iterator();
        while (_iter.hasNext()) {
            vo = (EgovOe1FileVO) _iter.next();
            insert("FileManageDAO.insertFileDetail", vo);
        }
        return _atchFileId;
    }

    /**
     * @param vo
     * @throws Exception
     */
    public void insertFileInf(EgovOe1FileVO vo) throws Exception {
        insert("FileManageDAO.insertFileMaster", vo);
        insert("FileManageDAO.insertFileDetail", vo);
    }

    /**
     * @param fileList
     * @throws Exception
     */
    public void updateFileInfs(List fileList) throws Exception {
        EgovOe1FileVO vo;
        Iterator _iter = fileList.iterator();
        while (_iter.hasNext()) {
            vo = (EgovOe1FileVO) _iter.next();
            insert("FileManageDAO.insertFileDetail", vo);
        }
    }

    /**
     * @param fileList
     * @throws Exception
     */
    public void deleteFileInfs(List fileList) throws Exception {

        Iterator _iter = fileList.iterator();
        EgovOe1FileVO vo;
        while (_iter.hasNext()) {
            vo = (EgovOe1FileVO) _iter.next();
            delete("FileManageDAO.deleteFileDetail", vo);
        }
    }

    /**
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(EgovOe1FileVO fvo) throws Exception {
        delete("FileManageDAO.deleteFileDetail", fvo);
    }

    /**
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectFileInfs(EgovOe1FileVO vo) throws Exception {

        log.debug(this.getClass().getName()
            + " selectFileInfs vo.getAtchFileId() " + vo.getAtchFileId());
        return list("FileManageDAO.selectFileList", vo);
    }

    /**
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(EgovOe1FileVO fvo) throws Exception {

        log.debug(this.getClass().getName()
            + " getMaxFileSN fvo.getAtchFileId() " + fvo.getAtchFileId());
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "FileManageDAO.getMaxFileSN", fvo);
    }

    /**
     * @param fvo
     * @return
     * @throws Exception
     */
    public EgovOe1FileVO selectFileInf(EgovOe1FileVO fvo) throws Exception {
        return (EgovOe1FileVO) selectByPk("FileManageDAO.selectFileInf", fvo);
    }

    /**
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(EgovOe1FileVO fvo) throws Exception {
        update("FileManageDAO.deleteCOMTNFILE", fvo);
    }

    /**
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectFileListByFileNm(EgovOe1FileVO fvo) throws Exception {
        log.debug(this.getClass().getName()
            + " selectFileListByFileNm vo.getAtchFileId() "
            + fvo.getAtchFileId());
        return list("FileManageDAO.selectFileListByFileNm", fvo);
    }

    /**
     * @param fvo
     * @return
     * @throws Exception
     */
    public int selectFileListCntByFileNm(EgovOe1FileVO fvo) throws Exception {
        log.debug(this.getClass().getName()
            + " selectFileListCntByFileNm fvo.getAtchFileId() "
            + fvo.getAtchFileId());
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "FileManageDAO.selectFileListCntByFileNm", fvo);
    }

    /**
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectImageFileList(EgovOe1FileVO vo) throws Exception {
        log.debug(this.getClass().getName()
            + " selectImageFileList vo.getAtchFileId() " + vo.getAtchFileId());
        return list("FileManageDAO.selectImageFileList", vo);
    }

    /**
     * @param vo
     * @throws Exception
     */
    public void insertFileDown(EgovOe1FileDownVO vo) throws Exception {
        insert("FileManageDAO.insertFileDown", vo);
    }

}
