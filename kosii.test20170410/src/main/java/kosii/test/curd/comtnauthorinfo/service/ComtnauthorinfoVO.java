package kosii.test.curd.comtnauthorinfo.service;

/**
 * @Class Name : ComtnauthorinfoVO.java
 * @Description : Comtnauthorinfo VO class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ComtnauthorinfoVO extends ComtnauthorinfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** AUTHOR_CODE */
    private java.lang.String authorCode;
    
    /** AUTHOR_NM */
    private java.lang.String authorNm;
    
    /** AUTHOR_DC */
    private java.lang.String authorDc;
    
    /** AUTHOR_CREAT_DE */
    private java.lang.String authorCreatDe;
    
    public java.lang.String getAuthorCode() {
        return this.authorCode;
    }
    
    public void setAuthorCode(java.lang.String authorCode) {
        this.authorCode = authorCode;
    }
    
    public java.lang.String getAuthorNm() {
        return this.authorNm;
    }
    
    public void setAuthorNm(java.lang.String authorNm) {
        this.authorNm = authorNm;
    }
    
    public java.lang.String getAuthorDc() {
        return this.authorDc;
    }
    
    public void setAuthorDc(java.lang.String authorDc) {
        this.authorDc = authorDc;
    }
    
    public java.lang.String getAuthorCreatDe() {
        return this.authorCreatDe;
    }
    
    public void setAuthorCreatDe(java.lang.String authorCreatDe) {
        this.authorCreatDe = authorCreatDe;
    }
    
}
