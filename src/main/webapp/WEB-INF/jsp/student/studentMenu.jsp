<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %><%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>منوی دانشجو</title>
</head>
<body>
<h1>منوی دانشجو</h1>
<h2> خوش آمدید <% out.print(SecurityContextHolder.getContext().getAuthentication().getName());%></h2>
<div id='divMenu'>
    <ul>
        <li><a href='student/studentCourses'>مشاهده لیست دوره های دانشجو</a></li>
        <li><a href='/logout'>خروج</a></li>

    </ul>
</div>
</body>
</html>
