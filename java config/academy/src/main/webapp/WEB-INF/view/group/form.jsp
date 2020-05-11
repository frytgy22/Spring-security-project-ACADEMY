<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Create group"/>
<c:set var="search" value="/groups/search"/>
<c:set var="text" value="Search by group name"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>

        <form:form modelAttribute="groupDto" class="mui-form " method="post">
            <form:hidden path="id"/>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="name" required="required" id="name" maxlength="30" cssClass="validate"/>
                <form:label path="name" for="name">Name</form:label>
                <form:errors path="name" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui--text-center">
                <button type="submit" class="mui-btn mui-btn--raised">Submit</button>
            </div>
        </form:form>

    </div>
</div>

<%@include file="../include/footer.jsp" %>
