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
package egovframework.oe1.cms.cmm.notify.email.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 계정을 처리하는 비즈니스 구현 클래스
 * <p><b>NOTE:</b> Exception 종류를 EgovBizException, RuntimeException 에서만 동작한다.
 * fail.common.msg 메세지키가 Message Resource 에 정의 되어 있어야 한다.
 * @author 관리환경 개발팀 윤성종
 * @since 2009.08.19
 * @version 1.0.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.19  윤성종          최초 생성
 *
 * </pre>
 */
public class EgovOe1SndngMailVO {
	 /** 제목 */
    private String sj;
    
    /** 발송결과코드 */
    private boolean sndngResult;
    
    /** 메일내용 */
    private String emailCn;
    
    /** atchFileIds */
    private List<String> atchFileIds;
    
    /** recptnPersons */
    private List<String> recptnPersons;
    
    /**
     * getSj
     *
     * @return
     * @see egovframework.mgt.cmm.ems.service.SndngMailVO#getSj()
    */
    public String getSj() {
        return sj;
    }

    /**
     * setSj
     *
     * @param sj
     * @see egovframework.mgt.cmm.ems.service.SndngMailVO#setSj(java.lang.String)
    */
    public void setSj(String sj) {
        this.sj = sj;
    }

    /**
     * getSndngResultCode
     *
     * @return
     * @see egovframework.mgt.cmm.ems.service.SndngMailVO#getSndngResultCode()
    */
    public boolean getSndngResult() {
        return sndngResult;
    }

    /**
     * setSndngResultCode
     *
     * @param sndngResultCode
     * @see egovframework.mgt.cmm.ems.service.SndngMailVO#setSndngResultCode(java.lang.String)
    */
    public void setSndngResult(boolean sndngResult) {
        this.sndngResult = sndngResult;
    }

    /**
     * getEmailCn
     *
     * @return
     * @see egovframework.mgt.cmm.ems.service.SndngMailVO#getEmailCn()
    */
    public String getEmailCn() {
        return emailCn;
    }

    public void setEmailCn(String emailCn) {
        this.emailCn = emailCn;
    }

    public List<String> getAtchFileIds() {
        return atchFileIds;
    }

    public void setAtchFileIds(List<String> atchFileIds) {
        this.atchFileIds = atchFileIds;
    }

    public List<String> getRecptnPersons() {
        return recptnPersons;
    }

    public void setRecptnPersons(List<String> recptnPersons) {
        this.recptnPersons = recptnPersons;
    }

    public void setRecptnPerson(String recptnPerson) {
        List<String> person = new ArrayList<String>();
        person.add(recptnPerson);
        setRecptnPersons(person);
    }
}
