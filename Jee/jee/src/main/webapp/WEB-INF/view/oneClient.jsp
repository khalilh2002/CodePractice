<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/head.jsp" />
<body>
<div class="container mt-5">
  <h1>Client Details</h1>

  <!-- Client Information -->
  <div class="card mb-4">
    <div class="card-body">
      <h5 class="card-title">Name: <c:out value="${client.nom}" /></h5>
      <h6 class="card-subtitle mb-2 text-muted">Prenom: <c:out value="${client.prenom}" /></h6>
    </div>
  </div>

  <!-- Commandes and their Produits -->
  <c:forEach items="${client.commandes}" var="commande">
    <div class="card mb-4">
      <div class="card-body">
        <h5 class="card-title">Commande ID: <c:out value="${commande.id}" /></h5>
        <h6>Quantity: <c:out value="${commande.quantite}" /></h6>
        <h6>Produits:</h6>
        <ul>
          <!-- Nested loop for produits -->
          <c:forEach items="${commande.produits}" var="produit">
            <li>
              <strong>Produit:</strong> <c:out value="${produit.nomProduit}" />
              <br />
              <strong>Prix:</strong> <c:out value="${produit.prix}" />
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>
