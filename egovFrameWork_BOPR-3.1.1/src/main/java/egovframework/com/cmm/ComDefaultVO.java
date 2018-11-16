package egovframework.com.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    조재영         최초 생성
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.02.01
 *  @version 1.0
 *  @see 
 *  
 */
public class ComDefaultVO implements Serializable {
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";
    
    /** 리턴 메시지 */
    private String returnMssage = "";
    
    /** 조회자 */
    private String loginId = "";
    
    /** 관리자 여부 */
    private String adminYn = "";
    
    /** 리턴 메시지 종류 */
    private String returnMssageType = "";
    
    /**
	 * loginId attribute 값을 리턴
	 * @return String
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * loginId attribute 값을 설정
	 * @param loginId String
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
    
	/**
	 * adminYn attribute 값을 리턴
	 * @return String
	 */
	public String getAdminYn() {
		return adminYn;
	}

	/**
	 * adminYn attribute 값을 설정
	 * @param adminYn String
	 */
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    
    /**
	 * searchKeywordFrom attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * @param searchKeywordFrom String
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * @param searchKeywordTo String
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}
	
	/**
	 * returnMssage attribute를 리턴한다.
	 * @return String
	 */
	public String getReturnMssage() {
		return returnMssage;
	}

	/**
	 * returnMssage attribute 값을 설정한다.
	 * @param returnMssage String
	 */
	public void setReturnMssage(String returnMssage) {
		this.returnMssage = returnMssage;
	}
	
	/**
	 * returnMssageType attribute를 리턴한다.
	 * @return String
	 */
	public String getReturnMssageType() {
		return returnMssageType;
	}

	/**
	 * returnMssageType attribute 값을 설정한다.
	 * @param returnMssageType String
	 */
	public void setReturnMssageType(String returnMssageType) {
		this.returnMssageType = returnMssageType;
	}
	
	
}
