<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>

<h3>Регистрация нового пользователя:</h3>

<c:if test="${userCreated == true}">
    <p style="color: red">Пользователь с таким логином уже существует!</p>
</c:if>

<c:if test="${emptyFormField == true}">
    <p style="color: red">Заполните все поля формы регистрации!</p>
</c:if>

<form action="/MK_JD2-88-2-0.0.0/signUp" method="post">
    <p><b>Логин</b> <input type="text" placeholder="Логин пользователя" name="login"></p>
    <p><b>Пароль</b> <input type="password" placeholder="Пароль" name="password"></p>
    <p><b>ФИО</b> <input type="text" placeholder="ФИО пользователя" name="name"></p>
    <p><b>Дата рождения</b> <input type="date", placeholder="Дата рождения" name="birthday"></p>
    <p><input type="submit" value="Зарегистрироваться"></p>
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/main'" value="На главную"></p>
</form>

</body>
</html>
