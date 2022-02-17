<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 16.02.2022
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h3>Профиль</h3>
<table border="1px">
    <tr>
        <th width="33%">Логин пользователя</th>
        <th width="33%"> <c:out value="${user.login}"/> </th>

    </tr>
    <tr>
        <th width="33%">ФИО пользователя</th>
        <th width="33%"> <c:out value="${user.name}"/> </th>
    </tr>
    <tr>
        <th width="33%">Дата рождения</th>
        <th width="33%"> <c:out value="${user.birthday}"/> </th>
    </tr>
    <tr>
        <th width="33%">Удаление аккаунта</th>
        <th width="33%"><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/profile/delete'" value="Удалить"> </th>
    </tr>
</table>
<p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/main'" value="На главную"></p>

</body>
</html>
