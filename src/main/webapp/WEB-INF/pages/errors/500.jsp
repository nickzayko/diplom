<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <spring:message code="title500" var="title500"></spring:message>
    <title>${title500}</title>
</head>
<body>
<%@include file="commonFileForErrors.jsp" %>
<spring:message code="fail500" var="fail500"></spring:message>
<h3>${fail500}</h3>
</body>
</html>
