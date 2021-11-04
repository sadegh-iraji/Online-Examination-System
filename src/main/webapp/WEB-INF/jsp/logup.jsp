<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/1/2021
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ثبت نام</title>
</head>
<body>
<h1>ثبت نام در سامانه</h1>
<h2>ثبت نام</h2>
<form action="logupResult" method="post">
    <table align='center'>
        <tr>
            <td align='right'><label for='firstname'>نام  : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='firstname' id='firstname'></td>
        </tr>
        <tr>
            <td align='right'><label for='lastname'>نام خانوادگی : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='lastname' id='lastname'></td>
        </tr>
        <tr>
            <td align='right'><label for='username'>نام کاربری : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='username' id='username'></td>
        </tr>
        <tr>
            <td align='right'><label for='password'>کلمه عبور : </label></td>
            <td align='left'><input type='password' required='required' pattern='.+' name='password' id='password'></td>
        </tr>
        <tr>
            <td align='right'>
                <span>نقش کاربری :</span>
            </td>
            <td>
                <label for="teacher">استاد</label>
                <input type="radio" name="userType" id="teacher" value="<p:out value="${teacher.name()}"/>">
                <label for="student">دانشجو</label>
                <input type="radio" name="userType" id="student" value="<p:out value="${student.name()}"/>">
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type='submit' value='تایید ثبت نام'></td>
        </tr>
    </table>
</form>
</body>
</html>
