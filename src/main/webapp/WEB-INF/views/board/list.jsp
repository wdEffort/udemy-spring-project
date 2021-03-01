<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>

<!-- s:main -->
<section id="main-content">
    <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> 글 목록</h3>
        <div class="row mt">
            <div class="col-lg-12">
                <div class="content-panel">
                    <section id="unseen">
                        <form class="form-inline text-center" method="get">
                            <div class="form-group">
                                <select class="form-control" name="searchType" id="searchType">
                                    <option value="" <c:if test="${empty searchCriteria.searchType}">selected</c:if>>선택</option>
                                    <option value="S" <c:if test="${searchCriteria.searchType eq 'S'}">selected</c:if>>제목</option>
                                    <option value="C" <c:if test="${searchCriteria.searchType eq 'C'}">selected</c:if>>내용</option>
                                    <option value="W" <c:if test="${searchCriteria.searchType eq 'W'}">selected</c:if>>작성자</option>
                                    <option value="SC" <c:if test="${searchCriteria.searchType eq 'SC'}">selected</c:if>>제목+내용</option>
                                    <option value="CW" <c:if test="${searchCriteria.searchType eq 'CW'}">selected</c:if>>내용+작성자</option>
                                    <option value="SCW" <c:if test="${searchCriteria.searchType eq 'SCW'}">selected</c:if>>제목+내용+작성자</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="searchKeyword" id="searchKeyword" value="${searchCriteria.searchKeyword}"/>
                            </div>
                            <button type="button" class="btn btn-success">검색</button>
                        </form>
                        <br/>
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th>글번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th class="numeric">조회수</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="boardPost" items="${boardPostList}">
                                    <tr>
                                        <td>${boardPost.postId}</td>
                                        <td>
                                            <a href="/board/view/${boardPost.postId}${pagingMaker.makeSearchURI(pagingMaker.pageCriteria.page)}">${boardPost.subject}</a>
                                        </td>
                                        <td>${boardPost.writer}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardPost.regDate}"/></td>
                                        <td class="numeric">${boardPost.hit}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </section>
                </div>
                <div class="showback text-center">
                    <div class="btn-group">
                        <c:if test="${pagingMaker.prev eq true}">
                            <a href="${pageContext.request.contextPath}/board/list${pagingMaker.makeSearchURI(1)}"><button type="button" class="btn btn-default"><i class="glyphicon glyphicon-backward"></i></button></a>
                            <a href="${pageContext.request.contextPath}/board/list${pagingMaker.makeSearchURI(pagingMaker.startPage - 1)}"><button type="button" class="btn btn-default"><i class="glyphicon glyphicon-chevron-left"></i></button></a>
                        </c:if>
                        <c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
                            <c:choose>
                                <c:when test="${pagingMaker.pageCriteria.page eq pNum}">
                                    <a href="javascript:void(0);"><button type="button" class="btn btn-info">${pNum}</button></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value="${pageContext.request.contextPath}/board/list${pagingMaker.makeSearchURI(pNum)}"/>"><button type="button" class="btn btn-default">${pNum}</button></a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pagingMaker.next eq true}">
                            <a href="${pageContext.request.contextPath}/board/list${pagingMaker.makeSearchURI(pagingMaker.endPage + 1)}"><button type="button" class="btn btn-default"><i class="glyphicon glyphicon-chevron-right"></i></button></a>
                            <a href="${pageContext.request.contextPath}/board/list${pagingMaker.makeSearchURI(pagingMaker.finalEndPage)}"><button type="button" class="btn btn-default"><i class="glyphicon glyphicon-forward"></i></button></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt text-right">
            <div class="col-lg-12">
                <button type="button" class="btn btn-primary">글 쓰기</button>
            </div>
        </div>
    </section>
</section>
<!-- e:main -->
<script>
    (function () {
        if ('${message}' !== '') {
            alert('${message}');
        }
    })();

    $(document).ready(function () {
        $('.btn-primary').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/write"/>';
        });

        $('.btn-success').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/list${pagingMaker.makeURI(1)}"/>'
                + '&searchType=' + $('#searchType').val()
                + '&searchKeyword=' + $('#searchKeyword').val();
        });
    });
</script>

<%@ include file="../include/footer.jsp" %>
