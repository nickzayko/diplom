<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <spring:message code="titleAuthorization" var="authorizationTitle"></spring:message>
    <title>${authorizationTitle}</title>
    <link href="/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<hr>
<span style="float: left">
    <a href="?lang=en"><font color="blue">en</font></a>
    <a href="?lang=ru"><font color="blue">ru</font></a>
    </span>

<spring:message code="welcomeAuthorizationPage" var="authorizationHeader"/>
<h1 class="reg-headline">${authorizationHeader}</h1>
<hr>
<springForm:form action="/authorization" method="post" class="input-form" commandName="userEntity">
    <div class="form-row">
        <spring:message code="loginPlaceholderAuthorization" var="loginPlaceHolderAuthor"/>
        <springForm:input type="text" path="userLogin" placeholder="${loginPlaceHolderAuthor}"/>
        <springForm:errors path="userLogin" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="passwordPlaceholderAuthorization" var="passwordPlaceholderAuthor"/>
        <springForm:input type="password" path="userPassword" placeholder="${passwordPlaceholderAuthor}"/>
        <springForm:errors path="userPassword" cssClass="reg-errors"/>
    </div>

    <div class="form-row">
        <spring:message code="buttonLogInAuthorization" var="buttonLogInAuthor"/>
        <input type="submit" value="${buttonLogInAuthor}">
    </div>
</springForm:form>

<form action="/registration" class="input-form" method="get">
    <div class="form-row">
        <spring:message code="buttonRegistrationAuthorization" var="buttonRegistrationAuthor"/>
        <input type="submit" value="${buttonRegistrationAuthor}">
    </div>
</form>
<hr>
<div class="reg-errors">
<spring:message code="${informationAuthorization}"/>
</div>




</body>
</html>
