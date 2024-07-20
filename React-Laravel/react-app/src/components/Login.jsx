/* eslint-disable react-hooks/rules-of-hooks */
import {  Card, Col } from "react-bootstrap";
import {  useState,useEffect } from "react";
import {useNavigate} from "react-router-dom";


import Header from "./Header";



export default function login() {
  
  
  const history = useNavigate();

  useEffect( ()=>{
    if (localStorage.getItem('user-info')) {
      history('/product');
    }
  }, [history]);

  const [email,setEmail] = useState('');
  const [password,setPassword] = useState('');
  
  async function login() {
    let item = {email , password };
    try {
      console.warn(JSON.stringify(item));
      
      let  result = await fetch("http://127.0.0.1:8000/api/login",{
        method:'POST',
        body: JSON.stringify(item),
        headers:{
          "Content-Type":"application/json",
          "Accept":"application/json"
        }
      });

      result = await result.json();
      console.log(result);

      if (result.error=="email or password are incorrect") {
        alert('email or password are incorrect');
        return;
      }

      localStorage.setItem("user-info",JSON.stringify(result));
      history('/product');
      
    } catch (error) {
      console.error('Login failed:', error.message);

    }
    

  }


  return (
    <>
      
      <Header/>
      

      <div className="d-flex justify-content-center align-items-center my-3">
        <Col md={6}>
          <Card>
            <Card.Header>Login</Card.Header>
            <Card.Body>
              <div className="col-md-12">
                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <input type="email" value={email} onChange={(e)=> setEmail(e.target.value)} name="email" className="form-control" />
                </div>
                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <input type="password" value={password} onChange={(e)=> setPassword(e.target.value)}  name="password" className="form-control" />
                </div>
                <button type="submit" onClick={login} className="btn btn-primary m-3 ">
                  Submit
                </button>
                
              </div>
            </Card.Body>
          </Card>
        </Col>
      </div>  
    </>
    
  )
}
