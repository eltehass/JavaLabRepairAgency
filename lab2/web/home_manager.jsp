<%@ page import="model.extra.Application" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.roles.BasePerson" %>
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
<strong>MANAGER PAGE</strong><br>
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
    <input type="hidden" name="command" value="acceptCommand"/>
    <table style="height: 68px; width: 500px; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;">
                <strong>ACCEPT</strong>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Application ID:&nbsp; &nbsp;
                <input name="managerInputAppId" type="number" min="1" max="100" size="50" value="1"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Master Id:&nbsp; &nbsp;&nbsp;
                <input name="managerInputMasterId" type="number" min="1" max="100" size="50" value="1"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Price:&nbsp; &nbsp;&nbsp;
                <input name="managerInputPrice" type="number" min="200" max="2000" size="50" value="200"/>
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
    <input type="hidden" name="command" value="declineCommand"/>
    <table style="height: 68px; width: 500px; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;">
                <strong>DECLINE</strong>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Application ID:&nbsp; &nbsp;
                <input name="managerInputAppId" type="number" min="1" max="100" size="50" value="1"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Message:&nbsp; &nbsp;&nbsp;
                <input name="managerInputMessage" type="text" size="50"/>
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
        <strong>Unhandled applications</strong><br><br>
        <%
            List<Application> applications = (ArrayList<Application>) request.getAttribute("unhandled_applications");

            if (applications.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (Application app : applications) {
                    out.println("Application " + app.id + ", body = " + app.body + ", idCustomer = " + app.idPersonInfo + "<br>");
                }
            }
        %>
    </div>
    <div class="column">
        <strong>Masters</strong><br><br>
        <%
            List<BasePerson> masters = (ArrayList<BasePerson>) request.getAttribute("masters");

            if (masters.isEmpty()) {
                out.println("Empty" + "<br>");
            } else {
                for (BasePerson master : masters) {
                    out.println("Master " + master.id + ", name = " + master.name + ", surname = " + master.surname + ", age = " + master.age + "<br>");
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