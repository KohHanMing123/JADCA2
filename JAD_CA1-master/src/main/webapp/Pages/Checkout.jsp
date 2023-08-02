<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat" %>
    
<%
// Get the totalPrice from the request parameter
String subTotalPriceString = request.getParameter("totalPrice");
double subTotal = 0.0;
try {
    subTotal = Double.parseDouble(subTotalPriceString);
} catch (NumberFormatException e) {
    // Handle the case where totalPrice is not a valid double
    // You can set a default value or redirect to an error page
}

double shipping = 5.0;
double taxRate = 0.08;

double tax = subTotal * taxRate;

double total = subTotal + shipping + tax;

DecimalFormat decimalFormat = new DecimalFormat("#.##");
String formattedSubTotalPrice = decimalFormat.format(subTotal);
String formattedShippingPrice = decimalFormat.format(shipping);
String formattedTax = decimalFormat.format(tax);
String formattedTotalPrice = decimalFormat.format(total);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check out</title>
</head>
<body>
	<div align="center">
    <h1>Check Out</h1>
    <br/>
    <form action="<%= request.getContextPath() %>/AuthorizePayment" method="post">
    <table>
        <tr>
            <td>Product/Service:</td>
            <td><input type="text" name="product" value="Next iPhone" /></td>
        </tr>
        <tr>
            <td>Sub Total:</td>
            <td><input type="text" name="subtotal" value="<%= formattedSubTotalPrice %>" /></td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td><input type="text" name="shipping" value="<%= formattedShippingPrice %>" /></td>
        </tr>    
        <tr>
            <td>Tax:</td>
            <td><input type="text" name="tax" value="<%= formattedTax %>" /></td>
        </tr>    
        <tr>
            <td>Total Amount:</td>
            <td><input type="text" name="total" value="<%= formattedTotalPrice %>" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Checkout" />
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>