<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error</title>
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
                        <li><a href="<spring:url value="/"/>" target="_blank">Home</a></li>
                    </ul>
                </td>
            </tr>
        </table>
    </div>
</header>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">
        <div class="mui--text-center">
            <img id="error" src="<spring:url value="/static/images/1.jpg"/>" alt="error">
        </div>

    </div>
</div>

<%@include file="./include/footer.jsp" %>
