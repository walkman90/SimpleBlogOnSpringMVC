<%-- 
    Document   : form
    Created on : 19 ???? 2012, 10:04:56
    Author     : Walkman
--%>

<%@ taglib prefix = "sf" uri = "http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <style type="text/css">
            <%@include file="../../CSS/reg.css" %>
        </style>
    </head>
<body>
<h2>Createa free account</h2>
<div id="form">
    <sf:form method="POST" action="addBlogger.html" modelAttribute="blogger" >
    <table >
    <tr>
    <th><sf:label path="login">Login:</sf:label></th>
    <td><sf:input path="login" size="15" maxlength="15"/>
    <small id = "username_msg"> Nospaces, please.</small><br/>
    <sf:errors path ="login" cssClass="error"/>
    </td>
    </tr>
    <tr>
    <th><sf:label path="password">Password:</sf:label></th>
    <td><sf:password path="password" size="30" showPassword="true"/>
    <small>6 characters or more(be tricky!)</small><br/>
    <sf:errors path="password" cssClass="error"/>
    </td>
    </tr>
    <tr>
    <th><sf:label path="email">EmailAddress:</sf:label></th>
    <td><sf:input path="email" size="30"/>
    <small>In case you forget something</small><br/>
    <sf:errors path="email" cssClass="error"/>
    </td>
    </tr>
    <tr>
    <th></th>
    <td><input name="commit" type="submit" value = "Create my account" />
    </td>
    </tr>
    </table>
    </fieldset>
    </sf:form>
</div>
</body>
</html>