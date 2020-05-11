<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Create student"/>
<c:set var="search" value="/students/search"/>
<c:set var="text" value="Search by student name and/or surname"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>

        <%@include file="include/formTemptale.jsp" %>

    </div>
</div>

<%@include file="../include/footer.jsp" %>
