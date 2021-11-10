<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/10/2021
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>تاییدیه ویرایش سوال تشریحی</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <h2><c:out value="${message}"/></h2>
    </c:when>
    <c:otherwise>
        <h1>تایید ویرایش سوال</h1>
        <h2>سوال با موفقیت ویرایش گردید</h2>
    </c:otherwise>
</c:choose>
<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
