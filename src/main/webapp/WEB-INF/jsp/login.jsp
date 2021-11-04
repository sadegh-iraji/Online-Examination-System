<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/1/2021
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fa">
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ورود</title>
</head>
<body onload='document.loginForm.username.focus();'>
<h1>صفحه ورود</h1>
<h2>ورود به برنامه</h2>
<c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;text-align: center">${errorMessge}</div></c:if>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
    <div class="error" style="color:red; font-weight: bold; margin: 30px 0px;text-align: center">
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
    </div>
</c:if>
<div id='divMenu'>
    <form name='login' action="/login" method='POST'>
        <table>
            <tr>
                <td>نام کاربری :</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>کلمه عبور :</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="ورود" /></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>
