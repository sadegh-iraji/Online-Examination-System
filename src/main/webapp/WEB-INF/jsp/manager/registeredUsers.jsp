<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <script src="../script/jQuery.js" type="text/javascript"></script>
    <title>کاربرهای ثبت نام شده</title>
</head>
<body>
<h2>لیست تمامی کاربران ثبت نام شده</h2>
<h3 align="center">فیلتر بر اساس:</h3>
<div align="center">
    <input type="text" id="name" class="search-key" placeholder="نام">
    <input type="text" id="lastname" class="search-key" placeholder="نام خانوادگی">
    <input type="text" id="role" class="search-key" placeholder="نقش">
    <input type="text" id="username" class="search-key" placeholder="نام کاربری">
    <input type="text" id="activation" class="search-key" placeholder="وضعیت">
    <input type="hidden" id="change" class="search-key" placeholder="ch">
</div>
<br>
<br>
<table align="center" class="border-table" id="MI6">
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
            نقش کاربری
        </th>
        <th>
            وضعیت
        </th>
        <th>
            تغییرات
        </th>
    </tr>
    <c:forEach items="${registeredUsers}" var="user">
        <tr>
            <td data-input="name">
                <c:out value="${user.firstname}"/>
            </td>
            <td data-input="lastname">
                <c:out value="${user.lastname}"/>
            </td>
            <td data-input="username">
                <c:out value="${user.username}"/>
            </td>
            <td data-input="role">
                <c:out value="${user.userType}"/>
            </td>
            <td data-input="activation">
                <c:choose>
                    <c:when test="${user.isActive}">تایید شده</c:when>
                    <c:otherwise>در انتظار تایید ...</c:otherwise>
                </c:choose>
            </td>
            <td data-input="change">
                <form action="editUser" method="post">
                    <input type="hidden" name="userId" value="<c:out value="${user.id}"/>">
                    <input type="submit" value="ویرایش کاربر" class="button-2">
                </form>
            </td>
        </tr>
    </c:forEach>

</table>

<p><a href='/home' class='underline'>رفتن به منو</a></p>

<script>
    var $filterableRows = $('#MI6').find('tr').not(':first'),
        $inputs = $('.search-key');

    $inputs.on('input', function () {

        $filterableRows.hide().filter(function () {
            return $(this).find('td').filter(function () {

                var tdText = $(this).text().toLowerCase(),
                    inputValue = $('#' + $(this).data('input')).val().toLowerCase();

                return tdText.indexOf(inputValue) != -1;

            }).length == $(this).find('td').length;
        }).show();

    });
</script>
</body>
</html>
