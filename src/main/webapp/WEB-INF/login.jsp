<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 11.02.2022
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Log in</title>
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
                            <h3 class="mb-5">Sign in</h3>
                            <div class="form-outline mb-4">
                                <input type="login" id="login" name="login" class="form-control form-control-lg" />
                                <label class="form-label" >Email</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password" id="typePasswordX-2" name="password" class="form-control form-control-lg" />
                                <label class="form-label" for="typePasswordX-2">Password</label>
                            </div>
                            <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
                            <form action="${pageContext.request.contextPath}/signup">
                                <a href="${pageContext.request.contextPath}/signup" class="btn btn-lg btn-block btn-secondary" type="submit"> Sign Up</a>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!------ Include the above in your HEAD tag ---------->
<%--<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Login Form -->
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="login">
            <input type="text" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>
        <a href="${pageContext.request.contextPath}/signup">Sign Up</a>
    </div>
</div>--%>
<%--

<div class="page">
    <main>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="loader"></div>
            <input type="text" name="login" required placeholder="Login"><br>
            <input type="password" name="password" required placeholder="Password" minlength="8"><br>
            <button type="submit">Sign in</button>
        </form>
        <
    </main>
</div>--%>

</body>
</html>
