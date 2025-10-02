<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirection</title>
    <meta http-equiv="refresh" content="0; URL=${pageContext.request.contextPath}/login">
</head>
<body>
    <p>Redirection vers la page de <a href="${pageContext.request.contextPath}/login">connexion</a>.</p>
</body>
</html>
