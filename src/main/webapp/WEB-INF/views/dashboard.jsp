<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</body>
</html>

