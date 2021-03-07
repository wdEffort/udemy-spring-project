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
        <!-- s:view -->
        <div class="row mt">
            <div class="col-lg-12">
                <div class="form-panel">
                    <form class="form-horizontal style-form" method="post" role="form">
                        <input type="hidden" name="postId" id="postId" value="${boardPost.postId}"/>
                        <input type="hidden" name="page" id="page" value="${searchCriteria.page}"/>
                        <input type="hidden" name="numPerPage" id="numPerPage" value="${searchCriteria.numPerPage}"/>
                        <input type="hidden" name="searchType" id="searchType" value="${searchCriteria.searchType}"/>
                        <input type="hidden" name="searchKeyword" id="searchKeyword"
                               value="${searchCriteria.searchKeyword}"/>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">제목</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="subject" value="${boardPost.subject}"
                                       readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">내용</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="content" rows="4"
                                          readonly>${boardPost.content}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                            <div class="col-sm-10">
                                <input type="text" name="writer" class="form-control" value="${boardPost.writer}"
                                       readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12" align="center">
                                <button type="button" class="btn btn-info">목록</button>
                                <button type="button" class="btn btn-danger">삭제</button>
                                <button type="button" class="btn btn-primary">수정</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- e:view -->

        <!-- s:comments -->
        <h3><i class="fa fa-angle-right"></i> 댓글</h3>

        <div class="row mt">
            <div class="col-lg-12">
                <div class="form-panel">
                    <form name="comments" class="form-horizontal style-form" method="post">
                        <input type="hidden" name="cmtId" id="cmtId"/>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="cmtWriter" id="cmtWriter"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">내용</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="cmtContent" id="cmtContent" rows="4"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12" align="center">
                                <button type="button" class="btn btn-success" data-mode="insert">등록</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="content-panel">
                    <section id="unseen">
                        <table class="table table-bordered table-striped table-condensed">
                            <colgroup>
                                <col style="width: 10%"/>
                                <col style="width: 15%"/>
                                <col/>
                                <col style="width: 15%"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th>댓글번호</th>
                                <th>작성자</th>
                                <th>내용</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="comments"></tbody>
                        </table>
                    </section>
                </div>
                <div class="showback text-center">
                    <div class="btn-group" id="pagination"></div>
                </div>
            </div>
        </div>
        <!-- e:comments -->
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
        var postId = ${boardPost.postId};
        var page = 1;

        getComments(postId, page);

        // 게시글 목록으로 이동
        $('.btn-info').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/list"/>'
                + '?page=' + $('#page').val()
                + '&numPerPage=' + $('#numPerPage').val()
                + '&searchType=' + $('#searchType').val()
                + '&searchKeyword=' + $('#searchKeyword').val();
        });

        // 게시글 삭제
        $('.btn-danger').on('click', function () {
            f.attr('action', '/board/delete');
            f.submit();
        });

        // 게시글 목록으로 수정 페이지로 이동
        $('.btn-primary').on('click', function () {
            location.href = '<c:url value="${pageContext.request.contextPath}/board/modify/${boardPost.postId}"/>'
                + '?page=' + $('#page').val()
                + '&numPerPage=' + $('#numPerPage').val()
                + '&searchType=' + $('#searchType').val()
                + '&searchKeyword=' + $('#searchKeyword').val();
        });

        // 댓글 등록 또는 수정
        $('.btn-success').on('click', function (e) {
            var _mode = $(e.target).data('mode');
            var params;

            if (_mode === 'insert') {
                params = {
                    postId: $('#postId').val(),
                    cmtContent: $('#cmtContent').val(),
                    writer: $('#cmtWriter').val()
                }

                $.ajax({
                    url: '<c:url value="${pageContext.request.contextPath}/board/comments"/>',
                    type: 'post',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-HTTP-Method-Override': 'POST'
                    },
                    dataType: 'text',
                    data: JSON.stringify(params),
                    success: function (res) {
                        if (res === 'success') {
                            $('#cmtContent').val('');
                            $('#cmtWriter').val('');

                            getComments(postId, 1);
                        }
                    }
                });
            } else if (_mode === 'update') {
                params = {
                    cmtContent: $('#cmtContent').val(),
                }

                $.ajax({
                    url: '<c:url value="${pageContext.request.contextPath}/board/comments/"/>' + $('#cmtId').val(),
                    type: 'patch',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-HTTP-Method-Override': 'PATCH'
                    },
                    dataType: 'text',
                    data: JSON.stringify(params),
                    success: function (res) {
                        if (res === 'success') {
                            $('#cmtId').val('');
                            $('#cmtContent').val('');
                            $('#cmtWriter').val('');
                            $('#cmtWriter').attr('readonly', false);

                            $('form[name="comments"] button').attr('data-mode', 'insert');
                            $('form[name="comments"] button').data('mode', 'insert');
                            $('form[name="comments"] button').text('등록');

                            getComments(postId, page);
                        }
                    }
                });
            }
        });

        $('#comments').on('click', 'button', function (e) {
            e.preventDefault();

            var _cmtId = $(this).parents('tr').data('cmtId');

            if ($(this).hasClass('cmt-mod')) { // 댓글 정보
                $('#cmtId').val(_cmtId);
                $('#cmtWriter').val($(this).parent().siblings().eq(1).text());
                $('#cmtWriter').attr('readonly', true);
                $('#cmtContent').val($(this).parent().siblings().eq(2).text());

                $('form[name="comments"] button').attr('data-mode', 'update');
                $('form[name="comments"] button').data('mode', 'update');
                $('form[name="comments"] button').text('수정');
            } else if ($(this).hasClass('cmt-del')) { // 댓글 삭제
                $.ajax({
                    url: '<c:url value="${pageContext.request.contextPath}/board/comments/"/>' + _cmtId,
                    type: 'delete',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-HTTP-Method-Override': 'DELETE'
                    },
                    success: function (res) {
                        if (res === 'success') {
                            $('#cmtId').val('');
                            $('#cmtContent').val('');
                            $('#cmtWriter').val('');
                            $('#cmtWriter').attr('readonly', false);

                            $('form[name="comments"] button').attr('data-mode', 'insert');
                            $('form[name="comments"] button').data('mode', 'insert');
                            $('form[name="comments"] button').text('등록');
                            getComments(postId, page);
                        }
                    }
                })
            }
        });

        $('#pagination').on('click', 'a > button', function (e) {
            e.preventDefault();

            page = $(this).data('page');

            getComments(postId, page);
        })
    });

    function getComments(_postId, _page) {
        $.getJSON('<c:url value="${pageContext.request.contextPath}/board/comments/"/>' + _postId + '/' + _page, function (_data) {
            var $comments = $('#comments');
            var _el = '';

            $.each(_data.list, function (idx, item) {
                _el += '<tr data-post-id="' + item.postId + '" data-cmt-id="' + item.cmtId + '">'
                    + '<td align="center">' + item.cmtId + '</td>'
                    + '<td align="center">' + item.writer + '</td>'
                    + '<td>' + item.cmtContent + '</td>'
                    + '<td align="center"><button type="button" class="btn btn-default cmt-mod">수정</button>&nbsp;<button type="button" class="btn btn-default cmt-del">삭제</button></td>'
                    + '</tr>';
            });

            $comments.html(_el);

            getPagination(_data.pagingMaker);
        });
    }

    function getPagination(_pm) {
        var $pagination = $('#pagination');
        var _el = '';

        if (_pm.prev) {
            _el += '<a href="javascript:void(0);"><button type="button" class="btn btn-link" data-page="1"><i class="glyphicon glyphicon-backward"></i></button></a>';
            _el += '<a href="javascript:void(0);"><button type="button" class="btn btn-link" data-page="' + (_pm.startPage - 1) + '"><i class="glyphicon glyphicon-chevron-left"></i></button></a>';
        }

        for (var i = _pm.startPage; i <= _pm.endPage; i++) {
            _el += '<a href="javascript:void(0);"><button type="button" class="btn btn-link" data-page="' + i + '">' + i + '</button></a>';
        }

        if (_pm.next) {
            _el += '<a href="javascript:void(0);"><button type="button" class="btn btn-link" data-page="' + (_pm.endPage + 1) + '"><i class="glyphicon glyphicon-chevron-right"></i></button></a>';
            _el += '<a href="javascript:void(0);"><button type="button" class="btn btn-link" data-page="' + _pm.finalEndPage + '"><i class="glyphicon glyphicon-forward"></i></button></a>';
        }

        $pagination.html(_el);
    }
</script>

<%@ include file="../include/footer.jsp" %>
