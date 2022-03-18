<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 11.02.2022
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title><fmt:message key="index.login"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center">
<section class="vh-100" style="background-color: #bdbebd;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="card-body p-5 text-center">
                            <h3 class="mb-5"><fmt:message key="index.login"/></h3>
                            <div class="form-outline mb-4">
                                <input type="login" id="login" name="login" class="form-control form-control-lg" />
                                <label class="form-label"><fmt:message key="index.email"/></label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password" id="typePasswordX-2" name="password" class="form-control form-control-lg" />
                                <label class="form-label" for="typePasswordX-2"><fmt:message key="index.password"/></label>
                            </div>
                            <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="index.signin"/></button>
                            <form action="${pageContext.request.contextPath}/signup">
                                <a href="${pageContext.request.contextPath}/signup" class="btn btn-lg btn-block btn-secondary" type="submit"><fmt:message key="index.signup"/></a>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
