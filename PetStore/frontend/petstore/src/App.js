import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import { Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import HomePage from './pages/HomePage';
import Navbar from './pages/Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
// import Product from './pages/Products';



function App() {
  return (
    <div className="App">
    <Navbar/>     
    </div>
  );
}

export default App;
