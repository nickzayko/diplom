<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <spring:message code="titleRegistration" var="registrationTitle"></spring:message>
    <title>${registrationTitle}</title>
</head>
<body>

<%@include file="commonFileForJSPInclude.jsp" %>

<spring:message code="welcomeRegistrationPage" var="registrationHeader"/>
<h1 class="reg-headline">${registrationHeader}</h1>
<hr>

<%--@elvariable id="userEntity" type="entity"--%>
<springForm:form action="/registration" class="input-form" commandName="userEntity" method="post">
    <div class="form-row">
        <spring:message code="name" var="registrationName"/>
        <springForm:input type="text" placeholder="${registrationName}" path="userName" id="field"/>
        <springForm:errors path="userName" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="surname" var="registrationSurname"/>
        <springForm:input type="text" path="userSurname" placeholder="${registrationSurname}"/>
        <springForm:errors path="userSurname" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="email" var="registrationEmail"/>
        <springForm:input type="email" path="userEmail" placeholder="${registrationEmail}"/>
        <springForm:errors path="userEmail" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="login" var="registrationLogin"/>
        <springForm:input type="text" path="userLogin" placeholder="${registrationLogin}"/>
        <springForm:errors path="userLogin" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="password" var="registrationPassword"/>
        <springForm:input type="password" path="userPassword" placeholder="${registrationPassword}"/>
        <springForm:errors path="userPassword" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="buttonSave" var="registrationButtonSave"/>
        <input type="submit" value="${registrationButtonSave}">
    </div>
</springForm:form>

<hr>
<a href="/"><spring:message code="forReturnToAuthorizationPage"/></a>
<hr>

</body>
</html>
