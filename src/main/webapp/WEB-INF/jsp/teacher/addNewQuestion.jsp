<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/9/2021
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>افزودن سوال جدید</title>
</head>
<body>
<h1>افزودن سوال جدید به آزمون</h1>
<h2>انتخاب نوع سوال جدید</h2>
<div align="center">
    <form action="addNewDQ" method="post">
        <input type="hidden" name="testId" value="${test.id}">
        <input type="submit" value="سوال تشریحی" class="button-1">
    </form>
    <form action="addNewMSQ" method="post">
        <input type="hidden" name="testId" value="${test.id}">
        <input type="submit" value="سوال چند گزینه ای" class="button-1">
    </form>
</div>
</body>
</html>
