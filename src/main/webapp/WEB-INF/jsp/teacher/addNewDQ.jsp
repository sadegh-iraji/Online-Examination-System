<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/9/2021
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>افزودن سوال تشریحی</title>
</head>
<body>
<h1>افزودن سوال تشریحی جدید</h1>

<form action="addNewDQConfirm" method="post">
    <table align='center'>
        <tr>
            <td align='right'><label for='title'>عنوان سوال : </label></td>
            <td align='left'><input type='text' required='required' pattern='^.{1,20}$' name='title' id='title'></td>
        </tr>
        <tr>
            <td align='right'><label for='content'>متن سوال : </label></td>
            <td align='left'><textarea name="content" id="content" cols="30" rows="10"></textarea></td>
        </tr>
        <tr>
            <td align='right'><label for='score'>نمره پیش فرض سوال : </label></td>
            <td align='left'><input type="number" name="score" id="score" min="0" step="0.1" value="0"></td>
        </tr>
        <tr>
            <td><input type="hidden" name="testId" value="${test.id}"/></td>
            <td><input type='submit' value='افزودن سوال جدید'></td>
        </tr>
    </table>
</form>
<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
