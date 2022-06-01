<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Credit Card</title>
</head>
<body>
<form action="/get" method="POST">
  <div>
   <label>Credit Card Number</label>
    <input name="creditCardNumber" id="creditCardNumber" >
  </div>
  <div>
    <label >Expiry Date (dd/mm/yyyy)</label>
    <input name="expiryDate" id="expiryDate">
  </div>
  <div>
    <button>Submit</button>
  </div>
</form>
</body>
</html>