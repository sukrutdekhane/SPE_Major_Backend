import axios from "axios";


const otp = async (phoneNumber) => {
    const response = await axios.post(`http://localhost:8081/send-otp-for-forgot-password?phoneNumber=${phoneNumber}`)
    return response.data
    
}


const otpVerification = async (phoneNumber,otp) => {
    const response = await axios.post(`http://localhost:8081/otp-verification-for-change-password?phoneNumber=${phoneNumber}&otp=${otp}`)
    return response.data
    
}

const exportObject = { otp, otpVerification }

export default exportObject;
