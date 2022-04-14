<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 16.02.2022
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body>

<h3>Статистика сайта</h3>
<table border="1px">
    <tr>
        <th width="33%">Количество зарегистрированных пользователей</th>
        <th width="33%">Количество сообщений</th>
        <th width="33%">Количество активных сессий</th>
    </tr>
    <tr>
        <td width="33%"> <c:out value="${usersCount}"/> </td>
        <td width="33%"> <c:out value="${messagesCount}"/> </td>
        <td width="33%"> <c:out value="${sessionsCount}"/> </td>
    </tr>
</table>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/main'" value="На главную"></p>

</body>
</html>
