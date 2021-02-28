<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../include/header.jsp" %>

<!-- s:main -->
<section id="main-content">
    <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> 글 쓰기</h3>
        <div class="row mt">
            <div class="col-lg-12">
                <div class="form-panel">
                    <form:form modelAttribute="vo" cssClass="form-horizontal style-form">
                        <div class="form-group">
                            <form:label path="subject" cssClass="col-sm-2 col-sm-2 control-label">제목</form:label>
                            <div class="col-sm-10">
                                <form:input path="subject" cssClass="form-control"/>
                                <form:errors path="subject" element="div" cssClass="alert alert-danger" role="alert"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="content" cssClass="col-sm-2 col-sm-2 control-label">내용</form:label>
                            <div class="col-sm-10">
                                <form:textarea path="content" cssClass="form-control" rows="4"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="writer" cssClass="col-sm-2 col-sm-2 control-label">작성자</form:label>
                            <div class="col-sm-10">
                                <form:input path="writer" cssClass="form-control"/>
                                <form:errors path="writer" element="div" cssClass="alert alert-danger" role="alert"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12" align="center">
                                <button type="submit" class="btn btn-theme">등록</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</section>
<!-- e:main -->

<%@ include file="../include/footer.jsp" %>
