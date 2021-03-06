<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 14.02.2022
  Time: 23:47
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
        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/user"><fmt:message key="index.search"/></a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/userProfile"><fmt:message key="user.subs"/></a>
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
      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/userProfile?sessionLocale=en">en</a></li>
      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/userProfile?sessionLocale=ru">ru</a></li>
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
<form class="d-flex ">
  <table class="table w-50 p-3 table-primary">
    <thead class="table-dark">
    <tr>
      <th scope="col">Subscription id</th>
      <th scope="col">Subscription start</th>
      <th scope="col">Subscription end</th>
      <th scope="col">Debt</th>
      <th scope="col">Book id</th>
      <th scope="col">Book name</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subs}" var="sub">
      <tr>
        <td><c:out value="${sub.getId()}"/></td>
        <td><c:out value="${sub.getStart()}"/></td>
        <td><c:out value="${sub.getEnd()}"/></td>
        <td><c:out value="${sub.getDebt()}"/></td>
        <td><c:out value="${sub.getBook().getId()}"/></td>
        <td><c:out value="${sub.getBook().getName()}"/></td>
        <td><a class="btn btn-secondary" href="${pageContext.request.contextPath}/returnbook?subID=${sub.getId()}"><fmt:message key="user.return"/></a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <table class="table w-50 p-3 table-info">
    <thead class="table-dark">
    <tr>
      <th scope="col">Receipt id</th>
      <th scope="col">Book id</th>
      <th scope="col">Book name</th>
      <th scope="col">Book author</th>
      <th scope="col">Status</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${receipts}" var="receipt">
      <tr>
        <td><c:out value="${receipt.getId()}"/></td>
        <td><c:out value="${receipt.getBook().getId()}"/></td>
        <td><c:out value="${receipt.getBook().getName()}"/></td>
        <td><c:out value="${receipt.getBook().getAuthor()}"/></td>
        <td><c:out value="${receipt.getStatus()}"/></td>
        <td><a class="btn btn-secondary" href="${pageContext.request.contextPath}/rejectorder?receiptID=${receipt.getId()}"><fmt:message key="user.reject"/></a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</form>

</body>
</html>
