<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 16.02.2022
  Time: 03:30
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
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/worker"><span class="mb-0 h2">Library</span> </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/worker">Receipts</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/subs">Subscriptions</a>
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
<form action="${pageContext.request.contextPath}/worker">
    <div class="d-flex">
        <a href="${pageContext.request.contextPath}/worker?status=EXPECTED" class="btn btn-secondary btn-sm">EXPECTED</a>
        <a href="${pageContext.request.contextPath}/worker?status=SUBSCRIPTION" class="btn btn-secondary btn-sm">SUBSCRIPTION</a>
        <a href="${pageContext.request.contextPath}/worker?status=COMPLETED" class="btn btn-secondary btn-sm">COMPLETED</a>
        <a href="${pageContext.request.contextPath}/worker?status=DENIED" class="btn btn-secondary btn-sm">DENIED</a>
        <a href="${pageContext.request.contextPath}/worker?status=ALL" class="btn btn-secondary btn-sm">ALL</a>
    </div>
</form>
<nav aria-label="...">
    <ul class="pagination pagination-sm">
        <c:forEach begin="1" end="${countPage}" var="i">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/worker?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
<table class="table w-75 p-3">
    <thead class="table-dark">
        <tr>
            <th scope="col">Receipt id</th>
            <th scope="col">User id</th>
            <th scope="col">User name</th>
            <th scope="col">User surname</th>
            <th scope="col">Book id</th>
            <th scope="col">Book name</th>
            <th scope="col">Book surname</th>
            <th scope="col">Status</th>
            <th scope="col">Approve order</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${receipts}" var="receipt">
        <tr>
            <td><c:out value="${receipt.getId()}"/></td>
            <td><c:out value="${receipt.getUser().getId()}"/></td>
            <td><c:out value="${receipt.getUser().getName()}"/></td>
            <td><c:out value="${receipt.getUser().getSurname()}"/></td>
            <td><c:out value="${receipt.getBook().getId()}"/></td>
            <td><c:out value="${receipt.getBook().getName()}"/></td>
            <td><c:out value="${receipt.getBook().getAuthor()}"/></td>
            <td><c:out value="${receipt.getStatus()}"/></td>
            <c:if test="${receipt.getStatus() == 'EXPECTED'}">
                <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/approve?receiptID=${receipt.getId()}">Approve</a></td>
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
</table>
</body>
</html>
