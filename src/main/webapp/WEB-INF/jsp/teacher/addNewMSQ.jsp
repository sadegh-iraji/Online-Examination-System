<%--
  Created by IntelliJ IDEA.
  User: SadegH
  Date: 11/9/2021
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8 ">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <script src="../script/jQuery.js" type="text/javascript"></script>
    <title>اضافه کردن سوال چند گزینه ای</title>
    <style>
        div input {
            display: block;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<h1>افزودن سوال چندگزینه ای جدید</h1>
<form action="addNewMSQConfirm" method="post">
    <table align='center'>
        <tr>
            <td align='right'><label for='title'>عنوان سوال : </label></td>
            <td align='center'><input type='text' required='required' pattern='^.{1,20}$' name='title' id='title'>
            </td>
        </tr>
        <tr>
            <td align='right'><label for='content'>متن سوال : </label></td>
            <td align='center'><textarea name="content" id="content" cols="21" rows="5"></textarea></td>
        </tr>
        <tr>
            <td align='right'><label for='optionArea'>گزینه ها : </label></td>
            <td align='center'>
                <div class="control-group" align="center">
                    <div id="optionArea" class="controls">
                        <input id="option_1" type="text" name="options[]">
                    </div>
                    <br>
                    <button id="addNewOption" style="background-color: darkolivegreen; color: wheat;"> افزودن گزینه جدید </button>
                    <button id="addRemoveOption" style="background-color: crimson;color: wheat;"> حذف گزینه جدید </button>
                </div>
            </td>
        </tr>
        <tr>
            <td align='right'><label for='answer'>متن گزینه صحیح : </label></td>
            <td align='center'><input type="text" name="answer" id="answer" required='required' pattern='.+' placeholder="متن گزینه صحیح را کپی کنید"></td>
        </tr>
        <tr>
            <td align='right'><label for='score'>نمره پیش فرض سوال : </label></td>
            <td align='center'><input type="number" name="score" id="score" min="0" value="0"></td>
        </tr>
        <tr>
            <td><input type="hidden" name="testId" value="${testId}" /></td>
            <td align="center"><input type='submit' value='افزودن سوال جدید' style="width:230px" class="button-2"></td>
        </tr>
    </table>
</form>

<!-- var -->
<script type="text/javascript">
    var tagCount = 1;
    $("#addNewOption").click(function (e) {
        //Append new field
        e.preventDefault();
        var newField = $('#optionArea input:first').clone();
        newField.val("");
        tagCount++;
        newField.attr("id", "option_" + tagCount);
        $("#optionArea").append(newField);
    });

    $("#addRemoveOption").click(function (e) {
        //Append new field
        e.preventDefault();
        var newField = $('#option_' + tagCount);
        tagCount--;
        newField.remove();
    });
</script>
</body>
</html>
