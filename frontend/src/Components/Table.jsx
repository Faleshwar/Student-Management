import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import URL from "../Urls/url";


const Table = () => {

    const navigate = useNavigate();

    const [students, setStudents] = useState([]);
    const token = localStorage.getItem("token");
    const [deleteMessage, toggleDeleteMessage] = useState(false);


    const deleteHandler = async (id)=>{
        try{
            const res = await axios.delete(URL+"/student/delete", {headers:{
                "Authorization":`Bearer ${token}`,
            },
        params:{
            "studentId":id
        }})

        if(res.data){
            console.log(res.data);
            toggleDeleteMessage(true);
            getList();
        }

        }catch(err){
            console.log(err)
        }
    }

    const getList = async ()=>{
        try{
            const res = await axios.get(URL+"/student/all", {headers:{Authorization:`Bearer ${token}`, "Content-Type":"application/json"}});

            if(res.data){
                setStudents(res.data);
            }

        }catch(err){
            console.log(err)
        }
    }

    const editHandler = (id)=>{
        navigate(`/edit/${id}`)
    }

    useEffect(()=>{

        if(token){
            getList();
        }

    },[])

    console.log(students)

  return (
    <div className='flex justify-center'>
        <div className=''>
            {deleteMessage && <h1 className='text-center mb-3 text-3xl font-semibold text-red-500'>Deleted</h1>}
            <h1 className='text-center text-3xl font-semibold'>Student Table</h1>
            <table className='table mt-5'>
                <thead className='bg-black text-white gap-x-10'>
                    <tr>
                        <th className='px-5 py-3'>Name</th>
                        <th className='px-5 py-3'>Phone Number</th>
                        <th className='px-5 py-3'>Email</th>
                        <th className='px-5 py-3'>Country</th>
                        <th className='px-5 py-3'>State</th> 
                        <th className='px-5 py-3'>City</th>
                        <th className='px-5 py-3'>Pincode</th>
                        <th className='px-5 py-3'>Action</th>
                    </tr>
                </thead>
                <tbody className='bg-white'>
                    {students && students.map((st, i)=><tr key={i}>
                        <td className='px-5 py-3'>{st.name}</td>
                        <td className='px-5 py-3'>{st.phoneNo}</td>
                        <td className='px-5 py-3'>{st.email}</td>
                        <td className='px-5 py-3'>{st.address.country}</td>
                        <td className='px-5 py-3'>{st.address.state}</td>
                        <td className='px-5 py-3'>{st.address.city}</td>
                        <td className='px-5 py-3'>{st.address.pincode}</td>
                        <td className='px-5 py-3 '>
                            <div className='flex gap-x-2'>
                                <button onClick={()=>editHandler(st.studentId)} className='border-2 px-3 py-1 rounded-md text-white border-green-500 bg-green-500'>Edit</button>
                                <button onClick={()=>deleteHandler(st.studentId)} className='border-2 px-3 py-1 rounded-md text-white border-red-500 bg-red-500'>Delete</button>
                            </div>
                        </td>

                    </tr>)}
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default Table