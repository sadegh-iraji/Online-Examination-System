<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/10/2021
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>استفاده از بانک سوالات دوره</title>
</head>
<body>
<h1>بانک سوالات دوره</h1>
<c:choose>
    <c:when test="${not empty message}">
        <h2><c:out value="${message}"/></h2>
    </c:when>
    <c:otherwise>
        <h2> بانک سوالات دوره <c:out value="${course.subject}"/></h2>
        <table align="center" class="border-table">
            <thead>
            <tr>
                <th>
                    عنوان سوال
                </th>
                <th>
                    متن سوال
                </th>
                <th>
                    نوع سوال
                </th>
                <th>
                    انتخاب سوال
                </th>
            </tr>
            </thead>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td>
                        <c:out value="${question.title}"/>
                    </td>
                    <td>
                        <c:out value="${question.content}"/>
                    </td>
                    <td>
                        <c:out value="${question.QType.toString()}"/>
                    </td>
                    <td>
                        <form action="addQFromBankConfirm" method="post">
                            <input type="hidden" name="testId" value="${test.id}">
                            <input type="hidden" name="questionId" value="<c:out value="${question.id}"/>">
                            <input type="submit" value="انتخاب سوال" class="button-2">
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
