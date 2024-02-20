// import axios from "axios"
// import { useState } from "react"
// import { useHistory } from "react-router-dom/cjs-router-dom.min"
// import '../CSS/Registration.css'

// const Register = ()=>{

//     //  const history = useHistory()
//     const [name, setName]=useState('')
//     const [phone, setPhone]=useState('')
//     const [email, setEmail]=useState('')
//     const [password, setPassword]=useState('')
//     const [address, setAddress]=useState('')

//     function onTextChange(e){
//         const fieldName = e.target.name;
//         const fieldValue = e.target.value;
//         console.log("Field Name: "+fieldName);
//         console.log("Field Value: "+fieldValue);
//         if (fieldName === 'name'){
//             setName(fieldValue)
//         } else if(fieldName === 'phone'){
//             setPhone(fieldValue)
//         } else if(fieldName === 'email'){
//             setEmail(fieldValue)
//         } else if(fieldName === 'password'){
//             setPassword(fieldValue)
//         } else if(fieldName === 'address'){
//             setAddress(fieldValue)
//         } 
//     }

//     function SubmitUser(){
//         if(name && phone && email && password && address){
//             axios.post('http://localhost:5000/customer/register', {
//                 name:name,
//                 phone:phone,
//                 email:email,
//                 password:password,
//                 address:address
//             })
//             .then(response =>{
//                 console.log(response.data);
//             })
//             .catch(error =>{
//                 console.log(error);
//             });
//         }
//         setName("");
//         setPhone("");
//         setEmail("")
//         setPassword("");
//         setAddress("");
//         // history.push('/')
//     }

//     return (
//         <div className="registration-container">
//           <div className="registration-wrapper">
//             <span>Sign Up</span>
//             <div className="registration-form">
//               <div className="signup-firstname">
//                 <label>First Name</label>
//                 <input type="text" name='name' value={name} onChange={onTextChange} />
//               </div>
//               <div className="signup-mobile">
//                 <label>Mobile</label>
//                 <input type="text" name='phone' value={phone} onChange={onTextChange} />
//               </div>
              
//               <div className="signup-email">
//                 <label>Email</label>
//                 <input type="email" name='email' value={email} onChange={onTextChange} />
//               </div>
//               <div className="signup-password">
//                 <label>Password</label>
//                 <input type="password" name='password' value={password} onChange={onTextChange} />
//               </div>
//               <div className="signup-address">
//                 <label>Address</label>
//                 <input type="text" name='address' value={address} onChange={onTextChange} />
//               </div>
//               <div className="signup-birthdate">
//                 <div className="signup-button">
//                   <button onClick={SubmitUser}>Sign Up</button>
//                 </div>
//               </div>
//             </div>
//           </div>
//         </div>
//       );
// }
// export default Register