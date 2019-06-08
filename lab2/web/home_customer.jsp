<%@ page import="model.extra.Invoice" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.extra.RegectedApplication" %>
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
<strong>CUSTOMER PAGE</strong><br>
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

<div>You have access to next commands</div>
<br><br>

<form action="BasePage" method="post">
    <input type="hidden" name="command" value="commentCommand"/>
    <table style="height: 68px; width: 500px; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;">
                <strong>COMMENT</strong>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Invoice ID:&nbsp; &nbsp;
                <input name="customerInputInvoiceId" type="number" min="1" max="100" size="50" value="1"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Comment:&nbsp; &nbsp;&nbsp;
                <input name="customerInputComment" type="text" size="50"/>
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

<form action="BasePage" method="post">
    <input type="hidden" name="command" value="createCommand"/>
    <table style="height: 68px; width: 500px; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;">
                <strong>CREATE</strong>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Body:&nbsp; &nbsp;&nbsp;
                <input name="customerInputBody" type="text" size="50"/>
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
        <strong>Finished invoices</strong><br><br>
        <%
            List<Invoice> invoices = (ArrayList<Invoice>) request.getAttribute("finished_invoices");

            if (invoices.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (Invoice invoice : invoices) {
                    out.println("Invoice " + invoice.id + ", idApp = " + invoice.idApplication + ", idMaster = " + invoice.idMaster + ", price = " + invoice.price + "<br>");
                }
            }
        %>
    </div>
    <div class="column">
        <strong>Rejected applications</strong><br><br>
        <%
            List<RegectedApplication> regectedApplications = (ArrayList<RegectedApplication>) request.getAttribute("rejected_applications");

            if (regectedApplications.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (RegectedApplication app : regectedApplications) {
                    out.println("RegectedApp " + app.id + ", appId = " + app.idApplication + ", idManager = " + app.idManager + ", message = " + app.message + "<br>");
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