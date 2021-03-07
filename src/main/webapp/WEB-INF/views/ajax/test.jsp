<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8"/>
        <title>AJAX 테스트</title>
        <script src="/resources/assets/js/jquery-1.8.3.min.js"></script>
        <style>
            #modify { width: 260px; height: 120px; background-color: lightgray; position: absolute; top: 20%; left: 30%; padding: 20px; z-index: 100; display: none; }
        </style>
    </head>
    <body>
        <h3>게시글 댓글 REST + AJAX Test</h3>

        <hr/>

        <div>
            <p>
                작성자 : <input type="text" name="writer" id="writer" maxlength="10" title="작성자 입력" placeholder="작성자"/>
            </p>
            <p>
                <textarea name="cmtContent" id="cmtContent" rows="5" cols="30" maxlength="200" title="댓글 내용 입력" placeholder="내용"></textarea>
            </p>
            <p>
                <button type="button" id="btn-submit">댓글 작성</button>
            </p>
        </div>

        <hr/>

        <ul id="comments"></ul>
        <div class="pagination"></div>

        <div id="modify">
            <div class="title-dialog" align="center"></div>
            <div align="center">
                <input type="hidden" name="mCmtId" id="mCmtId"/>
                <textarea name="mCmtContent" id="mCmtContent" rows="5" cols="30" maxlength="200" title="댓글 내용 입력" placeholder="내용"></textarea>
                <button type="button" id="btn-modify">수정</button>
                <button type="button" id="btn-delete">삭제</button>
                <button type="button" id="btn-close">닫기</button>
            </div>
        </div>
        <script>
            $(function () {
                getComments(1, 1);

                // 등록
                $('#btn-submit').on('click', function () {
                    var _postId = 1;
                    var _params = {
                        postId: _postId,
                        writer: $('#writer').val(),
                        cmtContent: $('#cmtContent').val()
                    };

                    $.ajax({
                        url: '/board/comments',
                        type: 'post',
                        headers: {
                            'Content-Type': 'application/json', // 요청 데이터 타입
                            'X-HTTP-Method-Override': 'POST' // GET, POST만을 지원하는 브라우저에서 PUT, PATCH, DELETE를 처리하지 못하는 이슈를 해결 할 수 있게 도와주는 속성
                        },
                        dataType: 'text',
                        data: JSON.stringify(_params),
                        success: function (res) {
                            if (res === 'success') {
                                console.log('댓글 등록 성공.');
                                $('#writer').val('');
                                $('#cmtContent').val('');
                                getComments(1, 1);
                            }
                        },
                        error: function (req, stt, err) {
                            console.log(req, stt, err);
                        }
                    })
                });

                // '#comments'가 '.comment button'에게 이벤트를 위임하여 처리
                $('#comments').on('click', '.comment button', function () {
                    var $li = $(this).parent();
                    var _cmtId = $li.data('cmtId');
                    var _cmtContent = $li.children('span').text();

                    $('.title-dialog').html(_cmtId + '번 댓글');

                    $('#mCmtId').val(_cmtId);
                    $('#mCmtContent').val(_cmtContent);

                    $('#modify').show('fast');
                });

                // 수정
                $('#btn-modify').on('click', function () {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/board/comments/' + $('#mCmtId').val(),
                        type: 'patch',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-HTTP-Method-Override': 'PATCH'
                        },
                        dataType: 'text',
                        data: JSON.stringify({cmtContent: $('#mCmtContent').val()}),
                        success: function (res) {
                            if (res === 'success') {
                                console.log('댓글 수정 성공');
                                $('#modify').hide('fast');
                                getComments(1, 1);
                            }
                        },
                        error: function (req, stt, err) {
                            console.log(req, stt, err);
                        }
                    })
                });

                // 삭제
                $('#btn-delete').on('click', function () {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/board/comments/' + $('#mCmtId').val(),
                        type: 'delete',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-HTTP-Method-Override': 'DELETE'
                        },
                        dataType: 'text',
                        success: function (res) {
                            if (res === 'success') {
                                console.log('댓글 삭제 성공');
                                $('#modify').hide('fast');
                                getComments(1);
                            }
                        },
                        error: function (req, stt, err) {
                            console.log(req, stt, err);
                        }
                    })
                });

                $('#btn-close').on('click', function () {
                    $('#modify').hide('fast');
                });

                $('.pagination').on('click', 'button', function (e) {
                    e.preventDefault();

                    var _postId = $(e.target).data('postId');
                    var _page = $(e.target).data('pageNum');

                    getComments(_postId, _page);
                })
            });

            // 목록 조회 + 페이징 처리
            function getComments(_postId, _page) {
                // GET 방식으로 댓글 목록 조회 URI 요청 + 페이징 처리
                $.getJSON('${pageContext.request.contextPath}/board/comments/' + _postId + '/' + _page, function (_data) {
                    var $comments = $('#comments');
                    var _el = '';

                    $.each(_data.list, function (_idx, _item) {
                        _el += '<li data-cmt-id="' + _item.cmtId + '" class="comment">'
                            + '<span>' + _item.cmtContent + '</span> (' + _item.writer + ')'
                            + '<button type="button">수정</button>'
                            + '</li>';
                    });

                    $comments.html(_el);

                    showPageNum(_postId, _data.pagingMaker);
                });
            }

            function showPageNum(_postId, _pm) {
                var $pagination = $('.pagination');
                var _el = '';

                if (_pm.prev) {
                    _el += '<button type="button" data-post-id="' + _postId + '" data-page-num="' + 1 + '"><<</button>';
                    _el += '<button type="button" data-post-id="' + _postId + '" data-page-num="' + (_pm.startPage - 1) + '"><</button>';
                }

                for (var i = _pm.startPage; i <= _pm.endPage; i++) {
                    _el += '<button type="button" data-post-id="' + _postId + '" data-page-num="' + i + '">' + i + '</button>';
                }

                if (_pm.next) {
                    _el += '<button type="button" data-post-id="' + _postId + '" data-page-num="' + (_pm.endPage + 1) + '">></button>';
                    _el += '<button type="button" data-post-id="' + _postId + '" data-page-num="' + _pm.finalEndPage + '">>></button>';
                }

                $pagination.html(_el);
            }
        </script>
    </body>
</html>