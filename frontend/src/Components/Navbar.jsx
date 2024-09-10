import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const Navbar = () => {

  const token = localStorage.getItem("token")
   const [isAuthenticated, toggleAuthenticate] = useState(token!=null);
  
   const handleLogout = (e)=>{
    e.preventDefault();
    localStorage.removeItem("token");
    toggleAuthenticate(false);
    window.location.reload();
   }

    const links = [{name:"Home", link:"/home" }, {name:"About", link:"/about"}, {name:"Services", link:"/services"}, {name:"Contact Us", link:"/contact"}];
  return (
    <nav>
        <div className=' bg-black text-white font-semibold py-4 px-1 flex'>
            <ul className=' block sm:flex gap-x-5 w-1/2'>
            <li></li>
            {links.map((value, i)=><li className='hover:text-green-400 cursor-pointer' key={i}>
                <a href={value.link}>{value.name}</a>
            </li>)}
            </ul>

           <ul className='flex w-1/2 justify-end mr-2 gap-x-5'>
          {isAuthenticated? <li>
                <button className='hover:text-green-500' onClick={handleLogout}>Log Out</button>
              </li>:<>
              <li>
                <Link className='hover:text-green-500' to="/login">Login</Link>
              </li>
              <li>
                <Link className='hover:text-green-500' to="/register">Register</Link>
              </li></>}
            </ul>
        </div>
    </nav>
  )
} 

export default Navbar