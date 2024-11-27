<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Produit</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <script>
    async function addProduit(event) {
      event.preventDefault(); // Prevent the default form submission behavior

      // Collect form data
      const nomProduit = document.getElementById("nomProduit").value;
      const prix = document.getElementById("prix").value;

      // Create JSON payload
      const produitData = {
        nomProduit: nomProduit,
        prix: prix
      };

      try {
        // Send POST request with JSON payload
        const response = await fetch("../produit/add", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(produitData)
        });

        if (response.ok) {
          const result = await response.json();
          alert("Produit added successfully: " + result.message);
          // Optionally, redirect or clear the form
          document.getElementById("addProduitForm").reset();
        } else {
          const error = await response.json();
          alert("Failed to add produit: " + error.error);
        }
      } catch (err) {
        console.error("Error adding produit:", err);
        alert("An error occurred while adding the produit.");
      }
    }
  </script>
</head>
<body>
<div class="container my-5">
  <h1>Add Produit</h1>
  <form id="addProduitForm" onsubmit="addProduit(event)">
    <div class="mb-3">
      <label for="nomProduit" class="form-label">Nom Produit</label>
      <input type="text" class="form-control" id="nomProduit" name="nomProduit" required>
    </div>
    <div class="mb-3">
      <label for="prix" class="form-label">Prix</label>
      <input type="number" class="form-control" id="prix" name="prix" step="0.01" required>
    </div>
    <button type="submit" class="btn btn-success">Add Produit</button>
  </form>
</div>
</body>
</html>
