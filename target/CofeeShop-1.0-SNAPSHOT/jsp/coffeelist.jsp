<%@ page import="org.example.models.CoffeeType" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order coffee</title>
    <style><%@include file="/css/style.css"%></style>
    <script>
        function validateForm() {
            var coffeeTypes = document.getElementsByName("coffeeType");
            var selectedCount = 0;
            var quantityCount = 0;
            for (var i = 0; i < coffeeTypes.length; i++) {
                if (coffeeTypes[i].checked) {
                    selectedCount++;
                    var quantity = document.getElementsByName("quantity")[i];
                    if (quantity.value && quantity.value > 0) {
                        quantityCount++;
                    }
                }
            }
            if (selectedCount === 0) {
                alert("Пожалуйста, выберите хотя бы один кофе");
                return false;
            }
            if (quantityCount !== selectedCount) {
                alert("Пожалуйста, введите количество для каждого выбранного кофе");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red"><%= request.getAttribute("error") %>
</p>
<% } %>
<div class="page-container">
    <h1>Select coffee: </h1>
    <div class="container">
        <form action="" method="post" onsubmit="return validateForm()">
            <table>
                <tr>
                    <th>NAME</th>
                    <th>PRICE</th>
                    <th>QUANTITY</th>
                </tr>
                <% List<CoffeeType> coffeeTypeList = (List<CoffeeType>) request.getAttribute("coffeeTypes");
                    if (coffeeTypeList != null) {
                        for (CoffeeType coffeeType : coffeeTypeList) {
                %>
                <tr>
                    <td><input type="checkbox" name="coffeeType"
                               value="<%= coffeeType.getTypeName() %>"><%= coffeeType.getTypeName() %>
                    </td>
                    <td><input type="hidden" name="price"
                               value="<%= coffeeType.getPrice() %>"><span><%= coffeeType.getPrice() %></span></td>
                    <td><input type="number" name="quantity" min="1" max="100"></td>
                </tr>
                <% }
                } %>
            </table>
            <br>
            <input type="submit" value="ORDER">
        </form>
    </div>
</div>
</body>
</html>