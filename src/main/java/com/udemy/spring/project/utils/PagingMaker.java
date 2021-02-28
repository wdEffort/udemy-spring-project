package com.udemy.spring.project.utils;

/**
 * 페이징 요소 출력 클래스
 */
public class PagingMaker {

    private int totalCount; // 전체 데이터 개수
    private int displayPageNum = 10; // 출력할 페이지 목록 개수
    private int startPage; // 페이지 목록의 시작 번호
    private int endPage; // 페이지 목록의 끝 번호
    private int finalEndPage; // 마지막 페이지 번호
    private boolean prev; // 이전 버튼을 나타낼지 여부를 결정하는 값
    private boolean next; // 다음 버튼을 나타낼지 여부를 결정하는 값
    private PageCriteria pageCriteria;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        // 페이징 처리 데이터 계산
        getPagingData();
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getFinalEndPage() {
        return finalEndPage;
    }

    public void setFinalEndPage(int finalEndPage) {
        this.finalEndPage = finalEndPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public PageCriteria getPageCriteria() {
        return pageCriteria;
    }

    public void setPageCriteria(PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
    }

    /**
     * 페이징 처리 데이터 계산 메소드
     */
    private void getPagingData() {
        this.endPage = (int) (Math.ceil(this.pageCriteria.getPage() / (double) this.displayPageNum)) * this.displayPageNum;
        this.startPage = (this.endPage - this.displayPageNum) + 1;

        this.finalEndPage = (int) (Math.ceil(this.totalCount / (double) this.pageCriteria.getNumPerPage()));

        if (this.endPage > this.finalEndPage) {
            this.endPage = this.finalEndPage;
        }

        this.prev = this.startPage != 1;
        this.next = (this.endPage * this.pageCriteria.getNumPerPage()) < totalCount;
    }
}
