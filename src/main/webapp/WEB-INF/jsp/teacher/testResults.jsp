<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/21/2021
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>نتایج آزمون</title>
</head>
<body>
<h1>نتایج آزمون <c:out value="${test.subject}"/></h1>
<c:choose>
    <c:when test="${empty test_students}"><h2><c:out value="${message}"/></h2></c:when>
    <c:otherwise>
        <h2>لیست شرکت کنندگان و نتایج آزمون</h2>
        <table align="center" class="border-table">
            <thead>
            <tr>
                <th>
                    مشخصات دانشجو
                </th>
                <th>
                    نتیجه آزمون
                </th>
                <th>
                    مشاهده و تصحیح
                </th>
            </tr>
            </thead>
            <c:forEach items="${test_students}" var="test_student">
                <tr>
                    <td>
                        <c:out value="${test_student.student.firstname}"/>
                        <c:out value="${test_student.student.lastname}"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${test_student.totalScore == null}">
                                <p>تصحیح نشده</p>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${test_student.totalScore}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="editResult" method="post">
                            <input type="hidden" name="test_studentId" value="<c:out value="${test_student.id}"/>">
                            <input type="submit" value="تصحیح نتیجه آزمون" class="button-2">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
