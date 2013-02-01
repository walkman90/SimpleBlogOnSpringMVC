<%-- 
    Document   : registrationSuccesess
    Created on : 19 жовт 2012, 15:53:56
    Author     : Walkman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "sf" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Upload</title>
         <style type="text/css">
            <%@include file="../../CSS/reg.css" %>
        </style>
    </head>
    <body>
        <h3>You are successfully registered!</h3></ br>
        <div id="form1">
        <img src="<%= request.getContextPath()%>/img/default.jpg" /></br>
        You may upload your profile image:
        <sf:form modelAttribute="imageUpload" method="POST" action="imgUpload.html" enctype="multipart/form-data">
        <th><sf:label path="fileData" >Image:</sf:label></th>
            <sf:input type="file" path="fileData"/></br>
            <sf:errors path ="fileData" cssClass="error"/></br>
            <input type="submit" value="Submit" />
            <a href ="imgUpload.html"><input type="button" value="Skip >" /></a>
        </sf:form>
        </div>
    </body>
</html>
