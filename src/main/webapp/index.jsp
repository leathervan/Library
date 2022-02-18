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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/"><span class="mb-0 h2">Library</span></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="d-flex">
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login</a>
                <a href="${pageContext.request.contextPath}/signup" class="btn btn-secondary">Sig up</a>
            </div>
        </form>
    </div>
</nav>
<form>
    <div class="input-group w-25">
        <input type="text" class="form-control" placeholder="Input name or author's name" aria-describedby="basic-addon2">
        <div class="input-group-append">
            <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Search</a>
        </div>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">Sort</button>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sort by name</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sort by author</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sort by edition</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sort by year edition</a></li>
            </ul>
        </div>
    </div>
</form>
<table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
        <th scope="col">Author</th>
        <th scope="col">Edition</th>
        <th scope="col">Year Edition</th>
        <th scope="col">Amount</th>
    </tr>
    </thead>


    <%
        BookService bookService=new BookService(BookDaoImpl.getInstance());
        List<Book> bookList = bookService.getAllBook();
        for (Book book : bookList) {
    %>
    <tr>
        <td><%= book.getId()%></td>
        <td><%= book.getName()%></td>
        <td><%= book.getAuthor()%></td>
        <td><%= book.getEdition()%></td>
        <td><%= book.getYear_edition()%></td>
        <td><%= book.getAmount()%></td>
    </tr>
    <%
        }
    %>

</table>
</body>
</html>
