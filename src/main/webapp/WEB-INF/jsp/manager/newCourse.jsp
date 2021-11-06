<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/2/2021
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>تعریف دوره جدید</title>
</head>
<body>
<h1>تعریف دوره جدید</h1>
<h2>ایجاد دوره</h2>
<form action="courseCreated" method="post">
    <table align='center'>
        <tr>
            <td align='right'><label for='subject'>عنوان دوره : </label></td>
            <td align='left'><input type='text' required='required' pattern='.+' name='subject' id='subject'></td>
        </tr>
        <tr>
            <td align='right'><label for='start'>تاریخ شروع دوره : </label></td>
            <td align='left'><input type='date' required='required' name='startDate' id='start'></td>
        </tr>
        <tr>
            <td align='right'><label for='end'>تاریخ پایان دوره : </label></td>
            <td align='left'><input type='date' required='required' name='finishDate' id='end'></td>
        </tr>
        <tr>
            <td align='right'><label for='capacity'>ظرفیت دوره : </label></td>
            <td align='left'><input type='number' required='required' pattern='.+' min="1" name='capacity'
                                    id='capacity'></td>
        </tr>
        <tr>
            <td></td>
            <td><input type='submit' value='ثبت دوره جدید'></td>
        </tr>
    </table>

    <script>
        var start = document.getElementById('start');
        var end = document.getElementById('end');

        start.addEventListener('change', function () {
            if (start.value)
                end.min = start.value;
        }, false);
        end.addEventLiseter('change', function () {
            if (end.value)
                start.max = end.value;
        }, false);
    </script>
</form>
</body>
</html>
