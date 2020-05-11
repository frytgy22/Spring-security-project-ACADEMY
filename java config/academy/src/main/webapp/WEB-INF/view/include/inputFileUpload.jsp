<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="form-group">
    <form:hidden path="photo"/>
    <label for="exampleFormControlFile1">Photo</label>
    <input type="file" multiple class="form-control-file" id="exampleFormControlFile1" name="img"
           accept=".jpg, .jpeg, .png"/>
    <form:errors path="photo" class="mui--text-danger" data-error="wrong" data-success="right"/>
</div>
