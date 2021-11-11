<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/11/2021
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ویرایش سوال چند گزینه ای</title>
</head>
<body>
<h1>ویرایش سوال چند گزینه ای</h1>
<h2>ویرایش سوال <c:out value="${mcQuestion.title}"/></h2>
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
            نمره سوال
        </th>
        <th>
            تغییرات گزینه ها
        </th>
        <th>
            تغییرات
        </th>
    </tr>
    </thead>
    <tr>
        <td>
            <c:out value="${mcQuestion.title}"/>
        </td>
        <td>
            <c:out value="${mcQuestion.content}"/>
        </td>
        <td>
            <c:out value="${tasq.score}"/>
        </td>
        <td>
            <form action="optionEdit" method="post">
                <input type="hidden" name="questionId" value="${mcQuestion.id}">
                <input type="submit" value="ویرایش گزینه ها" class="button-2">
            </form>
        </td>
    </tr>
    <tr>
        <form action="editDQuestionConfirm" method="post">
            <td>
                <input type="text" name="title" placeholder="تغییر عنوان">
            </td>
            <td>
                <input type="text" name="content" placeholder="تغییر متن">
            </td>
            <td>
                <input type="number" name="score" placeholder="تغییر نمره">
            </td>
            <td>
                <input type="hidden" name="id" value="<c:out value="${mcQuestion.id}"/>">
                <input type="hidden" name="tasqId" value="<c:out value="${tasq.id}"/>">
                <input type="submit" value="ویرایش سوال" class="button-2">
            </td>
        </form>
    </tr>
</table>
<p><a href='/home' class='underline'>لغو و رفتن به منو</a></p>
</body>
</html>
