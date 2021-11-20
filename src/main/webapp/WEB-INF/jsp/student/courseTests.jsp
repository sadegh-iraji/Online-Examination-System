<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/13/2021
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>آزمون های دوره</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty courseTests}">
        <h2>آزمون های دوره <c:out value="${course.subject}"/> </h2>
        <table align="center" class="border-table">
            <thead>
            <tr>
                <th>
                    عنوان آزمون
                </th>
                <th>
                    زمان آزمون
                </th>
                <th>
                    شرکت در آزمون
                </th>
            </tr>
            </thead>
            <c:forEach items="${courseTests}" var="test">
                <tr>
                    <td>
                        <c:out value="${test.subject}"/>
                    </td>
                    <td>
                        <c:out value="${test.time}"/>
                    </td>
                    <td>
                        <form action="startTest" method="post">
                            <input type="hidden" name="testId" value="<c:out value="${test.id}"/>">
                            <input type="hidden" name="studentId" value="<c:out value="${student.id}"/>">
                            <input type="submit" value="شروع آزمون" class="button-2">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>برای دوره <c:out value="${course.subject}"/>  آزمونی ثبت نشده یا قبلا در تمامی آنها شرکت کرده اید</h2>
    </c:otherwise>
</c:choose>

<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
