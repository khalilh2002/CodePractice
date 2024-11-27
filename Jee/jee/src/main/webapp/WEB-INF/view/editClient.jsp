<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Client</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <script>
    async function editClient(event) {
      event.preventDefault();

      // Collect form data
      const id = parseInt(document.getElementById("id").value, 10); // Convert to number
      const nom = document.getElementById("nom").value;
      const prenom = document.getElementById("prenom").value;

      const clientData = { id, nom, prenom };

      try {
        const response = await fetch("../client/edit", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(clientData),
        });

        if (response.ok) {
          const result = await response.json();
          alert(result.message);
          window.location.href = "../client/list";
        } else {
          const error = await response.json();
          alert("Error: " + error.error);
        }
      } catch (err) {
        console.error(err);
        alert("An error occurred while updating the client.");
      }
    }

  </script>
</head>
<body>
<div class="container my-5">
  <h1>Edit Client</h1>
  <form onsubmit="editClient(event)">
    <input type="hidden" id="id" value="${client.id}" />
    <div class="mb-3">
      <label for="nom" class="form-label">Nom</label>
      <input type="text" class="form-control" id="nom" name="nom" value="${client.nom}" required>
    </div>
    <div class="mb-3">
      <label for="prenom" class="form-label">Prenom</label>
      <input type="text" class="form-control" id="prenom" name="prenom" value="${client.prenom}" required>
    </div>
    <button type="submit" class="btn btn-primary">Save Changes</button>
  </form>
</div>
</body>
</html>
