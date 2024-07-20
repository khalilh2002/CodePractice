/* eslint-disable react-hooks/rules-of-hooks */
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import NavDropDown from "react-bootstrap/NavDropDown";

import { useNavigate } from "react-router-dom";

import { Link } from "react-router-dom";
import "./Css/header.css";

export default function header() {
  let user = JSON.parse(localStorage.getItem("user-info"));
  const history = useNavigate();
  function logout() {
    localStorage.clear();
    history("/product");
  }

  return (
    <>
      <Navbar bg="primary" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="#home">Navbar</Navbar.Brand>
          <Nav className="me-auto nav_bar">
            {localStorage.getItem("user-info") ? (
              <>
                <Link className="NavItem" to="/home">
                  home
                </Link>
                <Link className="NavItem" to="/product">
                  product
                </Link>
                <Link className="NavItem" to="/about">
                  about
                </Link>
                <Link className="NavItem" to="/product/addProduct">
                  addProduct
                </Link>
                <Link className="NavItem" to="/product/updateProduct">
                  updateProduct
                </Link>
                <Link className="NavItem" to="/product/removeProduct">
                  removeProduct
                </Link>

              </>
            ) : (
              <>
                <Link className="NavItem" to="/login">
                  login
                </Link>
                <Link className="NavItem" to="/register">
                  register
                </Link>
              </>
            )}
          </Nav>
          {localStorage.getItem("user-info") ? (
            <>
              <Nav>
                <NavDropDown title={user && user.name}>
                  <NavDropDown.Item onClick={logout}>Logout</NavDropDown.Item>
                </NavDropDown>
              </Nav>
            </>
          ) : (
            <></>
          )}
        </Container>
      </Navbar>
    </>
  );
}
