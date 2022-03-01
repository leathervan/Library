<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 22.02.2022
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="img/navbar_logo.jpg">
    <div class="container-fluid">
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/admin"><span class="mb-0 h2">Library</span> </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin">Books</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/adminWorkers">Workers</a>
                <a class="nav-link active" href="${pageContext.request.contextPath}/adminCustomers">Customers</a>
            </div>
        </div>
        <form class="d-flex">
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Logout</a>
            </div>
        </form>
    </div>
</nav>
<table class="table table-dark table-striped w-25 p-3">
    <tbody>
    <tr>
        <th>email</th>
        <td><c:out value="${user.getEmail()}"/></td>
    </tr>
    <tr>
        <th>Name</th>
        <td><c:out value="${user.getName()}"/></td>
    </tr>
    <tr>
        <th>Surname</th>
        <td><c:out value="${user.getSurname()}"/></td>
    </tr>
    </tbody>
</table>
<br>
<table class="table w-75 p-3">
    <thead class="table-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col">Email</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Blocked</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}"/></td>
            <td><c:out value="${user.getEmail()}"/></td>
            <td><c:out value="${user.getName()}"/></td>
            <td><c:out value="${user.getSurname()}"/></td>
            <td><c:out value="${user.getBlocked()}"/></td>
            <c:if test="${user.getBlocked() == true}">
                <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/blockuser?userID=${user.getId()}">Unblock</a></td>
            </c:if>
            <c:if test="${user.getBlocked() == false}">
                <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/blockuser?userID=${user.getId()}">Block</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
