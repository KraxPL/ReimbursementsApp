<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ReimbursementsApp</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>
<body>
<div class="app-header">
    <h1>ReimbursementsApp</h1>
</div>
<div class="app-content">
    <a class="btn-create" id="createBtn">Create New Reimbursement</a>
    <a class="btn-admin" href="<c:url value='/login' />">Admin Login</a>
</div>
<div id="form-container"></div>
</body>
<script src="/static/js/createReimbursementForm.js"></script>
</html>
