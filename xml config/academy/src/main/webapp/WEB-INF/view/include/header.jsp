<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <link href="//cdn.muicss.com/mui-latest/css/mui.min.css" rel="stylesheet" type="text/css"/>
    <script src="//cdn.muicss.com/mui-latest/js/mui.min.js"></script>
    <script src="//code.jquery.com/jquery-2.1.4.min.js"></script>

    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link rel="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css"
          type="text/css"/>
    <script src="<spring:url value="/static/js/script.js"/>"></script>
    <link href="<spring:url value="/static/css/style_main.css"/>" rel="stylesheet" type="text/css"/>

    <title>${title}</title>

</head>
<body>
<div id="sidedrawer" class="mui--no-user-select mui--z1">
    <div id="sidedrawer-brand" class="mui--appbar-line-height">
        <h5 class="mui--text-title h5">ACADEMY</h5>
    </div>
    <div class="mui-divider mui--z1"></div>
    <ul>
        <li>
            <strong>Teachers</strong>
            <ul>
                <li><a href="<spring:url value="/teachers"/>">List</a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="<spring:url value="/teachers/create"/>">Add</a></li>
                </sec:authorize>
            </ul>
        </li>
        <li>
            <strong>Students</strong>
            <ul>
                <li><a href="<spring:url value="/students"/>">List</a></li>

                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <li><a href="<spring:url value="/students/create"/>">Add</a></li>
                </sec:authorize>
            </ul>
        </li>
        <li>
            <strong>Groups</strong>
            <ul>
                <li><a href="<spring:url value="/groups"/>">List</a></li>

                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <li><a href="<spring:url value="/groups/create"/>">Add</a></li>
                </sec:authorize>
            </ul>
        </li>
        <li>
            <strong>Home</strong>
            <ul>
                <li><a href="<spring:url value="/"/>">Home</a></li>
            </ul>
        </li>
    </ul>
    <img id="animate" src="<spring:url value="/static/images/8.png"/>" alt="logo">
</div>
<header id="header">

    <div id="search">
        <form class="form-inline" action="<spring:url value="${search}"/>">
            <input class="form-control mr-sm-2" type="search" placeholder="${text}" aria-label="Search" name="search">
            <button id="button-search" class="btn  my-2 my-sm-0 mui--z1 btn-light" type="submit">Search
            </button>
        </form>
        <a id="logout" class=" mui--z1 btn btn-outline-success my-2 my-sm-0 btn-dark"
           href="<spring:url value="/logout"/>">Sing Out
        </a>
    </div>

    <div class="mui-appbar mui--appbar-line-height head">
        <div class="mui-container-fluid">
            <a class="sidedrawer-toggle mui--visible-xs-inline-block mui--visible-sm-inline-block js-show-sidedrawer">
                ☰</a>
            <a class="sidedrawer-toggle mui--hidden-xs mui--hidden-sm js-hide-sidedrawer">☰</a>
            <span class="mui--text-title mui--visible-xs-inline-block mui--visible-sm-inline-block">ACADEMY</span>
        </div>
    </div>
</header>

<%@include file="../include/formLogout.jsp" %>