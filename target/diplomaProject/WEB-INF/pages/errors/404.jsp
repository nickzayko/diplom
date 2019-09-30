<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <spring:message code="title404" var="title404"></spring:message>
    <title>${title404}</title>
</head>
<body>
<%@include file="commonFileForErrors.jsp" %>
<spring:message code="fail404" var="fail404"></spring:message>
<h3>${fail404}</h3>
</body>
</html>
