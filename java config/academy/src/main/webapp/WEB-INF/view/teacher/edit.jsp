<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="text" value="Search by teacher name and/or surname"/>
<c:set var="search" value="/teachers/search"/>
<c:set var="title" value="Edit teacher"/>
<%@include file="../include/header.jsp" %>
<%@include file="../include/script.jsp" %>

<div id="content-wrapper">
    <div class="mui--appbar-height"></div>
    <div class="mui-container-fluid">

        <br>
        <h1 class="mui--text-center">${title}</h1>

        <form:form modelAttribute="teacherDto" class="mui-form " method="post" enctype="multipart/form-data">
            <form:hidden path="id"/>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="name" required="required" id="name" maxlength="30" cssClass="validate"/>
                <form:label path="name" for="name">Name</form:label>
                <form:errors path="name" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="surname" required="required" id="surname" maxlength="30" cssClass="validate"/>
                <form:label path="surname" for="surname">Name</form:label>
                <form:errors path="surname" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield mui-textfield--float-label">
                <form:input path="email" required="required" id="email" type="email" cssClass="validate"/>
                <form:label path="email" for="email">Email</form:label>
                <form:errors path="email" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <form:hidden path="password"/>
            <form:hidden path="confirmPassword"/>

            <div class="mui-textfield">
                <form:input path="startWork" required="required" cssClass="validate" id="date" type="date"/>
                <form:label path="startWork" for="date">Start work date</form:label>
                <form:errors path="startWork" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <div class="mui-textfield mui-textfield--float-label">
                <form:select path="groupsId" id="groupSelect" cssClass="validate mui--z1" multiple="multiple">
                    <form:options class="" items="${groups}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:label for="groupSelect" path="groupsId">Groups</form:label>
                <form:errors path="groupsId" class="mui--text-danger" data-error="wrong" data-success="right"/>
            </div>

            <form:hidden path="role" value="ROLE_TEACHER"/>

            <%@include file="../include/inputFileUpload.jsp" %>

            <div class="mui--text-center">
                <button type="submit" class="mui-btn mui-btn--raised">Submit</button>
            </div>
        </form:form>

    </div>
</div>
</div>

<%@include file="../include/footer.jsp" %>
