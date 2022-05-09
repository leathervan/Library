<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 12.02.2022
  Time: 23:22
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
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/user"><span class="mb-0 h2"><fmt:message key="index.library"/></span> </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/user"><fmt:message key="index.search"/></a>
                <a class="nav-link" href="${pageContext.request.contextPath}/userProfile"><fmt:message key="user.subs"/></a>
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
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user?sessionLocale=en">en</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user?sessionLocale=ru">ru</a></li>
        </ul>
    </nav>
</div>
<form action="${pageContext.request.contextPath}/user">
    <div class="input-group w-25">
        <input type="text" id="search" name="search" class="form-control" placeholder="Input name or author's name" aria-describedby="basic-addon2">
        <button class="btn btn-secondary" type="submit"><fmt:message key="index.search"/></button>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false"><fmt:message key="worker.sort"/></button>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=name"><fmt:message key="user.sort.name"/></a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=author"><fmt:message key="user.sort.author"/></a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=edition"><fmt:message key="user.sort.edition"/></a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=year"><fmt:message key="user.sort.year"/></a>
            </ul>
        </div>
    </div>
</form>
<nav aria-label="...">
    <ul class="pagination pagination-sm">
        <c:forEach begin="1" end="${countPage}" var="i">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
<table class="table w-75 p-3">
    <thead class="table-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col"><fmt:message key="book.name"/></th>
        <th scope="col"><fmt:message key="book.author"/></th>
        <th scope="col"><fmt:message key="book.edition"/></th>
        <th scope="col"><fmt:message key="book.year"/></th>
        <th scope="col"><fmt:message key="book.amount"/></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.getId()}"/></td>
            <td><c:out value="${book.getName()}"/></td>
            <td><c:out value="${book.getAuthor()}"/></td>
            <td><c:out value="${book.getEdition()}"/></td>
            <td><c:out value="${book.getYearEdition()}"/></td>
            <td><c:out value="${book.getAmount()}"/></td>
            <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/orderbook?bookID=${book.getId()}&userID=${user.getId()}"><fmt:message key="user.order.book"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
