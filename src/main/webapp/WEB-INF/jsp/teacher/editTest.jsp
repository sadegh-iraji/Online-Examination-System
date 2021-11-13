<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/9/2021
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ویرایش آزمون</title>
</head>
<body>
<h1>ویرایش آزمون</h1>
<c:choose>
    <c:when test="${not empty message}">
        <h2><c:out value="${message}"/></h2>
    </c:when>
    <c:otherwise>
        <h2> سوالات ثبت شده برای آزمون <c:out value="${test.subject}"/></h2>
    </c:otherwise>
</c:choose>

<div align="center">
    <form action="addQuestionFromBank" method="post">
        <input type="hidden" name="testId" value="${test.id}">
        <input type="submit" value="افزودن سوال از بانک سوالات" class="button-1">
    </form>
    <form action="addNewQuestion" method="post">
        <input type="hidden" name="testId" value="${test.id}">
        <input type="submit" value="افزودن سوال جدید" class="button-1">
    </form>
</div>

<c:choose>
    <c:when test="${empty message}">

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
                    نمره سوال
                </th>
                <th>
                    ویرایش
                </th>
                <th>
                    حذف
                </th>
                <th>
                    حذف از آزمون
                </th>
            </tr>
            </thead>
            <c:forEach items="${tasqList}" var="tasq">
                <tr>
                    <td>
                        <c:out value="${tasq.question.title}"/>
                    </td>
                    <td>
                        <c:out value="${tasq.question.content}"/>
                    </td>
                    <td>
                        <c:out value="${tasq.question.QType.toString()}"/>
                    </td>
                    <td>
                        <c:out value="${tasq.score}"/>
                    </td>
                    <td>
                        <form action="editQuestion" method="post">
                            <input type="hidden" name="questionId" value="<c:out value="${tasq.question.id}"/>">
                            <input type="hidden" name="tasqId" value="<c:out value="${tasq.id}"/>">
                            <input type="submit" value="ویرایش سوال" class="button-2">
                        </form>
                    </td>
                    <td>
                        <form action="deleteQuestionFromTest" method="post">
                            <input type="hidden" name="tasqId" value="<c:out value="${tasq.id}"/>">
                            <input type="submit" value="حذف سوال از آزمون" class="button-2">
                        </form>
                    </td>
                    <td>
                        <form action="deleteQuestion" method="post">
                            <input type="hidden" name="questionId" value="<c:out value="${tasq.question.id}"/>">
                            <input type="submit" value="حذف سوال" class="button-2">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tfoot>
            <tr>
                <td colspan="3">
                    مجموع نمرات آزمون
                </td>
                <td>
                    <c:out value="${testScore}"/>
                </td>
            </tr>
            </tfoot>
        </table>

    </c:when>
</c:choose>

<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
