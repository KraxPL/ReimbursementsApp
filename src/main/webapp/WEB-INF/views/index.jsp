<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ReimbursementsApp</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
</head>
<body>
<div class="app-header">
    <h1>ReimbursementsApp</h1>
</div>
<div class="app-content">
    <a class="btn-create" href="<c:url value='/creatReimbursement' />">Create New Reimbursement</a>
    <a class="btn-admin" href="<c:url value='/login' />">Admin Login</a>
</div>
</body>
</html>
