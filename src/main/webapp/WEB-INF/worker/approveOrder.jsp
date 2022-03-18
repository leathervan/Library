<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 16.02.2022
  Time: 08:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>Approve order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<br>
<br>
<form action="${pageContext.request.contextPath}/approve?subID=${sub.id}&receiptID=${receipt.id}" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-12 d-flex justify-content-center align-items-center" >
                <div class="error-template">
                    <h1> Input date and time of return book</h1>
                    <br>
                    <table class="table w-75 p-3">
                        <thead class="table-dark">
                        <tr>
                            <th scope="col">Receipt id</th>
                            <th scope="col">User name</th>
                            <th scope="col">User surname</th>
                            <th scope="col">Book name</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${receipt.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.surname}</td>
                                <td>${book.name}</td>
                            </tr>
                        </tbody>
                    </table>
                    <input id="datetime" name="datetime" type="datetime-local">
                    <form>
                        <button class="btn btn-primary btn-lg btn-block" type="submit">Approve</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
