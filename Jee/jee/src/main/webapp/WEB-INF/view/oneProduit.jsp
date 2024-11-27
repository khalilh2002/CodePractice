<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Produit Details</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container my-5">
  <h1>Produit Details</h1>
  <p><strong>ID:</strong> <c:out value="${produit.id}" /></p>
  <p><strong>Nom:</strong> <c:out value="${produit.nomProduit}" /></p>
  <p><strong>Prix:</strong> <c:out value="${produit.prix}" /></p>
  <a href="/produit/list" class="btn btn-primary">Back to List</a>
</div>
</body>
</html>
