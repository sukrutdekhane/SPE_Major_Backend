import "./App.css";
import LoginPage from "./Components/Login.js";
import SignUpPage from "./Components/Signup";
import MoviesPage from "./Components/MoviesPage";
import MovieReviewPage from "./Components/MovieReviewPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ForgetPassword from "./Components/ForgetPassword";
import ResetPassword from "./Components/ResetPassword";

function App() {
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/signup" element={<SignUpPage/>} />
        <Route path="/moviespage" element={<MoviesPage />}/> 
        <Route path="/reviewmoviepage" element={<MovieReviewPage />}/> 
        <Route path="/forgetPassword" element={<ForgetPassword />}/> 
        <Route path="/resetPassword" element={<ResetPassword />}/> 
      </Routes>
    </BrowserRouter>
  );
}

export default App;
