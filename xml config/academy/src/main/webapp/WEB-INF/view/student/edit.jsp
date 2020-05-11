<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Edit student"/>
<c:set var="search" value="/students/search"/>
<c:set var="text" value="Search by student name and/or surname"/>
<%@include file="../include/header.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>

        <form:form class="mui-form" modelAttribute="studentDto" method="post" enctype="multipart/form-data">
            <form:hidden path="id"/>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="name" required="required" id="name" maxlength="30" cssClass="validate"/>
                <form:label path="name" for="name">Name</form:label>
                <form:errors path="name" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="surname" required="required" id="surname" maxlength="30" cssClass="validate"/>
                <form:label path="surname" for="surname">Surname</form:label>
                <form:errors path="surname" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="email" required="required" id="email" type="email" cssClass="validate"/>
                <form:label path="email" for="email">Email</form:label>
                <form:errors path="email" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <form:hidden path="password"/>
            <form:hidden path="confirmPassword"/>

            <div class="mui-select">
                <form:select path="groupId" required="required" cssClass="validate" id="group">
                    <form:option value="" disabled="true" selected="true">Select group</form:option>
                    <form:options items="${groups}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:label for="group" path="groupId">Group</form:label>
                <form:errors path="groupId" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield">
                <form:input path="birthDate" required="required" cssClass="validate" id="date" type="date"/>
                <form:label path="birthDate" for="date">Birth date</form:label>
                <form:errors path="birthDate" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <form:hidden path="role" value="ROLE_STUDENT"/>

            <%@include file="../include/inputFileUpload.jsp" %>

            <sec:csrfInput/>

            <div class="mui--text-center">
                <button type="submit" class="mui-btn mui-btn--raised">Submit</button>
            </div>
        </form:form>

    </div>
</div>

<%@include file="../include/footer.jsp" %>
