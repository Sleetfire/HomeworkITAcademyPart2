<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Chats</title>
</head>
<body>

<h3>Входящие сообщения</h3>
<table border="1px">
    <tr>
        <th width="20%">Логин отправителя</th>
        <th width="20%">Дата и время отправки</th>
        <th width="60%">Текст сообщения</th>
    </tr>
    <c:forEach var="receivedMessage" items="${receivedMessages}">
        <tr>
            <td width="20%"> ${receivedMessage.senderLogin} </td>
            <td width="20%"> ${receivedMessage.dateTime} </td>
            <td width="60%"> ${receivedMessage.text} </td>
        </tr>
    </c:forEach>
</table>

<h3>Исходящие сообщения</h3>
<table border="1px">
    <tr>
        <th width="20%">Логин получателя</th>
        <th width="20%">Дата и время отправки</th>
        <th width="60%">Текст сообщения</th>
    </tr>
    <c:forEach var="sentMessage" items="${sentMessages}">
        <tr>
            <td width="20%"> ${sentMessage.recipientLogin} </td>
            <td width="20%"> ${sentMessage.dateTime} </td>
            <td width="60%"> ${sentMessage.text} </td>
        </tr>
    </c:forEach>
</table>

<p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/main'" value="На главную"></p>

</body>
</html>
