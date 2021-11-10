<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/7/2021
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>لیست دوره های استاد</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty teacherCourses}">
        <h2>دوره های استاد <c:out value="${teacher.lastname}"/></h2>
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
                    آزمون های دوره
                </th>
            </tr>
            </thead>
            <c:forEach items="${teacherCourses}" var="course">
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
                        <form action="courseTests" method="post">
                            <input type="hidden" name="courseId" value="<c:out value="${course.id}"/>">
                            <input type="hidden" name="teacherId" value="<c:out value="${teacher.id}"/>">
                            <input type="submit" value="مشاهده آزمون های دوره" class="button-2">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>استاد <c:out value="${teacher.lastname}"/> هنوز به دوره ای اضافه نشده است</h2>
    </c:otherwise>
</c:choose>

<p><a href='/home' class='underline'>رفتن به منو</a></p>
</body>
</html>
