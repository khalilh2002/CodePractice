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
<script>
  function deleteProduit(id) {
    console.log(id)
    if (!confirm("Are you sure you want to delete this produit?")) {
      return;
    }
    fetch(`./produit/delete`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id: id }),
    })
      .then(response => {
        if (response.ok) {
          // Remove the row from the table
          const row = document.getElementById(`produit_${id}`);
          if (row) {
            row.remove();
          }
          alert("Produit deleted successfully.");
        } else {
          return response.json().then(error => {
            throw new Error(error.error || "Failed to delete produit.");
          });
        }
      })
      .catch(error => {
        console.error("Error deleting produit:", error);
        alert("Error: " + error.message);
      });
  }

</script>

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
      <tr id="produit_${produit.id}">
        <td><c:out value="${produit.id}" /></td>
        <td><c:out value="${produit.nomProduit}" /></td>
        <td><c:out value="${produit.prix}" /></td>
        <td>
          <a href="./produit/${produit.id}" class="btn btn-primary">Details</a>
          <a onclick="deleteProduit(${produit.id})" class="btn btn-danger">delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>


</body>
</html>
