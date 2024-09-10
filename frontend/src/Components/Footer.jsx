import React from 'react'

const Footer = () => {
  return (
    <div className='mt-auto'>
        <div className='flex w-screen justify-center bg-black text-white py-10'>
            <div className='flex flex-col items-center'>
                <h1 className='font-semibold text-xl'>Student Management</h1>
                <p >by <span className='text-sm'>Faleshwar Nishad</span> </p>
                <p className='text-sm'>@Copyright 2024</p>
            </div>
        </div>
    </div>
  )
}

export default Footer