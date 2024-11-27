<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Produit List</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container my-5">
  <h1>Produit List</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nom</th>
      <th>Prix</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="produit" items="${produitList}">
      <tr>
        <td><c:out value="${produit.id}" /></td>
        <td><c:out value="${produit.nomProduit}" /></td>
        <td><c:out value="${produit.prix}" /></td>
        <td>
          <a href="./produit/${produit.id}" class="btn btn-primary">Details</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
