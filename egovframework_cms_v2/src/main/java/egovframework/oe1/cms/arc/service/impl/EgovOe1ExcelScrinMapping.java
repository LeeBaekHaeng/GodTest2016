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
package egovframework.oe1.cms.arc.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import egovframework.oe1.cms.arc.service.EgovOe1ScrinVO;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

/**
 * Excel 화면정보 매핑 클래스
 * @author 운영환경1팀 김연수
 * @since 2010.08.09
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.09  김연수          최초 생성
 * 
 * </pre>
 */
public class EgovOe1ExcelScrinMapping extends EgovExcelMapping {

    protected Log log = LogFactory.getLog(this.getClass());

    /**
     * 엑셀 매핑
     * @param 검색 정보가 담긴 ChangeRequestVO
     * @return int
     * @exception Exception
     */
    @SuppressWarnings("deprecation")
    @Override
    public Object mappingColumn(HSSFRow row) {
        HSSFCell cell0 = row.getCell((short) 0);
        HSSFCell cell1 = row.getCell((short) 1);
        HSSFCell cell2 = row.getCell((short) 2);
        HSSFCell cell3 = row.getCell((short) 3);
        HSSFCell cell4 = row.getCell((short) 4);
        HSSFCell cell5 = row.getCell((short) 5);
        HSSFCell cell6 = row.getCell((short) 6);

        EgovOe1ScrinVO vo = new EgovOe1ScrinVO();

        vo.setScrinId(EgovExcelUtil.getValue(cell0));
        vo.setSysNm(EgovExcelUtil.getValue(cell1));
        vo.setCompnPckage(EgovExcelUtil.getValue(cell2));
        vo.setScrinEnglNm(EgovExcelUtil.getValue(cell3));
        vo.setScrinNm(EgovExcelUtil.getValue(cell4));
        vo.setScrinDc(EgovExcelUtil.getValue(cell5));
        vo.setEtcDc(EgovExcelUtil.getValue(cell6));

        log.debug("########### vo is " + vo.getScrinId());
        log.debug("########### vo is " + vo.getSysNm());
        log.debug("########### vo is " + vo.getCompnPckage());
        log.debug("########### vo is " + vo.getScrinEnglNm());
        log.debug("########### vo is " + vo.getScrinNm());
        log.debug("########### vo is " + vo.getScrinDc());
        log.debug("########### vo is " + vo.getEtcDc());

        return vo;
    }
}
