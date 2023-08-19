<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
</head>
<body>
<h1>Admin Dashboard</h1>

<h2>Current Settings</h2>
<p>Mileage Rate: $<%= request.getAttribute("currentMileageRate") %></p>
<p>Daily Allowance: $<%= request.getAttribute("currentDailyAllowance") %></p>

<h2>Update Settings</h2>
<form action="${pageContext.request.contextPath}/dashboard" method="post">
  <label for="newMileageRate">New Mileage Rate:</label>
  <input type="number" id="newMileageRate" name="newMileageRate" step="0.01" required><br>

  <label for="newDailyAllowance">New Daily Allowance:</label>
  <input type="number" id="newDailyAllowance" name="newDailyAllowance" step="0.01" required><br>

  <button type="submit">Update</button>
</form>
<h2>Edit Receipts List</h2>
<h3>Add New Receipt</h3>
<form action="${pageContext.request.contextPath}/receipts" method="post">
  <input type="hidden" name="action" value="add">
  <label for="newReceipt">New Receipt:</label>
  <input type="text" id="newReceipt" name="newReceipt" required>
  <button type="submit">Add Receipt</button>
</form>

<h3>Remove Receipt</h3>
<form action="${pageContext.request.contextPath}/receipts" method="post">
  <input type="hidden" name="action" value="remove">
  <label for="receiptToRemove">Select Receipt:</label>
  <select id="receiptToRemove" name="receiptToRemove">
    <c:forEach var="receipt" items="${receiptsList}">
      <option value="${receipt}">${receipt}</option>
    </c:forEach>
  </select>
  <button type="submit">Remove Receipt</button>
</form>
</body>
</html>

