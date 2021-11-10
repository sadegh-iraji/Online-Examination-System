<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>لیست دوره های موجود</title>
</head>
<body>
<h2>دوره های موجود</h2>
<table align="center" class="border-table">
    <thead>
    <tr>
        <th>
            عنوان دوره
        </th>
        <th>
            شناسه دوره
        </th>
        <th>
            تاریخ شروع دوره
        </th>
        <th>
            تاریخ پایان دوره
        </th>
        <th>
            ظرفیت دوره
        </th>
        <th>
            استاد دوره
        </th>
        <th>
            دانشجویان دوره
        </th>
    </tr>
    </thead>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>
                <c:out value="${course.subject}"/>
            </td>
            <td>
                <c:out value="${course.id}"/>
            </td>
            <td>
                <c:out value="${course.startDate}"/>
            </td>
            <td>
                <c:out value="${course.finishDate}"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${course.capacity==0}">
                        تکمیل
                    </c:when>
                    <c:otherwise>
                        <c:out value="${course.capacity}"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty course.teacher}">
                        <c:out value="${course.teacher.firstname}"/>
                        <c:out value="${course.teacher.lastname}"/>
                        <form action="courseTeacherSet" method="post">
                            <input type="hidden" name="id" value="<c:out value="${course.id}"/>">
                            <input type="submit" value="تغییر استاد">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="courseTeacherSet" method="post">
                            <input type="hidden" name="id" value="<c:out value="${course.id}"/>">
                            <input type="submit" value="اختصاص استاد" class="button-2">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="courseStudents" method="post">
                    <input type="hidden" name="id" value="<c:out value="${course.id}"/>">
                    <input type="submit" value="مشاهده دانشجویان" class="button-2">
                </form>
            </td>
        </tr>
    </c:forEach>

</table>

<p><a href='/home' class='underline'>بازگشت به منو</a></p>

</body>
</html>
