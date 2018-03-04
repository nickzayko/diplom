<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <spring:message code="chatPageTitle" var="chatPageTitle"/>
    <title>${chatPageTitle}</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

    <%--для обновления отправленных другими пользователями сообщений--%>
    <script>
        $(document).ready(function () {
            setInterval(updateMessage, 1000);
        });

        function updateMessage() {
            $.ajax({
                url: "/menu/findNewMessages",
                data: "time=${lastMessageTime}",
                dataType: "json",
                success: function (json) {
                    $("#newMessages").html('');
                    var $table1 = $("<table>").appendTo($("#newMessages"));
                    $.each(json, function (index, messageDTO) {
                        $("<tr class='trInTableMessages'>").appendTo($table1)
                            .append($("<td align='center' class='tdFirstInTableMessages'>").text(messageDTO.userName))
                            .append($("<td class='tdSecondInTableMessages' >").text(messageDTO.textOfMessage))
                    })
                }
            })
        }
    </script>

    <%--скрипт для показа предыдущих сообщений--%>
    <script>
        $(document).ready(function () {
            $("#buttonShowPreviousMessages").click(function () {
                $.ajax({
                    url: "/menu/loadPreviousMessages",
                    dataType: "json",
                    success: function (json) {
                        var $table = $("<table>").prependTo($("#somediv"));
                        $.each(json, function (index, messageDTO) {
                            $("<tr class='trInTableMessages'>").prependTo($table)
                                .append($("<td align='center' class='tdFirstInTableMessages'>").text(messageDTO.userName))
                                .append($("<td class='tdSecondInTableMessages'>").text(messageDTO.textOfMessage))
                        })
                    }
                })
            })
        })
    </script>
    <%--..................................................--%>

</head>
<body>

<%@include file="commonFileForJSPInclude.jsp" %>

<spring:message code="headOfChatPage" var="headOfChatPage"/>
<h1 class="reg-headline">${headOfChatPage}</h1>
<hr>

<spring:message code="labelChatTopic" var="labelChatTopic"></spring:message>
<spring:message code="labelChatCreator" var="labelChatCreator"></spring:message>
<table>
    <tr>
        <td><label>${labelChatTopic}</label></td>
        <td>${topicName}</td>
    </tr>
    <tr>
        <td><label>${labelChatCreator}</label></td>
        <td>${topicCreator}</td>
    </tr>
</table>
<hr>

<div class="tableAndButtonToViewPreviousMessages">

    <div class="leftBlock">
        <table class="tableMessagesHeader">
            <tr>
                <spring:message code="headOfTableAuthorOfMessage" var="authotOfMessage"></spring:message>
                <td align="center">${authotOfMessage}</td>
                <spring:message code="headOfTableTextOfMessage" var="textOfMessage"></spring:message>
                <td align="left">${textOfMessage}</td>
            </tr>
        </table>

        <div class="tableScriptAndMessages">

            <%--предыдущие сообщения--%>
            <div id="somediv" class="tableMessages"></div>

            <%--отправленные сообщения--%>
            <div class="tableMessages">
                <table>
                    <c:forEach var="message" items="${messagesList}">
                        <tr class="trInTableMessages">
                            <td align="center" class="tdFirstInTableMessages">
                                    ${message.getUserEntity().getUserName()}
                            </td>
                            <td class="tdSecondInTableMessages">
                                    ${message.getTextOfMessage()}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="newMessages" class="tableMessages"></div>
        </div>
    </div>

    <div class="rightBlock">
        <table>
            <tr>
                <td>

                    <%--подгрузка предыдущих сообщений--%>
                    <spring:message code="buttonShowPreviousMessages" var="previousMessages"/>
                    <button id="buttonShowPreviousMessages">${previousMessages}</button>

                </td>
            </tr>
        </table>
    </div>
</div>

<div class="footer">
    <springForm:form action="/menu/messageHandler" method="post" commandName="messageEntity">
        <div class="textArea">
            <textarea name="textOfMessage" cols="50" rows="5"></textarea>
            <springForm:errors cssClass="reg-errors" path="textOfMessage"/>
        </div>
        <div class="form-row1">
            <spring:message code="buttonSendMessage" var="sendMessage"/>
            <input type="submit" value="${sendMessage}">
        </div>
        <div class="form-row1">
            <spring:message code="buttonClearMessage" var="clearMessage"/>
            <input type="reset" value="${clearMessage}">
        </div>
    </springForm:form>

    <div class="form-row2">
        <a href="/">
            <spring:message code="buttonReturnToMenu" var="returnToMenu"/>
            <button>${returnToMenu}</button>
        </a>
    </div>
</div>

</body>
</html>
