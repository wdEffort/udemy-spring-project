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
                                        <td><a href="/board/view/${boardPost.postId}">${boardPost.subject}</a></td>
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
                            <button type="button" class="btn btn-default"><a href="${pageContext.request.contextPath}/board/list?page=1"><i class="glyphicon glyphicon-backward"></i></a></button>
                            <button type="button" class="btn btn-default"><a href="${pageContext.request.contextPath}/board/list?page=${pagingMaker.startPage - 1}"><i class="glyphicon glyphicon-chevron-left"></i></a></button>
                        </c:if>
                        <c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
                            <c:choose>
                                <c:when test="${pagingMaker.pageCriteria.page eq pNum}">
                                    <button type="button" class="btn btn-info">${pNum}</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-default"><a href="<c:url value="${pageContext.request.contextPath}/board/list?page=${pNum}"/>">${pNum}</a></button>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pagingMaker.next eq true}">
                            <button type="button" class="btn btn-default"><a href="${pageContext.request.contextPath}/board/list?page=${pagingMaker.endPage + 1}"><i class="glyphicon glyphicon-chevron-right"></i></a></button>
                            <button type="button" class="btn btn-default"><a href="${pageContext.request.contextPath}/board/list?page=${pagingMaker.finalEndPage}"><i class="glyphicon glyphicon-forward"></i></a></button>
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
    });
</script>

<%@ include file="../include/footer.jsp" %>
