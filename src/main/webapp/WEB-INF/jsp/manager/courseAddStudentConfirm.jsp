<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/3/2021
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>تایید اضافه شدن دانشجو به دوره</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <h2><c:out value="${message}"/></h2>
    </c:when>
    <c:otherwise>
        <h2>دانشجوی مورد نظر به دوره اضافه گردید</h2>
    </c:otherwise>
</c:choose>

<p><a href='/home' class='underline'>بازگشت به منو</a></p>
</body>
</html>
