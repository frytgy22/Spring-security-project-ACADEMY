<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@include file="./include/indexHead.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">Sign in</h1>

        <form class="mui-form " id="appointment-form" method="post" action="<spring:url value="/login"/>">
            <div class="mui-textfield mui-textfield--float-label">
                <input name="custom_username" id="username" class="form-control"/>
                <label for="username">Email</label>
            </div>
            <div class="mui-textfield mui-textfield--float-label">
                <input type="password" name="custom_password" id="password"/>
                <label for="password">Password</label>
            </div>
            <div class="mui-checkbox">
                <label>
                    <input type="checkbox" name="remember-me" id="remember-me"/> Remember me
                </label>
            </div>
            <security:csrfInput/>

            <div>
                <c:if test="${param.error == 'credentials'}">
                    <p class="mui--text-danger">Invalid email or password</p>
                </c:if>
                <c:if test="${param.logout == 'success'}">
                    <p class="mui--text-danger">Logged out successfully</p>
                </c:if>
            </div>

            <div class="mui--text-center">
                <button type="submit" class="mui-btn mui-btn--raised">Submit</button>
            </div>
        </form>

    </div>
</div>

<%@include file="./include/footer.jsp" %>
