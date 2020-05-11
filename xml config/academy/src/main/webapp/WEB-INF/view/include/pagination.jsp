<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:if test="${page.totalPages>1}">

    <div class="mui--text-center" id="paginator">
        <ul class="pagination">

            <c:choose>
                <c:when test="${page.hasPrevious()}">
                    <li class="page-item">
                        <a class="page-link mui--z1"
                           href="${url}?page=${page.number-1}&size=${page.size}${find}" tabindex="-1">Previous
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link mui--z1" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="1" end="${page.totalPages}">
                <c:choose>
                    <c:when test="${page.number + 1 == i}">
                        <li class="page-item active">
                            <a style="background: #2196F3" class="page-link mui--z1"
                               href="${url}?page=${i-1}&size=${page.size}${find}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link mui--z1" href="${url}?page=${i-1}&size=${page.size}${find}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${page.hasNext()}">
                    <li class="page-item">
                        <a class="page-link mui--z1"
                           href="${url}?page=${page.number+1}&size=${page.size}${find}" tabindex="-1">Next
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link mui--z1" href="#" tabindex="-1" aria-disabled="true">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>

</c:if>