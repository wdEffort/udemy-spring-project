package com.udemy.spring.project.utils;

/**
 * PageCriteria를 상속받은 SearchCriteria 클래스
 * PageCriteria 클래스가 가지는 page, numPerPage 멤버변수를 그대로 사용 가능하다.
 */
public class SearchCriteria extends PageCriteria {

    private String searchType;
    private String searchKeyword;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
