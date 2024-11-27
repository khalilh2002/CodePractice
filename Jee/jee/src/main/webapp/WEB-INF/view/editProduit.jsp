<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Produit</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <script>
    async function updateProduit(event) {
      event.preventDefault();

      // Collect form data
      const id = document.getElementById("id").value;
      const nomProduit = document.getElementById("nomProduit").value;
      const prix = document.getElementById("prix").value;

      // Create JSON payload
      const produitData = {
        id: id,
        nomProduit: nomProduit,
        prix: prix
      };

      try {
        // Send POST request with JSON payload
        const response = await fetch("../produit/edit", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(produitData)
        });

        if (response.ok) {
          const result = await response.json();
          alert("Produit updated successfully: " + result.message);
          window.location.href = "../produit/list"; // Redirect to the list page
        } else {
          const error = await response.json();
          alert("Failed to update produit: " + error.error);
        }
      } catch (err) {
        console.error("Error updating produit:", err);
        alert("An error occurred while updating the produit.");
      }
    }
  </script>
</head>
<body>
<div class="container my-5">
  <h1>Edit Produit</h1>
  <form id="editProduitForm" onsubmit="updateProduit(event)">
    <input type="hidden" id="id" name="id" value="${produit.id}">
    <div class="mb-3">
      <label for="nomProduit" class="form-label">Nom Produit</label>
      <input type="text" class="form-control" id="nomProduit" name="nomProduit" value="${produit.nomProduit}" required>
    </div>
    <div class="mb-3">
      <label for="prix" class="form-label">Prix</label>
      <input type="number" class="form-control" id="prix" name="prix" value="${produit.prix}" step="0.01" required>
    </div>
    <button type="submit" class="btn btn-primary">Save Changes</button>
  </form>
</div>
</body>
</html>
