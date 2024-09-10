import axios from 'axios';
import { ErrorMessage, Field, Form, Formik } from 'formik';
import React, { useState } from 'react'
import * as Yup from 'yup';
import URL from '../Urls/url';

const loginSchema = Yup.object({
    username:Yup.string().required("Username is required"),
    password:Yup.string().required("Password is required")
  });

 
  

const Login = () => {

  const [error, setError] = useState("");

  const loginReqest = async (data)=>{
    try{
      const res = await axios.post(URL+"/login", data);
      if(res.data){
        localStorage.setItem("token", res.data.token);
        window.location.reload();
      }

    }catch(err){
      console.log(err.response);
      setError("error")
      
    }
  }


  return (
    <div className="block md:flex justify-center items-start bg-transparent mb-20">
      <div className="w-full md:w-1/3 mt-10 py-5 px-10 rounded-md shadow-lg bg-slate-50">
        {error==="error" && <h1 className="font-semibold text-xl sm:text-3xl text-center text-red-500 py-2">Login Failed</h1>}
        <h1 className="font-semibold text-2xl sm:text-3xl text-center py-2">Login</h1>
        <hr className="mt-3" />
        <div className="mt-5">
          <Formik
          initialValues={{username:"", password:""}}
          validationSchema={loginSchema}
          onSubmit={(values, action)=>{
            loginReqest(values);
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
              className="my-1 text-sm text-red-600"
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
              className="py-1 text-sm text-red-600"
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

export default Login