<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Teachers"/>
<c:set var="search" value="/teachers/search"/>
<c:set var="text" value="Search by teacher name and/or surname"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>
        <p class="mui--text-danger">${message}</p>

        <div class="mui--text-right">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="<spring:url value="/teachers/create"/>"
                   class="mui-btn mui-btn--fab mui-btn--primary mui--z1">+</a>
            </sec:authorize>
        </div>

        <table class="mui-table mui-table--bordered table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Photo</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Group list</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th>Edit</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${teachers}" var="t">
                <tr>
                    <td>${t.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty t.photo}">
                                <img src="/uploads/teachers/${t.id}/${t.photo}" alt="${t.photo}" id="avatar"/>
                            </c:when>
                            <c:otherwise>
                                <img src="/uploads/1.png" alt="avatar" id="avatar"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${t.name}</td>
                    <td>${t.surname}</td>
                    <td>${t.email}</td>
                    <td>${t.role}</td>
                    <td>
                        <div class="mui-dropdown mui-dropdown--right">
                            <button class="mui-btn mui-btn--small mui-btn--primary mui--z1" data-mui-toggle="dropdown">
                                Groups
                                <span class="mui-caret mui-caret--right"></span>
                            </button>
                            <ul class="mui-dropdown__menu">
                                <c:forEach items="${t.groupsName}" var="item">
                                    <li><a href="#">${item}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a class="mui-btn mui-btn--small mui-btn--primary mui--z1"
                               href="<spring:url value="/teachers/edit/${t.id}"/>">Update</a>

                            <a class="mui-btn mui-btn--small mui-btn--danger mui--z1"
                               href="<spring:url value="/teachers/delete/${t.id}"/>">Delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <%@include file="../include/pagination.jsp" %>
    </div>
</div>

<%@include file="../include/footer.jsp" %>

