<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../include/header.jsp" %>

<!-- s:main -->
<section id="main-content">
    <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> 글 보기</h3>
        <!-- role 속성:
            . HTML5에서 새롭게 추가된 속성
            . ARIA(Accessible Rich Internet Application)라는 HTML5의 상세 규격 중 하나
            . 시각 장애인이 컴퓨터의 리더기를 사용해서 웹 페이지를 읽을 때 "해당 부분이 form이다" 라고 정의해주는 것
            . role은 필수적인 요소는 아니지만 화면용 리더기를 이용해야하는 사람들에게도
              불편함이 없는 사이트를 제공하고자 하는 경우에 이 속성을 이용함.
          -->
        <div class="row mt">
            <div class="col-lg-12">
                <div class="form-panel">
                    <form class="form-horizontal style-form" method="post" role="form">
                        <input type="hidden" name="postId" id="postId" value="${boardPost.postId}"/>
                        <input type="hidden" name="page" id="page" value="${pageCriteria.page}"/>
                        <input type="hidden" name="numPerPage" id="numPerPage" value="${pageCriteria.numPerPage}"/>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">제목</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="subject" value="${boardPost.subject}" readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">내용</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="content" rows="4" readonly>${boardPost.content}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                            <div class="col-sm-10">
                                <input type="text" name="writer" class="form-control" value="${boardPost.writer}" readonly/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-group">
                    <div class="col-sm-12" align="center">
                        <button type="button" class="btn btn-info">목록</button>
                        <button type="button" class="btn btn-danger">삭제</button>
                        <button type="button" class="btn btn-primary">수정</button>
                    </div>
                </div>
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
        var f = $('form[role="form"]');

        $('.btn-info').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/list"/>' + '?page=' + $('#page').val() + '&numPerPage=' + $('#numPerPage').val();
        });

        $('.btn-danger').on('click', function () {
            f.attr('action', '/board/delete');
            f.submit();
        });

        $('.btn-primary').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/modify/${boardPost.postId}"/>' + '?page=' + $('#page').val() + '&numPerPage=' + $('#numPerPage').val();
        });
    });
</script>

<%@ include file="../include/footer.jsp" %>
