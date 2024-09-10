import { ErrorMessage, Field, Form, Formik } from 'formik'
import * as Yup from 'yup';
import React, { useState } from 'react'
import axios from 'axios';
import URL from '../Urls/url';

const userRegistrationSchema = Yup.object({
  username:Yup.string().required("Username is required"),
  name:Yup.string().required("Name is required"),
  password:Yup.string().required("Password is required")
});



const Register = () => {
  const [message, setMessage] = useState("");

  const registerRequest = async (data)=>{
    try{
  
      const res = await axios.post(URL+"/register", data);
      if(res.data){
        setMessage("success");
      }
    }catch(err){
      console.log(err);
      setMessage("error")
    }
  }


  return (
    <div className="block md:flex justify-center items-start bg-transparent mb-20">
      <div className="w-full md:w-1/3 mt-10 py-5 px-10 rounded-md shadow-lg bg-slate-50">
      {message==="success" && <h1 className="font-semibold text-2xl sm:text-3xl text-center py-2 text-green-500">Registered</h1>}
      {message==="error" && <h1 className="font-semibold text-2xl sm:text-3xl text-center py-2 text-red-500">Registration Failed</h1>}

        <h1 className="font-semibold text-2xl sm:text-3xl text-center py-2">Register</h1>
        <hr className="mt-3" />
        <div className="mt-5">
          <Formik
          initialValues={{username:"", password:"", name:""}}
          validationSchema={userRegistrationSchema}
          onSubmit={(values, action)=>{
            registerRequest(values);
            action.resetForm();
          }}
          >
            <Form>
            <div className="mt-3">
              <label className="block m-1" htmlFor="username">Username</label>
              <Field
              type="text"
              name="username"
             className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" placeholder="Enter username"
              />
              <ErrorMessage
              name="username"
              component="p"
              className="mt-1 text-sm text-red-600"
            />
              
            </div>
            <div className="mt-3">
              <label className="block m-1" htmlFor="name">Name</label>
              <Field
              name="name"
              placeholder="Enter name"
              type="text"
              className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500"
              />
               <ErrorMessage
              name="name"
              component="p"
              className="mt-1 text-sm text-red-600"
            />
              
            
            </div>
            <div className="mt-3">
              <label name="password" className="block m-1" htmlFor="password">Password</label>
              <Field
              name="password"
              placeholder="Enter password"
              type="password"
              className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500"
              />
               <ErrorMessage
              name="password"
              component="p"
              className="mt-1 text-sm text-red-600"
            />
            </div>

            <div className="mt-6 mb-5">
              <input className="w-full px-2 py-1 border-2 font-semibold rounded-md bg-green-800 text-white focus:outline-none hover:text-white hover:bg-black cursor-pointer" type="Submit" />
            </div>
          </Form>
          </Formik>
        </div>
      </div>
    </div>
  )
}

export default Register