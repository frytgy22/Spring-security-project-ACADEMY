<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<form method="post" id="logout-form" action="<spring:url value="/logout"/>">
    <sec:csrfInput/>
</form>

<script>
    const logoutLink = document.getElementById("logout");
    logoutLink.addEventListener("click", (e) => {
        e.preventDefault();
        const logoutForm = document.getElementById("logout-form");
        logoutForm.submit();
    });
</script>