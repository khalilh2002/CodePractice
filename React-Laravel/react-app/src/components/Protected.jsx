import {useNavigate} from "react-router-dom";
import {  useEffect } from "react";

export default function Protected(urlVar) {

    let Cmp = urlVar.Cmp;
    console.log(Cmp);
    const history = useNavigate();

    useEffect( ()=>{
        if (!localStorage.getItem('user-info')) {
            history("/register");
        }
        
      }, [history]);
  
      return(
        <Cmp></Cmp>
      );
}
