<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 16.02.2022
  Time: 16:22
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

    <title>Profile</title>
</head>
<body>
<h3>Профиль</h3>
<table border="1px">
    <tr>
        <th width="33%">Логин пользователя</th>
        <th width="33%"><c:out value="${user.login}"/></th>

    </tr>
    <tr>
        <th width="33%">ФИО пользователя</th>
        <th width="33%"><c:out value="${user.name}"/></th>
    </tr>
    <tr>
        <th width="33%">Дата рождения</th>
        <th width="33%"><c:out value="${user.birthday}"/></th>
    </tr>
    <tr>
        <th width="33%">Удаление аккаунта</th>
        <th width="33%">
            <form id="user-form">
                <p><input type="submit" value="Удалить"></p>
            </form>
        </th>
    </tr>
</table>

<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/profile/update'"
          value="Обновить профиль"></p>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/main'" value="На главную"></p>

<script>
    $("#user-form").submit(function (event) {
        event.preventDefault();
        let url = '${pageContext.request.contextPath}/profile/delete';
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function(result) {
                window.location = '${pageContext.request.contextPath}/leave';
            }
        });
    });
</script>

</body>
</html>
