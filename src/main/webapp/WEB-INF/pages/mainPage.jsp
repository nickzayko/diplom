<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <spring:message code="titleMainPage" var="mainPageTitle"></spring:message>
    <title>${mainPageTitle}</title>
</head>
<body>

<%@include file="commonFileForJSPInclude.jsp" %>

<spring:message code="headOfMainPage" var="mainPageHeader"/>
<h1 class="reg-headline">${mainPageHeader}</h1>
<hr>

<%--@elvariable id="topicEntity" type="entity"--%>
<springForm:form action="/menu/createTopic" method="post" modelAttribute="topicEntity" cssClass="input-form">
    <div class="form-row">
        <spring:message code="inputNewTopic" var="newTopicPlaceholder"/>
        <springForm:input path="topicName" type="text" placeholder="${newTopicPlaceholder}"/>
        <springForm:errors path="topicName" cssClass="reg-errors"/>
    </div>
    <div class="form-row">
        <spring:message code="buttonCreateTopic" var="createTopic"/>
        <input type="submit" value="${createTopic}">
    </div>
</springForm:form>

<springForm:form action="/menu/findChat" cssClass="input-form" method="post">
    <div class="form-row">
        <spring:message code="placeholderToFindChat" var="placeholderToFindChat"/>
        <input type="text" name="findChat" placeholder="${placeholderToFindChat}">
    </div>
    <div class="form-row">
        <spring:message code="buttonFindChat" var="buttonFindChat"/>
        <input type="submit" value="${buttonFindChat}">
    </div>
</springForm:form>

<springForm:form action="/menu/showMyChats" method="get" cssClass="input-form">
    <div class="form-row">
        <spring:message code="buttonShowMyChats" var="buttonShowMyChats"/>
        <input type="submit" value="${buttonShowMyChats}">
    </div>
</springForm:form>
<hr>
<springForm:form action="/menu/exit" method="get" cssClass="input-form">
    <div class="form-row">
        <spring:message code="buttonExitFromApplication" var="buttonExitFromApplication"/>
        <input type="submit" value="${buttonExitFromApplication}">
    </div>
</springForm:form>

${userEntity.idUser}
${userEntity.userName}
${userEntity.userSurname}
${userEntity.userEmail}
${userEntity.userLogin}
${userEntity.userPassword}
<hr>

</body>
</html>
