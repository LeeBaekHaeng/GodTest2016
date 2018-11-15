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
package egovframework.oe1.cms.com.service;

import java.util.List;
import java.util.Map;

/**
 * 파일정보의 관리를 위한 서비스 인터페이스
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
public interface EgovOe1FileMngService {

    /**
     * selectFileInfs
     * @param fvo
     * @return
     * @throws Exception
     */
    public List selectFileInfs(EgovOe1FileVO fvo) throws Exception;

    /**
     * insertFileInf
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf(EgovOe1FileVO fvo) throws Exception;

    /**
     * insertFileInfs
     * @param fvoList
     * @throws Exception
     */
    public String insertFileInfs(List fvoList) throws Exception;

    /**
     * updateFileInfs
     * @param fvoList
     * @throws Exception
     */
    public void updateFileInfs(List fvoList) throws Exception;

    /**
     * deleteFileInfs
     * @param fvoList
     * @throws Exception
     */
    public void deleteFileInfs(List fvoList) throws Exception;

    /**
     * deleteFileInf
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(EgovOe1FileVO fvo) throws Exception;

    /**
     * selectFileInf
     * @param fvo
     * @return
     * @throws Exception
     */
    public EgovOe1FileVO selectFileInf(EgovOe1FileVO fvo) throws Exception;

    /**
     * getMaxFileSN
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(EgovOe1FileVO fvo) throws Exception;

    /**
     * deleteAllFileInf
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(EgovOe1FileVO fvo) throws Exception;

    /**
     * selectFileListByFileNm
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm(EgovOe1FileVO fvo)
            throws Exception;

    /**
     * selectImageFileList
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectImageFileList(EgovOe1FileVO vo) throws Exception;

    /**
     * insertFileDown
     * @param fvo
     * @throws Exception
     */
    public void insertFileDown(EgovOe1FileDownVO fvo) throws Exception;

}
