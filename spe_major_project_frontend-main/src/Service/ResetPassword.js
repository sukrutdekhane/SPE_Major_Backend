import axios from "axios";

const passwordReset = async (phoneNumber,password) => {
    const response = await axios.post(`http://localhost:8081/change-password?phoneNumber=${phoneNumber}&newPassword=${password}`)
    return response.data
    
}

export default {passwordReset};