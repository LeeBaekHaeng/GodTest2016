package godsoft.test.crud.comtnbbs.service;

/**
 * @Class Name : ComtnbbsVO.java
 * @Description : Comtnbbs VO class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-05-01
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ComtnbbsVO extends ComtnbbsDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** NTT_ID */
    private java.math.BigDecimal nttId;
    
    /** BBS_ID */
    private java.lang.String bbsId;
    
    /** NTT_NO */
    private java.math.BigDecimal nttNo;
    
    /** NTT_SJ */
    private java.lang.String nttSj;
    
    /** NTT_CN */
    private java.sql.Clob nttCn;
    
    /** ANSWER_AT */
    private java.lang.String answerAt;
    
    /** PARNTSCTT_NO */
    private java.math.BigDecimal parntscttNo;
    
    /** ANSWER_LC */
    private java.math.BigDecimal answerLc;
    
    /** SORT_ORDR */
    private java.math.BigDecimal sortOrdr;
    
    /** RDCNT */
    private java.math.BigDecimal rdcnt;
    
    /** USE_AT */
    private java.lang.String useAt;
    
    /** NTCE_BGNDE */
    private java.lang.String ntceBgnde;
    
    /** NTCE_ENDDE */
    private java.lang.String ntceEndde;
    
    /** NTCR_ID */
    private java.lang.String ntcrId;
    
    /** NTCR_NM */
    private java.lang.String ntcrNm;
    
    /** PASSWORD */
    private java.lang.String password;
    
    /** ATCH_FILE_ID */
    private java.lang.String atchFileId;
    
    /** FRST_REGIST_PNTTM */
    private java.sql.Date frstRegistPnttm;
    
    /** FRST_REGISTER_ID */
    private java.lang.String frstRegisterId;
    
    /** LAST_UPDT_PNTTM */
    private java.sql.Date lastUpdtPnttm;
    
    /** LAST_UPDUSR_ID */
    private java.lang.String lastUpdusrId;
    
    public java.math.BigDecimal getNttId() {
        return this.nttId;
    }
    
    public void setNttId(java.math.BigDecimal nttId) {
        this.nttId = nttId;
    }
    
    public java.lang.String getBbsId() {
        return this.bbsId;
    }
    
    public void setBbsId(java.lang.String bbsId) {
        this.bbsId = bbsId;
    }
    
    public java.math.BigDecimal getNttNo() {
        return this.nttNo;
    }
    
    public void setNttNo(java.math.BigDecimal nttNo) {
        this.nttNo = nttNo;
    }
    
    public java.lang.String getNttSj() {
        return this.nttSj;
    }
    
    public void setNttSj(java.lang.String nttSj) {
        this.nttSj = nttSj;
    }
    
    public java.sql.Clob getNttCn() {
        return this.nttCn;
    }
    
    public void setNttCn(java.sql.Clob nttCn) {
        this.nttCn = nttCn;
    }
    
    public java.lang.String getAnswerAt() {
        return this.answerAt;
    }
    
    public void setAnswerAt(java.lang.String answerAt) {
        this.answerAt = answerAt;
    }
    
    public java.math.BigDecimal getParntscttNo() {
        return this.parntscttNo;
    }
    
    public void setParntscttNo(java.math.BigDecimal parntscttNo) {
        this.parntscttNo = parntscttNo;
    }
    
    public java.math.BigDecimal getAnswerLc() {
        return this.answerLc;
    }
    
    public void setAnswerLc(java.math.BigDecimal answerLc) {
        this.answerLc = answerLc;
    }
    
    public java.math.BigDecimal getSortOrdr() {
        return this.sortOrdr;
    }
    
    public void setSortOrdr(java.math.BigDecimal sortOrdr) {
        this.sortOrdr = sortOrdr;
    }
    
    public java.math.BigDecimal getRdcnt() {
        return this.rdcnt;
    }
    
    public void setRdcnt(java.math.BigDecimal rdcnt) {
        this.rdcnt = rdcnt;
    }
    
    public java.lang.String getUseAt() {
        return this.useAt;
    }
    
    public void setUseAt(java.lang.String useAt) {
        this.useAt = useAt;
    }
    
    public java.lang.String getNtceBgnde() {
        return this.ntceBgnde;
    }
    
    public void setNtceBgnde(java.lang.String ntceBgnde) {
        this.ntceBgnde = ntceBgnde;
    }
    
    public java.lang.String getNtceEndde() {
        return this.ntceEndde;
    }
    
    public void setNtceEndde(java.lang.String ntceEndde) {
        this.ntceEndde = ntceEndde;
    }
    
    public java.lang.String getNtcrId() {
        return this.ntcrId;
    }
    
    public void setNtcrId(java.lang.String ntcrId) {
        this.ntcrId = ntcrId;
    }
    
    public java.lang.String getNtcrNm() {
        return this.ntcrNm;
    }
    
    public void setNtcrNm(java.lang.String ntcrNm) {
        this.ntcrNm = ntcrNm;
    }
    
    public java.lang.String getPassword() {
        return this.password;
    }
    
    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    
    public java.lang.String getAtchFileId() {
        return this.atchFileId;
    }
    
    public void setAtchFileId(java.lang.String atchFileId) {
        this.atchFileId = atchFileId;
    }
    
    public java.sql.Date getFrstRegistPnttm() {
        return this.frstRegistPnttm;
    }
    
    public void setFrstRegistPnttm(java.sql.Date frstRegistPnttm) {
        this.frstRegistPnttm = frstRegistPnttm;
    }
    
    public java.lang.String getFrstRegisterId() {
        return this.frstRegisterId;
    }
    
    public void setFrstRegisterId(java.lang.String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }
    
    public java.sql.Date getLastUpdtPnttm() {
        return this.lastUpdtPnttm;
    }
    
    public void setLastUpdtPnttm(java.sql.Date lastUpdtPnttm) {
        this.lastUpdtPnttm = lastUpdtPnttm;
    }
    
    public java.lang.String getLastUpdusrId() {
        return this.lastUpdusrId;
    }
    
    public void setLastUpdusrId(java.lang.String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }
    
}
