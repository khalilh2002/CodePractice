import { useState  , useEffect } from "react";
import {  Card, Col } from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import Header from './Header';
export default function Register() {

  const [name , setName] = useState('');
  const [email , setEmail] = useState('');
  const [password , setPassword] = useState('');

  const history = useNavigate();

  useEffect( ()=>{
    if (localStorage.getItem('user-info')) {
      history('/product');
    }
  }, [history]);


  async function signUp(){
    let item = {name , email , password};
    console.warn(item);
    
    let  result = await fetch("http://127.0.0.1:8000/api/register",{
      method:'POST',
      body: JSON.stringify(item),
      headers:{
        "Content-Type":"application/json",
        "Accept":"application/json"
      }
    });

    result = await result.json();
    localStorage.setItem("user-info",JSON.stringify(result));
    history('/product');
    console.warn(result);

  }
  return (
    <>
      <Header/>

      <div className="d-flex justify-content-center align-items-center my-3">
        <Col md={6}>
          <Card>
            <Card.Header>title</Card.Header>
            <Card.Body>
              <div className="col-md-12">
                <div className="form-group">
                  <label htmlFor="name">Name</label>
                  <input type="text" value={name} onChange={(e)=>setName(e.target.value)} name="name" className="form-control" />
                </div>
                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <input type="email"value={email} onChange={(e)=>setEmail(e.target.value)}  name="email" className="form-control" />
                </div>
                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <input type="password"value={password} onChange={(e)=>setPassword(e.target.value)}  name="password" className="form-control" />
                </div>
                <button type="submit" onClick={signUp} className="btn btn-primary m-3 ">
                  Submit
                </button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </div>
    </>
      
  );
}
