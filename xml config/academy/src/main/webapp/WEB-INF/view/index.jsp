<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@include file="./include/indexHead.jsp" %>

<div id="content-wrapper" class="mui--text-center">
    <div class="mui--appbar-height"></div>
    <br>
    <br>
    <div class="mui--text-display3 row wow zoomInDown">ACADEMY</div>
    <br>
    <br>
    <a href="<spring:url value="/main"/>" class="mui-btn mui-btn--raised">Get started</a>
    <br>
    <br>
    <i class="fa fa-graduation-cap fa-5x  mui--text-display3 row wow zoomIn" aria-hidden="true"></i>
    <p class="mui--text-danger">${message}</p>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>
<script>
    new WOW().init();
</script>

<%@include file="./include/footer.jsp" %>
