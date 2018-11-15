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

/**
 * 일반 로그인을 처리하는 비즈니스 인터페이스 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovOe1LoginService {
	 /**
	 * 일반 로그인을 처리한다
     * @param vo
     *        - 아이디, 비밀번호가 담긴 LoginVO
     * @return result - 로그인결과(세션정보)
     * @exception Exception
     */
    EgovOe1LoginVO actionLogin(EgovOe1LoginVO vo) throws Exception;

    /**
     * 아이디를 찾는다.
     * @param vo
     *        - 이름, 이메일주소, 사용자구분이 담긴 LoginVO
     * @return result - 아이디
     * @exception Exception
     */
    EgovOe1LoginVO searchId(EgovOe1LoginVO vo) throws Exception;

    /**
     * 비밀번호를 찾는다.
     * @param vo
     *        - 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답,
     *        사용자구분이 담긴 LoginVO
     * @return result - 임시비밀번호전송결과
     * @exception Exception
     */
    boolean searchPassword(EgovOe1LoginVO vo) throws Exception;

}
