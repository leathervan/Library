<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 15.02.2022
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<br>
<br>
<form>
    <div class="container">
        <div class="row">
            <div class="col-md-12 d-flex justify-content-center align-items-center" >
                <div class="error-template">
                    <h1> <fmt:message key="user.info.rejected"/></h1>
                    <br>
                    <div class="error-actions">
                        <a href="${pageContext.request.contextPath}/userProfile" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span> <fmt:message key="index.back"/> </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
