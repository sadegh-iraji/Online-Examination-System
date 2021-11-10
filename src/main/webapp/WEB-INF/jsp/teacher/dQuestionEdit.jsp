<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/10/2021
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ویرایش سوال تشریحی</title>
</head>
<body>
<h1>ویرایش سوال تشریحی</h1>
<h2>ویرایش سوال <c:out value="${descriptiveQuestion.title}"/></h2>
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
            تغییرات
        </th>
    </tr>
    </thead>
    <tr>
        <td>
            <c:out value="${descriptiveQuestion.title}"/>
        </td>
        <td>
            <c:out value="${descriptiveQuestion.content}"/>
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
                <input type="hidden" name="id" value="<c:out value="${descriptiveQuestion.id}"/>">
                <input type="submit" value="ویرایش سوال" class="button-2">
            </td>
        </form>
    </tr>
</table>
<p><a href='/home' class='underline'>لغو و رفتن به منو</a></p>
</body>
</html>
