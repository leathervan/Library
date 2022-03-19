<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 21.02.2022
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="img/navbar_logo.jpg">
    <div class="container-fluid">
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/admin"><span class="mb-0 h2"><fmt:message key="index.library"/></span> </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin"><fmt:message key="admin.books"/></a>
                <a class="nav-link active" href="${pageContext.request.contextPath}/adminWorkers"><fmt:message key="admin.workers"/></a>
                <a class="nav-link" href="${pageContext.request.contextPath}/adminCustomers"><fmt:message key="admin.customers"/></a>
            </div>
        </div>
        <form class="d-flex">
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger"><fmt:message key="index.logout"/></a>
            </div>
        </form>
    </div>
</nav>
<div class="position-absolute bottom-0 end-0">
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/adminWorkers?sessionLocale=en">en</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/adminWorkers?sessionLocale=ru">ru</a></li>
        </ul>
    </nav>
</div>
<table class="table table-dark table-striped w-25 p-3">
    <tbody>
    <tr>
        <th><fmt:message key="index.email"/></th>
        <td><c:out value="${user.getEmail()}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="index.name"/></th>
        <td><c:out value="${user.getName()}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="index.surname"/></th>
        <td><c:out value="${user.getSurname()}"/></td>
    </tr>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/addworker">
    <div class="input-group w-25">
        <button class="btn btn-secondary" type="submit"><fmt:message key="admin.addworker"/></button>
    </div>
</form>
<table class="table w-75 p-3">
    <thead class="table-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col"><fmt:message key="index.email"/></th>
        <th scope="col"><fmt:message key="index.name"/></th>
        <th scope="col"><fmt:message key="index.surname"/></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${workers}" var="worker">
        <tr>
            <td><c:out value="${worker.getId()}"/></td>
            <td><c:out value="${worker.getEmail()}"/></td>
            <td><c:out value="${worker.getName()}"/></td>
            <td><c:out value="${worker.getSurname()}"/></td>
            <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/deleteworker?userID=${worker.getId()}"><fmt:message key="admin.deleteworker"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
