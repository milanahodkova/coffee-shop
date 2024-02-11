<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body {
      font-family: verdana, arial;
      font-size: 11px;
      color: black;
      margin: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      text-align: center;
    }

    table {
      font-family: verdana, arial;
      font-size: 12px;
      margin: 0 auto;
      border: 1px black solid;
    }

    th {
      padding: 5px;
    }

    td {
      padding: 2px 5px;
      height: 23px;
    }

    input.field {
      height: 18px;
      margin: 0;
      font-family: verdana, arial;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <table width="300px" cellspacing="0px" cellpadding="2px" border="0px">
    <tr style="background-color: white">
      <th>Order Confirmation</th>
    </tr>
    <tr style="background-color: #F0F0F0" align="center">
      <td>Your order has been successfully placed.</td>
    </tr>
  </table>
  <br/>
  <a href="${pageContext.request.contextPath}/">Return to the Shop</a>
</div>
</body>
</html>