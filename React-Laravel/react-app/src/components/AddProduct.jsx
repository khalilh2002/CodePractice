import { useState } from "react";
import { Button, Card, Col } from "react-bootstrap";
import Header from "./Header";

function AddProduct() {
  const [name, setName] = useState("");
  const [file, setFile] = useState("");
  const [description, setDescription] = useState("");

  async function submit() {
    const formData = new FormData();
    console.log(name, file, description);
    formData.append("name", name);
    formData.append("file", file);
    formData.append("description", description);

    try {
      let result = await fetch("http://127.0.0.1:8000/api/product/addProduct", {
        method: "POST",
        body: formData,
        headers: {
          "Accept": "application/json"
      }
      });

      result = await result.json();

      if (result.error) {
        alert(result.messages);
        return;
      }
      console.log(result);
      alert("Product saved");
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred while saving the product");
    }
  }

  return (
    <div>
      <Header />
      <div className="mx-auto p-3 d-flex justify-content-center align-items-center">
        <Col md={6}>
          <Card>
            <Card.Header>Title</Card.Header>
            <Card.Body>
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type="text"
                name="name"
                id="name"
                className="form-control"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />

              <label htmlFor="file" className="form-label">
                Image
              </label>
              <input
                type="file"
                name="file"
                id="file"
                className="form-control"

                onChange={(e) => setFile(e.target.files[0])} // Use e.target.files[0] for file input
              />

              <label htmlFor="description" className="form-label">
                Description
              </label>
              <input
                type="text"
                name="description"
                id="description"
                className="form-control"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              />

              <Button onClick={submit} className="my-2">
                Click
              </Button>
            </Card.Body>
          </Card>
        </Col>
      </div>
    </div>
  );
}

export default AddProduct;
