<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 21.02.2022
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/admin"><span class="mb-0 h2"><fmt:message key="index.library"/></span> </a>
        <form class="d-flex">
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/admin" class="btn btn-primary"><fmt:message key="index.back"/></a>
            </div>
        </form>
    </div>
</nav>
<body>
<form action="${pageContext.request.contextPath}/editbook?bookID=${book.id}" method="post">
<table class="table w-75 p-3">
    <thead class="table-dark">
    <tr>
        <th scope="col"><fmt:message key="book.name"/></th>
        <th scope="col"><fmt:message key="book.author"/></th>
        <th scope="col"><fmt:message key="book.edition"/></th>
        <th scope="col"><fmt:message key="book.year"/></th>
        <th scope="col"><fmt:message key="book.amount"/></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.edition}</td>
            <td>${book.yearEdition}</td>
            <td>${book.amount}</td>

        </tr>
        <tr>
            <td><input type="text" id="name" name="name" class="form-control" placeholder="Input book name" aria-describedby="basic-addon2"></td>
            <td><input type="text" id="author" name="author" class="form-control" placeholder="Input book author" aria-describedby="basic-addon2"></td>
            <td><input type="text" id="edition" name="edition" class="form-control" placeholder="Input book edition" aria-describedby="basic-addon2"></td>
            <td><input type="number" id="year" name="year" class="form-control" placeholder="Input book year edition" aria-describedby="basic-addon2"></td>
            <td><input type="number" id="amount" name="amount" class="form-control" placeholder="Input book year edition" aria-describedby="basic-addon2"></td>
            <td><button class="btn btn-secondary" type="submit">Edit book</button></td>
        </tr>
    </tbody>
</table>
</form>
</body>
</html>
