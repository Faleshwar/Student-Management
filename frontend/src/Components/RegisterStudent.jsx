
import * as Yup from 'yup';
import { ErrorMessage, Field, Form, Formik } from 'formik';
import axios from 'axios';
import URL from '../Urls/url';
import { useState } from 'react';

const RegisterStudent = () => {


  const token = localStorage.getItem("token");
  const [message, setMessage] = useState("");

  
  const registerRequest = async (values, action)=>{
  
    const payload = {"name":values.name, "phoneNo":values.phone, "email":values.email, "address":{
      "country":values.country, "city":values.city, "state":values.state, "pincode":values.pincode
    }}
    try{

      const res = await axios.post(URL+"/student/add", payload, {headers:{
        "Authorization":`Bearer ${token}`,
        "Content-Type":"application/json"
      }})

      if(res.data){
        console.log(res.data);
        setMessage(res.data.status)
      }

    }catch(err){
      console.log(err);
      setMessage("error")
    }
    action.resetForm();
  }

 
  const registerStudent= Yup.object({
    name:Yup.string().required("Name is required"),
    email:Yup.string().email().required("Email is required"),
    phone:Yup.string().required("Phone number is required"),
    country:Yup.string().required("Country is required"),
    state:Yup.string().required("State is required"),
    city:Yup.string().required("City is required"),
    pincode:Yup.string().required("Pincode is required")
  });

  return (
    <div className="block md:flex justify-center items-start bg-transparent mb-20">
      <div className="w-full md:w-1/2 mt-10 py-5 px-10 rounded-md shadow-lg bg-slate-50">
      
      {message==="success" && <h1 className="font-semibold text-2xl sm:text-3xl text-center text-green-400 py-2">Registered</h1>}
        {message ==="error" && <h1 className="font-semibold text-2xl sm:text-3xl text-center text-red-400 py-2">Registration Failed</h1>}
        <h1 className="font-semibold text-2xl sm:text-3xl text-center py-2">Register</h1>
        <hr className="mt-3" />
        <div className="mt-5">
         
            <Formik
            
            initialValues={{name:"", email:"", phone:"", country:"", state:"", city:"", pincode:""}}
            validationSchema={registerStudent}
            onSubmit={registerRequest}
          
             >
              <Form>
            <div className="mt-3">
              <label className="block m-1" htmlFor="name">Name</label>
             
              <Field
              name="name"
              type="text"
              className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
              placeholder="Enter student name"

              />
              <ErrorMessage
              component="p"
              name='name'
              className='my-1 text-sm text-red-600'
              />
            </div>

            <div className="mt-3">
              <label className="block m-1" htmlFor="email">Email</label>
              <Field
              name="email"
              className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
              placeholder="Enter email"
              type="email"/>
              <ErrorMessage
              name='email'
              className="my-1 text-sm text-red-600"
              component="p"
              />
            </div>

            <div className="mt-3">
              <label className="block m-1" htmlFor="name">Phone Number</label>
              <Field
              className="w-full px-2 py-1 border-2 appearance-none rounded-md focus:outline-none focus:border-green-500"
              placeholder="Enter phone number" 
               name='phone'
              />
              <ErrorMessage
             name='phone'
              component="p"
              type="tel"
               className="my-1 text-sm text-red-600"
              />
              
            </div>
            
            <div className="mt-3">
              <label className="block m-1" htmlFor="country">Select Country</label>
              <Field
            name="country"
            className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
            placeholder="Enter country"
            />
            <ErrorMessage
            name='country'
            component="p"
            className='my-1 text-sm text-red-600'
            />
            </div>
            <div className="mt-3">
              <label className="block m-1" htmlFor="state">Select State</label>
              <Field
            name="state"
            className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
            placeholder="Enter city"
            />
            <ErrorMessage
            name='state'
            component="p"
            className='my-1 text-sm text-red-600'
            />
            </div>

            
            <div className="mt-3">
              <label className="block m-1" htmlFor="city">Select City</label>
        

            <Field
            name="city"
            className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
            placeholder="Enter city"
            />
            <ErrorMessage
            name='city'
            component="p"
            className='my-1 text-sm text-red-600'
            />

            </div>

            <div>
              <label className="block m-1" htmlFor="pincode">Pincode</label>
              <Field
            name="pincode"
            className="w-full px-2 py-1 border-2 rounded-md focus:outline-none focus:border-green-500" 
            placeholder="Enter pincode"
            />
            <ErrorMessage
            name='pincode'
            component="p"
            className='my-1 text-sm text-red-600'
            />
            </div>

            <div className="mt-6 mb-5">

              <input className="w-full px-2 py-1 border-2 font-semibold rounded-md bg-green-800 text-white focus:outline-none hover:text-white hover:bg-black cursor-pointer" type="Submit" />
            </div></Form>
              
            </Formik>
        </div>
      </div>
    </div>
  )


}



export default RegisterStudent;