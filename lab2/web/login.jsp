<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .button {
            background-color: #2E8E3B;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<hr/>
<hr/>
<hr/>
<p style="text-align: center;"><em><strong>Welcome to service "LHass Service Center"</strong></em></p>

<form action="BasePage" method="post">
    <input type="hidden" name="command" value="loginCommand"/>
    <table style="height: 68px; width: 500px; margin-left: auto; margin-right: auto; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;"><strong>SIGN IN</strong></td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Login:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
                <input name="signInLoginInput" type="text"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Password:&nbsp; &nbsp;&nbsp;
                <input name="signInPasswordInput" type="password"/>
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

<p>&nbsp;&nbsp;</p>

<form action="BasePage" method="post">
    <input type="hidden" name="command" value="registerCommand"/>
    <table style="height: 68px; width: 500px; margin-left: auto; margin-right: auto; border: 1px solid black;">
        <tbody>
        <tr>
            <td style="width: 280px; text-align: center; border: 1px solid black;"><strong>SIGN UP</strong></td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Login:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
                &nbsp;&nbsp;
                <input name="signUpLoginInput" type="text"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Password:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
                <input name="signUpPasswordInput" type="password"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Repate password:&nbsp; &nbsp;
                <input name="signUpRepeatPasswordInput" type="password"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Name:&nbsp; &nbsp;
                <input name="signUpName" type="text"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Surname:&nbsp; &nbsp;
                <input name="signUpSurname" type="text"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Age:&nbsp; &nbsp;
                <input name="signUpAge" type="number" min="18" max="100" value="18"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Email:&nbsp; &nbsp;
                <input name="signUpEmail" type="text"/>
            </td>
        </tr>
        <tr>
            <td style="width: 280px; text-align: left;">Phone:&nbsp; &nbsp;
                <input name="signUpPhone" type="number" min="1000" max="100000000000" value="1000"/>
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

<p>&nbsp;</p>
<hr/>
<hr/>
<hr/>

<p style="color:#FF0000";>
<%
    String message = (String) request.getAttribute("message");
    if (message != null)
        out.println(message);
%>
</p>

<script>
    (function(){
        var path= document.location.pathname.replace(/^.*\/([^\/]+)$/, '$1');
        window.onpopstate = function() {
            history.pushState({}, "", path);
        };
        history.pushState({}, "", path);
    })();
</script>

</body>
</html>