<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <spring:message code="titleResultOfFindChats" var="resultOfFindChatsTitle"></spring:message>
    <title>${resultOfFindChatsTitle}</title>
</head>
<body>

<%@include file="commonFileForJSPInclude.jsp" %>

<spring:message code="headResultOfFindChatsPage" var="resultOfFindChatsPageHeader"/>
<h1 class="reg-headline">${resultOfFindChatsPageHeader}</h1>
<hr>

<table>
    <tr>
        <spring:message code="chatsTopic" var="chatsTopicLabel"/>
        <td><b><font size="5"><label>${chatsTopicLabel}</label></font></b></td>
    </tr>
    <c:forEach var="chat" items="${listOfExistChats}">
        <tr>
            <springForm:form action="/menu/goToChatPage" method="post">
                <td>
                    <div class="form-row"><input type="text" value="${chat}" name="topic"></div>
                </td>
                <spring:message code="buttonGoToChatPage" var="buttonGoToChatPage"/>
                <td>
                    <div class="form-row"><input type="submit" value="${buttonGoToChatPage}"></div>
                </td>
            </springForm:form>
        </tr>
    </c:forEach>
</table>
<hr>
<a href="/"><spring:message code="forReturnToMenuFromFindChats"/></a>
<hr>

</body>
</html>
