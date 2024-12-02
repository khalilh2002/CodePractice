<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JSP - Hello World</title>
  <!-- Bootstrap CSS -->
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Optional Bootstrap JS (for some components) -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Main container -->
<div class="container mt-5">
  <!-- Card for content -->
  <div class="card">
    <div class="card-header">
      Welcome
    </div>
    <div class="card-body">
      <h1>Welcome to the Application!</h1>

      <!-- Buttons styled with Bootstrap -->
      <a href="user/user-all.xhtml" class="btn btn-primary btn-lg">User List</a>
      <a href="user/user-add.xhtml" class="btn btn-success btn-lg">User Add</a>
      <a href="product/product-list.xhtml" class="btn btn-info btn-lg">Product List</a>
      <a href="login.xhtml" class="btn btn-warning btn-lg">Login</a>

      <br/><br/>
      <h4>You need to be logged in to access the cart.</h4>

      <a href="cart/cart.xhtml" class="btn btn-danger btn-lg">Go to Cart</a>
    </div>
  </div>
</div>

</body>
</html>
