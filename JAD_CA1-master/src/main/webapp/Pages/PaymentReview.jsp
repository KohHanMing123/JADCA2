<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Review</title>
</head>
<body>
	<div align="center">
    <h1>Please Review Before Paying</h1>
    <form action="<%= request.getContextPath() %>/ExecutePayment" method="post">
    <table>	
    <% System.out.println("Payment ID: " + request.getParameter("paymentId")); %>
    <% System.out.println("Payer ID: " + request.getParameter("PayerID")); %>
        <tr>
            <td colspan="2"><b>Transaction Details:</b></td>
            <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>${transaction.description}</td>
            
        </tr>
        <tr>
            <td>Subtotal:</td>
            <td>${transaction.amount.details.subtotal} SGD</td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td>${transaction.amount.details.shipping} SGD</td>
        </tr>
        <tr>
            <td>Tax:</td>
            <td>${transaction.amount.details.tax} SGD</td>
        </tr>
        <tr>
            <td>Total:</td>
            <td>
            	<input type="hidden" name="total" value="${transaction.amount.total}"> ${transaction.amount.total} SGD
            </td>
            
        </tr>
         
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Payer Information:</b></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>${payer.firstName}</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>${payer.lastName}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${payer.email}</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Shipping Address:</b></td>
        </tr>
        <tr>
            <td>Recipient Name:</td>
            <td>${shippingAddress.recipientName}</td>
        </tr>
        <tr>
            <td>Line 1:</td>
            <td>${shippingAddress.line1}</td>
        </tr>
        <tr>
            <td>City:</td>
            <td>${shippingAddress.city}</td>
        </tr>
        <tr>
            <td>State:</td>
            <td>${shippingAddress.state}</td>
        </tr>
        <tr>
            <td>Country Code:</td>
            <td>${shippingAddress.countryCode}</td>
        </tr>
        <tr>
            <td>Postal Code:</td>
            <td>${shippingAddress.postalCode}</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Pay Now" />
            </td>
        </tr>    
    </table>
    </form>
</div>
</body>
</html>