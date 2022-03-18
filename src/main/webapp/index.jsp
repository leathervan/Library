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
<section class="vh-125" style="background-color: #bdbebd;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <img src="img/logo.jpg">
                    <div class="card-body p-5 text-center">
                        <p class="text-center fs-1"><fmt:message key="index.welcome"/></p>
                        <p class="text-center"><fmt:message key="index.please"/></p>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-lg btn-block btn-primary" type="submit"><fmt:message key="index.signin"/></a>
                        <a href="${pageContext.request.contextPath}/signup" class="btn btn-lg btn-block btn-secondary" type="submit"><fmt:message key="index.signup"/></a>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item"><a class="page-link" href="index.jsp?sessionLocale=en">en</a></li>
                            <li class="page-item"><a class="page-link" href="index.jsp?sessionLocale=ru">ru</a></li>
                            <li class="page-item"><a class="page-link" href="#">ua</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
