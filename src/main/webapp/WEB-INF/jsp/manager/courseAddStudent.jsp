<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/3/2021
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>اضافه کردن دانشجو به دوره</title>
</head>
<body>
<h1>اضافه کردن دانشجو به دوره</h1>
<h2>اضافه کردن دانشجو به دوره <c:out value="${course.subject}"/></h2>
<form action="courseAddStudentConfirm" method="post">
    <table align="center">
        <tr>
            <td>
                <label for="studentId">انتخاب دانشجو :</label>
                <select name="studentId" id="studentId">
                    <option value="">لیست دانشجویان ...</option>
                    <c:forEach items="${students}" var="student">
                        <option value="<c:out value="${student.id}"/>">
                            <c:out value="${student.firstname}"/>
                            <c:out value="${student.lastname}"/>
                            / <c:out value="${student.username}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="hidden" name="courseId" value="${course.id}">
                <input type='submit' value='اضافه کردن دانشجوی انتخابی به دوره' class="button-2">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
