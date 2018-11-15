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
package egovframework.oe1.cms.cmm.notify.email.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메일전송의 데이터를 처리하는 DAO 클래스
 * <p>
 * <b>NOTE:</b>
 * @author 관리환경 개발팀 윤성종
 * @since 2009.08.19
 * @version 1.0.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.19  윤성종          최초 생성
 * 
 * </pre>
 */
@Repository("egovOe1SSLMailDAO")
public class EgovOe1SSLMailDAO extends EgovAbstractDAO {
	Log log = LogFactory.getLog(this.getClass());


    /**
     * 메일전송을 위해 첨부될 파일리스트 조회
     *
     * @param fileNames 파일ID
     * @return
     * @throws Exception
    */
    public List<EgovMap> selectFileList(List<String> fileNames) throws Exception {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("atchFileIds", fileNames);
        log.debug(this.getClass() + " selectFileList : " + map.toString());
        return list("oe1SSLMailDAO.selectFileList", map);
    }

}
