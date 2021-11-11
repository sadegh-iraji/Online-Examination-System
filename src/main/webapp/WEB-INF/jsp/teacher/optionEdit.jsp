<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/11/2021
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ویرایش گزینه ها</title>
</head>
<body>
<h1>ویرایش گزینه ها</h1>
<h2>ویرایش گزینه های سوال <c:out value="${mcQuestion.title}"/></h2>
<table align="center" class="border-table">
    <thead>
    <tr>
        <th>
            متن گزینه
        </th>
        <th>
            وضعیت گزینه
        </th>
        <th>
            تغییرات
        </th>
    </tr>
    </thead>
    <c:forEach items="${options}" var="option">
        <tr>
            <td>
                <c:out value="${option.content}"/>
            </td>
            <c:choose>
                <c:when test="${option.answer}">
                    <td style="color: green">گزینه درست</td>
                </c:when>
                <c:otherwise>
                    <td style="color: darkred">گزینه نادرست</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <form action="editOptionConfirm" method="post">
                <td>
                    <input type="text" name="content" placeholder="تغییر متن">
                </td>
                <td></td>
                <td>
                    <input type="hidden" name="id" value="<c:out value="${option.id}"/>">
                    <input type="submit" value="ویرایش گزینه" class="button-2">
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
<p><a href='/home' class='underline'>لغو و رفتن به منو</a></p>
</body>
</html>
