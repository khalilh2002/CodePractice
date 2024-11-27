<%--
  Created by IntelliJ IDEA.
  User: khalil
  Date: 11/26/24
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/head.jsp"/><%@ taglib prefix="c" uri="jakarta.tags.core"%>

<body>

<div class="container mt-5">
  <a href="./commande/add" class="btn btn-success">Add</a>

  <h1>List of commandes</h1>
  <table class="table">
    <thead>
    <tr>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="commande" items="${commandes}">
      <tr id="client-row-${client.id}">
        <td>${commande.id}</td>
        <td>
          <a href="./commande/${commande.id}" class="btn btn-primary">View</a>
          <button class="btn btn-danger" onclick="deleteClient(${commande.id})">Delete</button>
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

