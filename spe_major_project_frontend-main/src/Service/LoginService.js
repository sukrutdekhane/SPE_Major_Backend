
import axios from "axios";

const login = async (loginCredentials) => {
    const response = await axios.post(`http://localhost:8081/login`, loginCredentials)
    return response.data
    
}

export default {login};