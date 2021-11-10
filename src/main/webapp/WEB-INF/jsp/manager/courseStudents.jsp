<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>دانشجویان دوره</title>
</head>
<body>

<h1>لیست دانشجویان</h1>

<c:choose>
    <c:when test="${not empty students}">
        <h2>لیست دانشجویان دوره <c:out value="${course.subject}"/></h2>
    </c:when>
    <c:otherwise>
        <h2>دانشجویی به دوره اضافه نشده است</h2>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${course.capacity!=0}">
        <div align="center">
            <form action="courseAddStudent" method="post">
                <input type="hidden" name="id" value="${course.id}">
                <input type="submit" value="اضافه کردن دانشجو به دوره" class="button-1">
            </form>
        </div>
    </c:when>
</c:choose>

<br>
<br>

<c:choose>
    <c:when test="${not empty students}">
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
                    تغییرات
                </th>
            </tr>
            </thead>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>
                        <c:out value="${student.firstname}"/>
                    </td>
                    <td>
                        <c:out value="${student.lastname}"/>
                    </td>
                    <td>
                        <form action="removeStudentFromCourse" method="post">
                            <input type="hidden" name="studentId" value="${student.id}">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <input type="submit" value="حذف دانشجو از دوره" class="button-2">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>


<p><a href='/home' class='underline'>بازگشت به منو</a></p>

</body>
</html>
