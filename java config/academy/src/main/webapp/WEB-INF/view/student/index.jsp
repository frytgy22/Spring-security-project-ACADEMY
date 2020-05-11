<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Students"/>
<c:set var="search" value="/students/search"/>
<c:set var="text" value="Search by student name and/or surname"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>
        <p class="mui--text-danger">${message}</p>

        <div class="mui--text-right">
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                <a href="<spring:url value="/students/create"/>"
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
                <th>Group</th>
                <th>Birth date</th>
                <th>Email</th>
                <th>Role</th>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <th>Edit</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty s.photo}">
                                <img src="/uploads/students/${s.id}/${s.photo}" alt="${s.photo}" id="avatar"/>
                            </c:when>
                            <c:otherwise>
                                <img src="/uploads/1.png" alt="avatar" id="avatar"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${s.name}</td>
                    <td>${s.surname}</td>
                    <td>${s.groupName}</td>
                    <td>${s.birthDate}</td>
                    <td>${s.email}</td>
                    <td>${s.role}</td>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                        <td>
                            <a class="mui-btn mui-btn--small mui-btn--primary mui--z1"
                               href="<spring:url value="/students/edit/${s.id}"/>">Update</a>

                            <a class="mui-btn mui-btn--small mui-btn--danger mui--z1"
                               href="<spring:url value="/students/delete/${s.id}"/>">Delete</a>
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

