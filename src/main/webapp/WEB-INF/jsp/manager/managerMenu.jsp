<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %><%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/1/2021
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>منوی مدیریت</title>
</head>
<body>
<h1>منوی مدیریت</h1>
<h2> خوش آمدید <% out.print(SecurityContextHolder.getContext().getAuthentication().getName());%></h2>
</body>
<div id='divMenu'>
    <ul>
        <li><a href='manager/registeredUsers'>مشاهده لیست کاربران ثبت نام شده</a></li>
        <li><a href='manager/newCourse'>تعریف دوره جدید</a></li>
        <li><a href='manager/courses'>مشاهده دوره های موجود</a></li>
        <li><a href='/logout'>خروج</a></li>
    </ul>
</div>
</html>
