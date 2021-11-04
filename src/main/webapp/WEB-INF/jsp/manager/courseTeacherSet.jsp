<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>انتخاب استاد دوره</title>
</head>
<body>
<h1>انتخاب استاد دوره</h1>
<h2>انتخاب استاد برای دوره <c:out value="${course.subject}"/></h2>
<form action="courseTeacherSetConfirm" method="post">
    <table align="center">
        <tr>
            <td>
                <label for="teacherId">انتخاب استاد :</label>
                <select name="teacherId" id="teacherId">
                    <option value="" disabled selected>لیست اساتید ...</option>
                    <c:forEach items="${teachers}" var="student">
                        <option value="<c:out value="${student.id}"/>">
                            <c:out value="${student.firstname}"/> <c:out value="${student.lastname}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="hidden" name="courseId" value="${course.id}" class="button-2">
                <input type='submit' value='انتخاب استاد برای دوره'>
            </td>
        </tr>
    </table>
</form>
<p><a href='/home' class='underline'>انصراف و بازگشت به منو</a></p>
</body>
</html>
