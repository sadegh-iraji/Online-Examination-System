<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %><%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>منوی استاد</title>
</head>
<body>
<h1>منوی استاد</h1>
<h2> خوش آمدید <% out.print(SecurityContextHolder.getContext().getAuthentication().getName());%></h2>
<div id='divMenu'>
    <ul>
        <li><a href='teacher/teacherCourses'>مشاهده لیست دوره های مدرس</a></li>
        <li><a href='/logout'>خروج</a></li>

    </ul>
</div>
</body>
</html>
