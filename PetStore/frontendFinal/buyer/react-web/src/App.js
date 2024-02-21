import { Route, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import './App.css'
import Cart from './pages/Cart'
import Login from './pages/Login'
import ProductList from './pages/ProductList'
import Register from './pages/Register'
import HomePage from './pages/HomePage'

function App() {
  return (
    <div className='container-fluid'>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/ProductList' element={<ProductList />} />
        <Route path='/homePage' element={<HomePage/>}/>
        <Route path='/cart' element={<Cart />} />
      </Routes>

      <ToastContainer />
    </div>
  )
}

export default App
