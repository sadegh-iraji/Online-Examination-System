<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/3/2021
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>ویرایش اطلاعات کاربر</title>
</head>
<body>
<h2>ویرایش اطلاعات کاربر</h2>
<table align="center" class="border-table">
    <thead>
    <tr>
        <th>
            نام
        </th>
        <th>
            نام خانوادگی
        </th>
        <th>
            نام کاربری
        </th>
        <th>
            وضعیت
        </th>
        <th>
            تغییرات
        </th>
    </tr>
    </thead>
    <tr>
        <td>
            <c:out value="${user.firstname}"/>
        </td>
        <td>
            <c:out value="${user.lastname}"/>
        </td>
        <td>
            <c:out value="${user.username}"/>
        </td>
        <td>
            <c:choose>
                <c:when test="${user.isActive}">تایید شده</c:when>
                <c:otherwise>در انتظار تایید ...</c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <form action="editUserConfirm" method="get">
            <td>
                <input type="text" name="firstname" placeholder="تغییر نام">
            </td>
            <td>
                <input type="text" name="lastname" placeholder="تغییر نام خانوادگی">
            </td>
            <td>
                <input type="text" name="username" placeholder="تغییر نام کاربری">
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.isActive}">
                        <select name="isActive" id="isActive">
                            <option value="">فعال بماند</option>
                            <option value="false">
                                عیر فعال شود
                            </option>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <select name="isActive" id="isActive">
                            <option value="">غیرفعال بماند</option>
                            <option value="true">
                                فعال شود
                            </option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                <input type="submit" value="ویرایش کاربر" class="button-2">
            </td>
        </form>
    </tr>
</table>

<p><a href='/home' class='underline'>لغو و رفتن به منو</a></p>


</body>
</html>
