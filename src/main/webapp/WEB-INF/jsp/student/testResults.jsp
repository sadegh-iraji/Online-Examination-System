<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/23/2021
  Time: 4:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>نتایج آزمون ها</title>
</head>
<body>
<h1>نتایج آزمون های دانشجو</h1>
<c:choose>
    <c:when test="${not empty message || empty test_student}">
        <c:out value="${message}"/>
    </c:when>
    <c:otherwise>
        <table align="center" class="border-table">
            <thead>
            <tr>
                <th>
                    عنوان آزمون
                </th>
                <th>
                    تاریخ و زمان پایان آزمون
                </th>
                <th>
                    نمره کل آزمون
                </th>
                <th>
                    نمره آزمون دانشجو
                </th>
            </tr>
            </thead>
            <c:forEach items="${test_student}" var="test_student">
                <tr>
                    <td>
                        <c:out value="${test_student.test.subject}"/>
                    </td>
                    <td>
                        <c:out value="${test_student.finishTime}"/>
                    </td>
                    <td>
                        <c:out value="${test_student.test.testScore}"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${test_student.totalScore == null}">
                                آزمون تصحیح نشده است
                            </c:when>
                            <c:otherwise>
                                <c:out value="${test_student.totalScore}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
