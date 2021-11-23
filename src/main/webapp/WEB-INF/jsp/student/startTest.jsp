<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/15/2021
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="/styles/style.css" type="text/css">
    <script src="/script/jQuery.js" type="text/javascript"></script>
    <title>برگزاری آزمون</title>

</head>
<body>
<h1>برگزاری آزمون</h1>
<h2>آزمون <c:out value="${test.subject}"/></h2>

<div class="timer"><span id="time">--:--</span></div>

<c:set value="${tasqList}" var="tasqPageList"/>
<c:set value="false" var="flag"/>

<c:forEach items="${tasqPageList.pageList}" var="tasq">
    <form method="post" id="form_${tasqList.page}">

        <c:out value="${tasq.question.content}"/>
        <br>
        <c:choose>
            <c:when test="${tasq.question.QType eq DQ}">
                <c:forEach items="${answers}" var="answer">
                    <c:if test="${answer.tasq.id == tasq.id}">
                        <c:set var="hasAnswer" value="${answer.content}"/>
                    </c:if>
                </c:forEach>
                <input type="text" name="answer" id="answer" size="100" value="${hasAnswer}" placeholder="پاسخ">
            </c:when>
            <c:otherwise>
                <c:forEach items="${tasq.question.selections}" var="selection">
                    <br>
                    <c:forEach items="${answers}" var="answer">
                        <c:if test="${answer.tasq.id == tasq.id}">
                            <c:set var="hasAnswer" value="${answer.content}"/>
                        </c:if>
                    </c:forEach>
                    <label for="selection_<<c:out value="${selection.id}"/>>">
                        <c:out value="${selection.content}"/>
                    </label>
                    <input type="radio" name="answer" id="selection_<<c:out value="${selection.id}"/>>"
                           value="<c:out value="${selection.content}"/>"
                    <c:if test="${hasAnswer == selection.content}">
                           checked
                    </c:if>>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <input type="hidden" name="tasqId" id="tasqId" value="${tasq.id}">
        <input type="hidden" name="testId" id="testId" value="${sessionScope.testId}">
    </form>
</c:forEach>

<c:if test="${tasqPageList.lastPage}">
    <form method="post" id="finishTest">
        <input type="hidden" name="test_studentId" value="${sessionScope.test_student.id}">
        <input type="button" value="پایان آزمون" id="finishButton"
               onclick="submitForm('/student/startTest/${tasqPageList.page+1}');submitFinishTest()">
    </form>
</c:if>

<br/>

<c:choose>
    <%-- Show Prev as link if not on first page --%>
    <c:when test="${tasqPageList.firstPage}">
        <span>قبلی</span>
    </c:when>
    <c:otherwise>
        <c:url value="/student/startTest/prev" var="url"/>
        <a onclick="submitForm('${url}')" href='<c:out value="${url}" />'>قبلی</a>
    </c:otherwise>
</c:choose>
<c:forEach begin="1" end="${tasqPageList.pageCount}" step="1" varStatus="tagStatus">
    <c:choose>
        <%-- In PagedListHolder page count starts from 0 --%>
        <c:when test="${(tasqPageList.page + 1) == tagStatus.index}">
            <span>${tagStatus.index}</span>
        </c:when>
        <c:otherwise>
            <c:url value="/student/startTest/${tagStatus.index}" var="url"/>
            <a onclick="submitForm('${url}')" href='<c:out value="${url}" />'>${tagStatus.index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:choose>
    <%-- Show Next as link if not on last page --%>
    <c:when test="${tasqPageList.lastPage}">
        <span>بعدی</span>
    </c:when>
    <c:otherwise>
        <c:url value="/student/startTest/next" var="url"/>
        <a onclick="submitForm('${url}')" href='<c:out value="${url}" />'>بعدی</a>
    </c:otherwise>
</c:choose>
<script defer type="text/javascript">
    function submitForm(url) {
        $.ajax({
            type: "Post",
            url: url,
            dataType: "json",
            data: $("#form_${tasqList.page}").serialize()
        })
    }

    function submitFinishTest() {
        $.ajax({
            type: "Post",
            url: "/student/finishTest",
            dataType: "json",
            data: $("#finishTest").serialize()
        })
        window.location.href = ("/student/finishTest");
    }

    // The data/time we want to countdown to
    var countDownDate = new Date('${sessionScope.test_student.finishTime.toString()}').getTime();

    // Run myfunc every second
    var myfunc = setInterval(function() {

        var now = new Date().getTime();
        var timeleft = countDownDate - now;

// Calculating the days, hours, minutes and seconds left
        var minutes = Math.floor((timeleft % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((timeleft % (1000 * 60)) / 1000);

// Result is output to the specific element

        document.getElementById("time").innerHTML = minutes + ":" + seconds

// Display the message when countdown is over
        if (--timeleft < 0) {
            clearInterval(myfunc);
            submitForm('/student/startTest/${tasqPageList.page+1}');
            submitFinishTest();
        }
    }, 1000);
</script>
</body>
</html>
