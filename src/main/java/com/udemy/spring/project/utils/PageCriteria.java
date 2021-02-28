package com.udemy.spring.project.utils;

/**
 * 페이징 처리 클래스
 */
public class PageCriteria {

    private int page; // 시작 페이지
    private int numPerPage; // 한 페이지당 데이터 출력 개수

    public PageCriteria() {
        this.page = 1;
        this.numPerPage = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        // 페이지 번호가 0보다 작거나 같게 설정된 경우 1로 강제 설정
        if (page <= 0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        // 한 페이지당 데이터 출력 개수가 0이거나 100보다 크게 설정된 경우 강제로 10으로 설정
        if (numPerPage <= 0 || numPerPage > 100) {
            this.numPerPage = 10;
            return;
        }

        this.numPerPage = numPerPage;
    }

    /**
     * 데이터 조회 시작 위치를 계산하는 메소드
     *
     * @return
     */
    public int getStartPage() {
        // (시작 페이지 - 1) * 한 페이지당 데이터 출력 개수
        return (this.page - 1) * this.numPerPage;
    }

    @Override
    public String toString() {
        return "PageCriteria{" +
                "page=" + page +
                ", numPerPage=" + numPerPage +
                '}';
    }
}
