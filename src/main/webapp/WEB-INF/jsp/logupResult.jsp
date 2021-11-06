<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/1/2021
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
    <title>تایید ثبت نام</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <h1>نام کاربری تکراری است</h1>
        <h2><c:out value="${message}"/></h2>
        <p><a href='/' class='underline'>بازگشت به صفحه اصلی</a></p>
    </c:when>
    <c:otherwise>
        <h1>ثبت نام شما با موفقیت انجام شد</h1>
        <h2>حساب کاربری شما در انتظار تایید مدیریت ...</h2>
        <p><a href='/' class='underline'>بازگشت به صفحه اصلی</a></p>
    </c:otherwise>
</c:choose>

</body>
</html>
