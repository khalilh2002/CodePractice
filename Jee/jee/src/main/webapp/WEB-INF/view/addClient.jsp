
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/head.jsp" />
<body class="container mt-5">

<h2 class="mb-4">Add New Client</h2>

<form id="addClientForm" class="border p-4 rounded" style="max-width: 500px; margin: auto;">
  <div class="mb-3">
    <label for="nom" class="form-label">First Name (Nom)</label>
    <input type="text" id="nom" name="nom" class="form-control" placeholder="Enter first name" required>
  </div>
  <div class="mb-3">
    <label for="prenom" class="form-label">Last Name (Prenom)</label>
    <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Enter last name" required>
  </div>
  <button type="button" id="submitBtn" class="btn btn-primary">Add Client</button>
</form>

<div id="responseMessage" class="mt-4"></div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function () {
    $('#submitBtn').on('click', function () {
      const clientData = {
        nom: $('#nom').val(),
        prenom: $('#prenom').val()
      };

      $.ajax({
        url: '../client/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(clientData),
        success: function (response) {
          $('#responseMessage').html('<div class="alert alert-success">Client added successfully!</div>');
          $('#addClientForm')[0].reset();
        },
        error: function (xhr) {
          const errorMessage = xhr.responseJSON ? xhr.responseJSON.error : 'An error occurred.';
          $('#responseMessage').html('<div class="alert alert-danger">' + errorMessage + '</div>');
        }
      });
    });
  });
</script>

</body>
</html>
