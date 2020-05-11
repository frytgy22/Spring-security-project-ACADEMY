<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Groups"/>
<c:set var="search" value="/groups/search"/>
<c:set var="text" value="Search by group name"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>
        <p class="mui--text-danger">${message}</p>

        <div class="mui--text-right">
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                <a href="<spring:url value="/groups/create"/>"
                   class="mui-btn mui-btn--fab mui-btn--primary mui--z1">+</a>
            </sec:authorize>
        </div>

        <table class="mui-table mui-table--bordered table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Student list</th>
                <th>Teachers list</th>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <th>Edit</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${groups}" var="group">
                <tr>
                    <td>${group.id}</td>
                    <td>${group.name}</td>
                    <td>
                        <div class="mui-dropdown mui-dropdown--right">
                            <button class="mui-btn mui-btn--small mui-btn--primary mui--z1" data-mui-toggle="dropdown">
                                Students
                                <span class="mui-caret mui-caret--right"></span>
                            </button>
                            <ul class="mui-dropdown__menu">
                                <c:forEach items="${group.students}" var="item">
                                    <li><a href="#">${item.name} ${item.surname}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="mui-dropdown mui-dropdown--right">
                            <button class="mui-btn mui-btn--small mui-btn--primary mui--z1" data-mui-toggle="dropdown">
                                Teachers
                                <span class="mui-caret mui-caret--right"></span>
                            </button>
                            <ul class="mui-dropdown__menu">
                                <c:forEach items="${group.teachers}" var="item">
                                    <li><a href="#">${item.name} ${item.surname}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                        <td>
                            <a class="mui-btn mui-btn--small mui-btn--primary mui--z1"
                               href="<spring:url value="/groups/edit/${group.id}"/>">Update</a>

                            <button class="mui-btn mui-btn--small mui-btn--danger delete mui--z1"
                                    value="<spring:url value="/groups/delete/${group.id}"/>">Delete
                            </button>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <%@include file="../include/pagination.jsp" %>
    </div>
</div>

<script src="<spring:url value="/static/js/button_listener.js"/>"></script>
<%@include file="../include/footer.jsp" %>

