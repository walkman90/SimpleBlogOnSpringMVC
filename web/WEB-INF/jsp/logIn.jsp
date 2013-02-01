<%-- 
    Document   : logIn
    Created on : 19 жовт 2012, 16:22:07
    Author     : Walkman
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Bloggy</title>
        <style type="text/css">
            <%@include file="../../CSS/login.css" %>
        </style>
    </head>
    
<body>
<h1>Welcome!</h1>       
<div id="form">
    <table>
    <sf:form method="POST" action="userHome.html" modelAttribute="logIn">
    <tr>
    <th><sf:label path="email">EmailAddress:</sf:label></th>
    <td><sf:input path="email" size="30"/><br/>
    <sf:errors path="email" cssClass="error"/>
    </td>
    </tr>
    <tr>
    <th><sf:label path="password">Password:</sf:label></th>
    <td><sf:password path="password" size="30" showPassword="true"/><br/>
    <sf:errors path="password" cssClass="error"/>
    </td>
    </tr>
    <tr>
    <th></th>
    <td><input name="commit" type="submit" value = "Enter" />
    </td>
    </tr>
    <tr><td></td><td>You are not registered? <a href="registration.html">
        <input type="button" value="Sign Up!"><a/><tr/><td/>
    </sf:form>
    </table>
</div>
</body>  
</html>