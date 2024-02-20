// import axios from "axios";
// import { useState } from "react";
// import { Link, useHistory } from "react-router-dom/cjs/react-router-dom.min"
// import '../CSS/login.css'


// const Login =()=>{

//     // const history = useHistory();
//     const [email, setEmail] = useState('');
//     const [password, setPassword] = useState('');
    
//     const onTextChange = (e) =>{
//         if(e.target.name === 'email')
//             setEmail(e.target.value)
//         if(e.target.name === 'password')
//             setPassword(e.target.value)

//         console.log(password);
//     }

//     const Submit = ()=>{
//         axios.post('http://localhost:5000/customer/login',{
//             email:email,
//             password:password
//         })
//         .then((response)=>{
//             console.log(response.data);
//             if(response.data.token !== null && response.data.token !== undefined){
//                 sessionStorage.setItem("customerID", response.data.id);
//                 sessionStorage.setItem("name", response.data.name)
//                 sessionStorage.setItem("token", response.data.token)
//                 sessionStorage.setItem("email", email)
//             }
//             // history.push("/home")
//         })
//         .catch((error)=>{
//             console.log(error);
//         })
//     }

//     return(
//         <>
//         <div className="login-container">
//         <div className="login-wrapper">
//             <span>Login</span>
//             <div className="login-form">
//                 <div className="login-email">
//                     <label>Email</label>
//                     <input type="email" name='email' onChange={onTextChange} value={email}/>
//                 </div>

//                 <div className="login-password">
//                     <label>Password</label>
//                     <input type="password" name='password' onChange={onTextChange} value={password} />
//                 </div>

//                 <div className="login-submitbutton">
//                     <button onClick={Submit}>Login</button>
//                 </div>
//                 <div className="signup-link">
//                     <span>Don't have account? <Link to="/registration">Sign Up</Link></span>
//                 </div>
//             </div>
//         </div>
//     </div>
//         </>
//     )
// }

// export default Login


