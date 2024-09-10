import { BrowserRouter, Route, Routes } from "react-router-dom";
import EditStudent from "./Components/EditStudent";
import Footer from "./Components/Footer";
import Home from "./Components/Home";
import Login from "./Components/Login";
import Navbar from "./Components/Navbar";
import Table from "./Components/Table";
import Register from "./Components/Register";
import RegisterStudent from "./Components/RegisterStudent";
import { useEffect, useState } from "react";
import NotFoundPage from "./Components/NotFoundPage";



function App() {
 

const token = localStorage.getItem("token");
 const [isAuthenticated, toggleAuthenticate] = useState(token!=null);

 useEffect(()=>{
  toggleAuthenticate(token!=null);
 },[])


  return (
    <BrowserRouter>
    
    <div className="flex flex-col min-h-screen">
    <Navbar/>
   <Routes>
    {isAuthenticated?<><Route path="/*" element={<Home/>}/>
    <Route path="/home" element={<Home/>}/>
    <Route path="/add" element={<RegisterStudent/>} />
    <Route path="/view" element={<Table/>} />
    <Route path="/edit/:id" element={<EditStudent/>} /></>:<>
    <Route path="/*" element={<NotFoundPage/>}/>
    <Route path="/login" element={<Login/>} />
    <Route path="/register" element={<Register/>} />
    </>}

   </Routes>
    
    
    <Footer/></div>
    </BrowserRouter>
  );
}

export default App;
