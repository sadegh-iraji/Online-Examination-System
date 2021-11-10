<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/3/2021
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>تایید ویرایش کاربر</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <h1>نام کاربری تکراری است</h1>
        <h2><c:out value="${message}"/></h2>
        <p><a href='/home' class='underline'>بازگشت به منو</a></p>
    </c:when>
    <c:otherwise>
        <h1>تایید ویرایش کاریر</h1>
        <h2>اطلاعات کاربر با موفقیت ویرایش گردید.</h2>
        <p><a href='/home' class='underline'>بازگشت به منو</a></p>
    </c:otherwise>
</c:choose>
</body>
</html>
