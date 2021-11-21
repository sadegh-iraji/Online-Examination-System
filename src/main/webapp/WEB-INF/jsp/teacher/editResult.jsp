<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/21/2021
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <script src="/script/jQuery.js" type="text/javascript"></script>
    <title>تصحیح آزمون</title>
</head>
<body>
<h1>تصحیح آزمون</h1>
<h2>نتیجه آزمون ${test_student.test.subject} متعلق
    به ${test_student.student.firstname} ${test_student.student.lastname}</h2>
<table align="center" class="border-table">
    <thead>
    <tr>
        <th>
            سوالات
        </th>
        <th>
            نمره
        </th>
    </tr>
    </thead>
    <c:forEach items="${answers}" var="answer">
        <tr>
            <td>
                <p>نوع سوال : <c:out value="${answer.tasq.question.QType.toString()}"/></p>
                <p>متن سوال :</p>
                <p><c:out value="${answer.tasq.question.content}"/></p>
                <p>پاسخ دانشجو :</p>
                <p><c:out value="${answer.content}"/></p>
            </td>
            <td>
                <p>نمره سوال : <c:out value="${answer.tasq.score}"/></p>
                <p>نمره دانشجو :</p>
                <c:choose>
                    <c:when test="${answer.tasq.question.QType == DQ}">
                        <form method="post" id="form_${answer.id}">
                            <input type="hidden" name="answerId" value="${answer.id}">
                            <input type="hidden" name="test_studentId" value="${test_student.id}">
                            <input type="number" name="answerScore" id="answerScore" value="${answer.score}"
                                   min="0" max="${answer.tasq.score}">
                            <input type="button" onclick="submitForm('form_${answer.id}')" value="تایید نمره دانشجو">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${answer.score}"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <p>مجموع نمرات کسب شده دانشجو : </p>
        </td>
        <td>
            <p id="totalScore">
                <c:if test="${test_student.totalScore != null}">
                    <c:out value="${test_student.totalScore}"/>
                </c:if>
            </p>
        </td>
    </tr>
</table>
<p><a href='/home' class='underline'>رفتن به منو</a></p>
<script defer type="text/javascript">
    function submitForm(answerId) {
        var formId = ("#".concat(answerId));
        $.ajax({
            type: "Post",
            url: "/teacher/editAnswerScore",
            dataType: "json",
            data: $(formId).serialize(),
            success: function (data){
                $("#totalScore").text(data);
            }
        })
    }
</script>
</body>
</html>
