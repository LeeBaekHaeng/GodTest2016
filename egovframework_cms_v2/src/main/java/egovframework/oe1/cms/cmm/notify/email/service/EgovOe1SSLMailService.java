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

import org.springframework.stereotype.Component;

/**
 * 메일전송 로직을 처리하는 서비스 인터페이스
 * <p><b>NOTE:</b>
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
public interface EgovOe1SSLMailService {
	 boolean sendEmailTo(EgovOe1SndngMailVO mailvo);
}
