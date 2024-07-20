/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";
import { Modal, Button, CardImg } from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function UpdateProduct({ id }) {
  const [product, setProduct] = useState("");

  const [name, setName] = useState("");
  const [file, setFile] = useState("");
  const [description, setDescription] = useState("");

  const [show, setShow] = useState(false);
  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  async function fetchData(userID) {
    let result = await fetch(
      "http://127.0.0.1:8000/api/product/get/" + userID,
      {
        method: "GET",
        headers: {
          Accept: "*/*",
        },
      }
    );
    result = await result.json();
    setProduct(result);
  }

  async function SubmitUpdate() {
    if (description.trim() !== '') {
      setDescription(product.description)
    }

    const formData = new FormData();
    formData.append("id", id);
    formData.append("name", name);
    formData.append("file", file);
    formData.append("description", description);

    let result = await fetch("http://127.0.0.1:8000/api/product/update", {
      method: "POST",
      body: formData,
      headers: {
        Accept: "application/json",
      },
    });
    result = await result.json();

    if (result.ok) {
      alert(result.message);
    } else if (result.error) {
      alert(result.error);
    }
  }

  useEffect(() => {
    fetchData(id); // Pass id directly instead of { id }
  }, [id]);

  return (
    <>
      <Button variant="primary" className="btn btn-sm mx-2" onClick={handleShow}>
          Update
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <CardImg
            src={`http://127.0.0.1:8000/${product.file_path}`}
            alt=""
            width={300}
          />
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
            placeholder={product.name}
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
            value={description } // Use the state value if available, otherwise use the default value from product
            onChange={(e) => setDescription(e.target.value)}
            placeholder={product.description} // Optional placeholder if needed
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={SubmitUpdate}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default UpdateProduct;
