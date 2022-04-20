<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>

    <title>Update</title>
</head>
<body>

<h3>Обновление данных пользователя:</h3>

<form id="user-form">
    <p><b>Логин</b> <input type="text" placeholder="Новый логин" name="login"></p>
    <p><b>Пароль</b> <input type="password" placeholder="Новый пароль" name="password"></p>
    <p><b>ФИО</b> <input type="text" placeholder="Новое ФИО" name="name"></p>
    <p><b>Дата рождения</b> <input type="date" , placeholder="Новая дата рождения" name="birthday"></p>
    <p><input type="submit" value="Обновить"></p>
</form>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/main'" value="На главную"></p>


<script>
    $("#user-form").submit(function (event) {
        event.preventDefault();
        let form = $(this);
        let url = '${pageContext.request.contextPath}/profile/update';

        let login = form.find('input[name="login"]').val();
        let password = form.find('input[name="password"]').val();
        let name = form.find('input[name="name"]').val();
        let birthday = form.find('input[name="birthday"]').val();
        let data;

        if (login) {
            data = "login=" + login;
        }

        if (password && data) {
            data += "&password=" + password;
        } else if (password) {
            data += "password=" + password;
        }

        if (name && data) {
            data += "&name=" + name;
        } else if (name) {
            data += "name=" + name;
        }

        if (birthday && data) {
            data += "&birthday" + birthday;
        } else if (birthday) {
            data += "birthday" + birthday;
        }

        $.ajax({
            type: 'PUT',
            url: url,
            contentType: 'application/x-www-form-urlencoded',
            data: "login=" + login + "&password=" + password + "&name=" + name + "&birthday=" + birthday,
            success: function (data) {
                $("#result").html(data +
                    " link: <a href='" + url + "'>" + url + "</a>");
                window.location.href = '${pageContext.request.contextPath}/profile';
            }
        });
    });
</script>

</body>
</html>
