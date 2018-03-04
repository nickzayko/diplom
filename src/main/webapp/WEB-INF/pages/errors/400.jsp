<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:message code="title400" var="title400"></spring:message>
    <title>${title400}</title>
</head>
<body>
<%@include file="commonFileForErrors.jsp" %>
<spring:message code="fail400" var="fail400"></spring:message>
<h3>${fail400}</h3>

</body>
</html>
