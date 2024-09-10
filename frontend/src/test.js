import axios from "axios";


const token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzI1NzIyMjU3LCJleHAiOjE3MjU4MDg2NTd9.lkCmZoYJxWDF6wYWmEqsDzEEIdPbw5MbP10Ns8AN3wVdy2j3Xa2zgqz0KC3QPtlK";
const load = async () =>{
    try{
        const res = await axios.get("http://localhost:8080/student/all", {headers:{"Authorization":`Bearer ${token}`, "Content-Type":"application/json"}});
        
        const data = await res.json();
        console.log(data)

    }catch(err){
        console.error(err)
    }
}

load();