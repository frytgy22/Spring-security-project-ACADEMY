<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="//cdn.muicss.com/mui-latest/extra/mui-colors.min.css">
    <link rel="stylesheet" href="<spring:url value="/static/css/style.css"/>"/>
    <script src="//cdn.muicss.com/mui-latest/extra/mui-combined.min.js"></script>
</head>

<body>
<header class="mui-appbar mui--z1">
    <div class="mui-container">
        <table>
            <tr class="mui--appbar-height">
                <td class="mui--text-title">Academy</td>
                <td class="mui--text-right">
                    <ul class="mui-list--inline mui--text-body2">
                        <sec:authorize access="isAuthenticated()" var="authenticated"/>
                        <c:choose>
                            <c:when test="${authenticated}">
                                <li>
                                    <span id="welcome">Welcome, <sec:authentication property="principal.name"/>
                                     <sec:authentication property="principal.surname"/></span>
                                    <a id="logout" href="<spring:url value="/logout"/>">
                                        <i class="fa fa-user" aria-hidden="true"></i> Sing out </a>
                                </li>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li>
                                        <a href="<spring:url value="/h2console"/>" target="_blank">
                                            <i class="fa fa-database" aria-hidden="true"></i> H2 console</a>
                                    </li>
                                </sec:authorize>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<spring:url value="/login"/>">
                                        <i class="fa fa-sign-in" aria-hidden="true"></i> Sing in</a>
                                </li>
                                <li>
                                    <a href="<spring:url value="/students/register"/>">
                                        <i class="fa fa-user-plus" aria-hidden="true"></i> Student registration</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </td>
            </tr>
        </table>
    </div>
</header>

<%@include file="../include/formLogout.jsp" %>