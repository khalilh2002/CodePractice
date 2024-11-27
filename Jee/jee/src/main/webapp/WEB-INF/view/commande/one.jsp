<jsp:include page="../include/head.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<body>
<div class="container my-5">
  <h1 class="mb-4">Commande Details</h1>

  <div class="card mb-4">
    <div class="card-header">
      <h5>Commande ID: <c:out value="${commande.id}" /></h5>
    </div>
    <div class="card-body">
      <p><strong>Quantity:</strong> <c:out value="${commande.quantite}" /></p>

      <h5>Client Details:</h5>
      <ul>
        <li><strong>ID:</strong> <c:out value="${commande.client.id}" /></li>
        <li><strong>Name:</strong> <c:out value="${commande.client.nom}" /></li>
        <li><strong>Email:</strong> <c:out value="${commande.client.prenom}" /></li>
      </ul>

      <h5>Products:</h5>
      <ul>
        <c:forEach var="product" items="${commande.produits}">
          <li>
            <strong>ID:</strong> <c:out value="${product.id}" /> -
            <strong>Name:</strong> <c:out value="${product.nomProduit}" /> -
            <strong>Price:</strong> <c:out value="${product.prix}" />
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <a href="/commande/list" class="btn btn-primary">Back to Commandes</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
