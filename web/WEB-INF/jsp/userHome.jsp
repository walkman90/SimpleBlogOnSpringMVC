<%-- 
    Document   : userHome
    Created on : 19 жовт 2012, 16:08:09
    Author     : Walkman
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bloggy::${userName}</title>
    <style type="text/css">
        <%@include file="../../CSS/userHome.css" %>
    </style>
</head>

<body>
    <div id="container">
        <div id="home">Home</div>
        <div id="username">${blogger.login}
            <a href="signOut.html">
                    <input type="button" value="Sign out"></a>
        </div></br>
        <img src="<%= request.getContextPath()%>${blogger.imgURL}" alt="picture" />
        <form action="addTweet.html" method="POST">
            <textarea name="tweet" cols="50" rows="5"></textarea></br>
        <div id="post_bt"><input name="commit" type="submit" value = "Add post" /></div>
        </form><br/>
        <div id="tweets" >        
            <ol>
            <c:forEach var = "tweet" items="${tweets}">
                <c:out value="${tweet.text}"/></br><a href="removeTweet.html?id=${tweet.id}">
                    <input type="button" value="Remove"></a><br /><hr/><br/>
            </c:forEach>
            </ol>
        </div>
    </div>
</body>
</html>
