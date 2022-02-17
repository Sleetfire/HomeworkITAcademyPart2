<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Send message</title>
</head>
<body>

<h3>Написать сообщение</h3>

<c:if test="${wrongRecipientLogin == true}">
    <p style="color: red">Пользователя с таким логином нет!</p>
</c:if>

<form action="/MK_JD2-88-2-0.0.0/message" method="post">
    <p><b>Получатель </b><input type="text" placeholder="Логин получателя" name="recipientLogin"></p>
    <p><b>Текст </b><input type="text" placeholder="Текст сообщения" name="text"></p>
    <p><input type="submit" value="Отправить"></p>
</form>

<p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/main'" value="На главную"></p>

</body>
</html>
