<%@ page import="entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.book.BookDao" %>
<%@ page import="dao.book.BookDaoImpl" %>
<%@ page import="service.UserService" %>
<%@ page import="service.BookService" %>
<html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<section class="vh-100" style="background-color: #bdbebd;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <img src="img/logo.jpg">
                    <div class="card-body p-5 text-center">
                        <p class="text-center fs-1">Welcome to the library</p>
                        <p class="text-center">Please, login or sign up to continue</p>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-lg btn-block btn-primary" type="submit"> Login</a>
                        <a href="${pageContext.request.contextPath}/signup" class="btn btn-lg btn-block btn-secondary" type="submit"> Sign Up</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
