<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/8/2021
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>افزودن آزمون جدید</title>
</head>
<body>
<h1>تعریف آزمون جدید</h1>
<h2>افزودن آزمون جدید به دوره <c:out value="${course.subject}"/></h2>
<form action="testCreated" method="post">
    <table align='center'>
        <tr>
            <td align='right'><label for='subject'>عنوان آزمون : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='subject' id='subject'></td>
        </tr>
        <tr>
            <td align='right'><label for='description'>توضیخات آزمون : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='description' id='description'></td>
        </tr>
        <tr>
            <td align='right'><label for='time'>زمان آزمون (به دقیقه) : </label></td>
            <td align='left'><input type='number' required='required' name='time' id='time'></td>
        </tr>
        <tr>
            <td><input type="hidden" value="${course.id}" name="courseId"></td>
            <td><input type='submit' value='ذخیره آزمون'></td>
        </tr>
    </table>
</form>

<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
