
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
//import './App.css';


import Login from "./components/Login";
import Register from "./components/Register";
import Product from "./components/Product";

import AddProduct from "./components/AddProduct";
import UpdateProduct from "./components/UpdateProduct";
import RemoveProduct from "./components/RemoveProduct";

import About from "./components/About";
import Protected from "./components/Protected";
import Home from './components/Home';



function App() {
  

  return (
    <>
      <BrowserRouter>
        <header>

          <Routes>
            <Route path="" element={<Home />} />
            <Route path="home" element={<Home />} />
            <Route path="Register" element={<Register />} />
            <Route path="login" element={<Login />} />
            <Route path="product" element={<Product />} />
            <Route path="about" element={<About />} />
            <Route path="product/addProduct" element={<Protected Cmp={AddProduct}/>} />
            <Route path="product/updateProduct" element={<Protected Cmp={UpdateProduct}/>} />
            <Route path="product/removeProduct" element={<Protected Cmp={RemoveProduct}/>} />

          </Routes>
        </header>
      </BrowserRouter>

    </>
  );
}

export default App;
