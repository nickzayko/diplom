<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <spring:message code="chatPageTitle" var="chatPageTitle"/>
    <title>${chatPageTitle}</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script>

        $(document).ready(function () {
            setInterval(updateMessage, 1000);
        });

        function updateMessage() {
            $.ajax({
                url: "/menu/updateMessages",
                dataType: "json",
                success: function (json) {
                    $("#somediv").html('');
                    var $table = $("<table>").appendTo($("#somediv"));
                    $.each(json, function (index, messageDTO) {
                        $("<tr>").appendTo($table)
                            .append($("<td width='235px' align='center'>").text(messageDTO.userName))
                            .append($("<td align='left' >").text(messageDTO.textOfMessage))
                    })
                }
            })
        }
    </script>

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

<table class="tableMessagesHeader">
    <tr>
        <spring:message code="headOfTableAuthorOfMessage" var="authotOfMessage"></spring:message>
        <td align="center">${authotOfMessage}</td>
        <spring:message code="headOfTableTextOfMessage" var="textOfMessage"></spring:message>
        <td align="left">${textOfMessage}</td>
    </tr>
</table>

<div id="somediv" class="somediv">
    <table>
        <c:forEach var="message" items="${messagesList}">
            <tr>
                <td width='235px' align='center'>
                        ${message.getUserEntity().getUserName()}
                </td>
                <td align='left'>
                        ${message.getTextOfMessage()}
                </td>
            </tr>
        </c:forEach>
    </table>
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
