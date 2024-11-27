<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/view/include/head.jsp" />

<body>
<div class="container mt-5">
  <h1>List of Clients</h1>
  <table class="table">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nom</th>
      <th>Prenom</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client" items="${clientList}">
      <tr id="client-row-${client.id}">
        <td>${client.id}</td>
        <td>${client.nom}</td>
        <td>${client.prenom}</td>
        <td>
          <a href="./client/edit?id=${client.id}" class="btn btn-warning">edit</a>

          <a href="./client/${client.id}" class="btn btn-primary">View</a>
          <button class="btn btn-danger" onclick="deleteClient(${client.id})">Delete</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script>
  /**
   * Deletes a client by ID using a POST request to the server.
   * On success, removes the row from the table.
   */
  function deleteClient(clientId) {
    if (!confirm("Are you sure you want to delete this client?")) {
      return;
    }

    fetch(`./client/delete`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id: clientId }),
    })
      .then(response => {
        if (response.ok) {
          // Remove the row from the table
          const row = document.getElementById(`client-row-${clientId}`);
          if (row) {
            row.remove();
          }
          alert("Client deleted successfully.");
        } else {
          return response.text().then(text => {
            throw new Error(text || "Failed to delete client.");
          });
        }
      })
      .catch(error => {
        console.error("Error deleting client:", error);
        alert("Error: " + error.message);
      });
  }
</script>
</body>
</html>
