import { useEffect, useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./Header";
import Update from "./UpdateProduct";


function ProductList() {
  const [products, setProducts] = useState([]);
  async function fetchData() {
    try {
      let result = await fetch("http://127.0.0.1:8000/api/product/list", {
        method: "GET",
        headers: {
          Accept: "*/*",
        },
      });
      result = await result.json();
      setProducts(result);
    } catch (error) {
      console.error("Error fetching the product list:", error);
    }
  }
  useEffect(() => {
    
    fetchData();
  }, []);


  async function deleteItem(id) {
    let result = await fetch('http://127.0.0.1:8000/api/product/delete/' + id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Accept": "*/*"
      }
    });
    result = await result.json()
    if (result.ok) {
      alert(result.message)
      fetchData()

    }else if (result.error) {
      alert(result.message)
    }
  }

 
  return (
    <div>
      <Header />
      <Row className="g-4 p-4" >
        {products.map((item , index) => (
          <Col md={3} key={index} >
            <Card>
              <Card.Img
                src={`http://127.0.0.1:8000/${item.file_path}`}
                alt={item.name}
              />
              <div className="card-body  text-center">
                <h3 >{item.name}</h3>
                <a onClick={()=>{deleteItem(item.id)}} className="btn btn-sm btn-danger">Delete</a>
                <Update id={item.id}>
                </Update>
              </div>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
}

export default ProductList;
