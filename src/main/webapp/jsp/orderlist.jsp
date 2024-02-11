<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.example.models.CoffeeOrderItem" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>YOUR ORDER</title>
    <style>
        <%@include file="/css/style.css" %>
    </style>
</head>
<body>
<div class="page-container">
    <h1>YOUR ORDER:</h1>
    <div class="container">
        <form action="<%=request.getContextPath()%>/orderlist" method="post">
            <label for="fullName">FULL NAME:</label>
            <input type="text" id="fullName" name="fullName" required><br><br>
            <label for="address">ADDRESS:</label>
            <input type="text" id="address" name="address" required><br><br>

            <table>
                <tr>
                    <th>NAME</th>
                    <th>PRICE</th>
                    <th>QUANTITY</th>
                    <th>TOTAL</th>
                </tr>
                <%
                    List<String> coffeeTypes = (List<String>) request.getSession().getAttribute("coffeeTypes");
                    List<Double> prices = (List<Double>) request.getSession().getAttribute("prices");
                    List<Integer> quantities = (List<Integer>) request.getSession().getAttribute("quantities");
                    List<Double> sumOfEach = (List<Double>) request.getSession().getAttribute("sum");
                    List<Integer> freeCupsList = (List<Integer>) request.getSession().getAttribute("freeCups");

                    double orderTotal = (double) request.getSession().getAttribute("totalPrice");
                    double delivery = (double) request.getSession().getAttribute("delivery");
                    double orderTotalWithDelivery = (double) request.getSession().getAttribute("finalPrice");

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    for (int i = 0; i < coffeeTypes.size(); i++) {
                        String coffeeType = coffeeTypes.get(i);
                        double price = prices.get(i);
                        int quantity = quantities.get(i);
                        double sum = sumOfEach.get(i);
                        int freeCups = freeCupsList.get(i);
                %>

                <tr>
                    <td><%= coffeeType %>
                    </td>
                    <td><%= decimalFormat.format(price) %>
                    </td>
                    <td><%= quantity %>
                    </td>
                    <td><%= decimalFormat.format(sum) %>
                    </td>
                </tr>
                <%
                    if (freeCups > 0) {
                %>
                <tr>
                    <td colspan="3"></td>
                    <td>-<%= decimalFormat.format(freeCups * price)%>
                    </td>
                </tr>
                <%
                    }
                %>
                <%
                    }
                %>
                <tr>
                    <td colspan="3">Order Total:</td>
                    <td><%= decimalFormat.format(orderTotal) %>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">Delivery:</td>
                    <td><%= decimalFormat.format(delivery) %>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">Order Total with Delivery:</td>
                    <td><%= decimalFormat.format(orderTotalWithDelivery) %>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="ORDER">
        </form>
    </div>
</div>
</body>
</html>