<%@ page import="model.extra.Invoice" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.extra.UserResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        .row {
            display: flex;
        }

        .column {
            flex: 50%;
        }

        .topcorner{
            position:absolute;
            top:30px;
            right:30px;
        }
    </style>
</head>
<body>
<strong>MASTER PAGE</strong><br>
<strong>Welcome, dear

    <%
        String usrName = (String) request.getAttribute("usrName");
        if (usrName != null)
            out.println(" " + usrName + " ");
    %>
    <%
        String usrSurname = (String) request.getAttribute("usrSurname");
        if (usrSurname != null)
            out.println(usrSurname);
    %>
</strong>
<br>

<div>You have access to next commands</div><br><br>

<form action="BasePage" method="post">
    <input type="hidden" name="command" value="handleCommand"/>
    <table style="height: 68px; width: 500px; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;">
                <strong>HANDLE</strong>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Invoice ID:&nbsp; &nbsp;
                <input name="inputInvoiceId" type="number" min="1" max="100" size="50" value="1"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: center;">
                <input type="submit" class="button" value="Submit"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form action="BasePage" method="post" class="topcorner">
    <input type="hidden" name="command" value="logoutCommand"/>
    <input type="submit" class="button" value="Log out"/>
</form>


<div class="row">
    <div class="column">
        <strong>Unhandled invoices</strong><br><br>
        <%
            List<Invoice> unhandledInvoices = (ArrayList<Invoice>) request.getAttribute("undone_invoices");

            if (unhandledInvoices.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (Invoice invoice : unhandledInvoices) {
                    out.println("Invoice " + invoice.id + ", appId = " + invoice.idApplication + ", price = " + invoice.price + "<br>");
                }
            }
        %>
    </div>
    <div class="column">
        <strong>User responses</strong><br><br>
        <%
            List<UserResponse> userResponses = (ArrayList<UserResponse>) request.getAttribute("user_responses");

            if (userResponses.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (UserResponse userResponse : userResponses) {
                    out.println("UserResponse " + userResponse.id + ", invoiceId = " + userResponse.idInvoice + ", comment = " + userResponse.comment + "<br>");
                }
            }
        %>
    </div>
</div>


<br><br><br>
<p style="color:#FF0000";>
<%
    String message = (String) request.getAttribute("message");
    if (message != null)
        out.println(message);
%>
</p>
</body>
</html>