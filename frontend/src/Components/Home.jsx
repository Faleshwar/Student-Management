import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {

    const task = [{name:"Add New", link:"/add"}, {name:"View", link:"/view"}, {name:"Manage", link:"/view"}]

  return (
    <div className='flex my-auto justify-center items-start'>
        <div className='w-full mt-3 flex flex-col bg-white md:w-1/3 px-5 py-3 '>
        {task.map((value, i)=><Link key={i} to={value.link} className='mt-5 text-center bg-black text-white font-semibold border-2 border-black px-3 py-1 rounded-md shadow-lg hover:bg-transparent hover:text-black'>{value.name} Student
        </Link>)}
        </div>
    </div>
  )
}

export default Home