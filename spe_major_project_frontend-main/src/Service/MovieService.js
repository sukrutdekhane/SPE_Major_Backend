
import axios from "axios";

const getAllMovies = async () => {
    const response = await axios.get(`http://localhost:8081/get-all-movie`)
    return response.data
    
}

const sendReview = async (movieName,review) => {
    const response = await axios.post(`http://localhost:8081/send-review?movieName=${movieName}&review=${review}`)
    return response.data
    
}

const getAllComments = async (movieName) => {
    console.log(movieName)
    const response = await axios.get(`http://localhost:8081/get-all-review?movieName=${movieName}`)
    console.log(response.data)
    return response.data
    
}

const exportObject = { getAllMovies, sendReview,getAllComments }

export default exportObject
