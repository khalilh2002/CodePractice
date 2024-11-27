<jsp:include page="../include/head.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<body>
<h2 class="mb-4">Add Commande</h2>

<form id="commandeForm" class="border p-4 rounded" style="max-width: 500px; margin: auto;">
  <!-- Client Selection -->
  <div class="mb-3">
    <label for="clientSelect" class="form-label">Select Client</label>
    <select class="form-select" id="clientSelect" name="client_id" required>
      <c:forEach items="${clients}" var="client">
        <option value="${client.id}">${client.nom} ${client.prenom}</option>
      </c:forEach>
    </select>
  </div>

  <!-- Product Selection -->
  <div class="mb-3">
    <label for="productSelect" class="form-label">Select Products</label>
    <select class="form-select" id="productSelect" name="produit_ids" multiple required>
      <c:forEach items="${produits}" var="produit">
        <option value="${produit.id}">${produit.nomProduit}</option>
      </c:forEach>
    </select>
  </div>

  <!-- Submit Button -->
  <button type="submit" id="submitBtn" class="btn btn-primary">Add Command</button>
</form>

<script>
  // JavaScript to handle form submission via fetch API
  document.getElementById('commandeForm').addEventListener('submit', async function (e) {
    e.preventDefault(); // Prevent default form submission

    // Get form values
    const clientId = document.getElementById('clientSelect').value;
    const productSelect = document.getElementById('productSelect');
    const productIds = Array.from(productSelect.selectedOptions).map(option => option.value);

    // Prepare JSON payload
    const payload = {
      client_id: parseInt(clientId),
      produit_ids: productIds.map(id => parseInt(id))
    };

    try {
      console.log(JSON.stringify(payload));
      // Send POST request
      const response = await fetch('../commande/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        const result = await response.json();
        alert(result.message);
      } else {
        const error = await response.json();
        alert('Error: ' + error.error);
      }
    } catch (err) {
      alert('An unexpected error occurred: ' + err.message);
    }
  });
</script>
</body>
</html>
