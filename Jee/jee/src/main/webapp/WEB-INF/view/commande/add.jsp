<jsp:include page="../include/head.jsp"/>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<body>
<h2 class="mb-4">Add Commande</h2>

<form  class="border p-4 rounded" style="max-width: 500px; margin: auto;">
  <select class="form-select">
    <c:forEach items="${clients}" var="client">
        <option value="${client.id}">${client.nom} ${client.prenom}</option>
    </c:forEach>
  </select>

  <button type="button" id="submitBtn" class="btn btn-primary">Add Command</button>
</form>

</body>
</html>
