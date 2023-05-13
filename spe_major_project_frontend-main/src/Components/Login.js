import { React, useEffect, useState } from "react";
import "./Stylesheet/Login.css";
import { NavLink } from "react-router-dom";
import loginService from "../Service/LoginService";
import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBInput,
  MDBIcon,
} from "mdb-react-ui-kit";
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const [user, setUser] = useState(null); 
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();



  const loginHandler = async (loginCredentials) => {
    try {
      const userObject = await loginService.login(loginCredentials);
      if (userObject) {
        setUser(userObject);
        alert("Log in successfully");
        navigate("/moviespage");
        
      } else {
        alert("Log in failed, check username and password entered");
      }
    } catch (exception) {
      alert("Log in failed, check username and password entered");
    }
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };


  const handleSubmit = (e) => {
    e.preventDefault();

    const loginCredentials = {
      email,
      password,
    };

    loginHandler(loginCredentials);

    setEmail("");
    setPassword("");
  };

  return (
    <MDBContainer fluid>
      <MDBRow className="d-flex justify-content-center align-items-center h-100">
        <MDBCol col="12">
          <MDBCard
            className="bg-dark text-white my-5 mx-auto"
            style={{ borderRadius: "1rem", maxWidth: "400px" }}
          >
            <MDBCardBody className="p-5 d-flex flex-column align-items-center mx-auto w-100">
              <h2 className="fw-bold mb-2 text-uppercase">Login</h2>
              <p className="text-white-50 mb-5">
                Please enter your login and password!
              </p>

              <MDBInput
                wrapperClass="mb-4 mx-5 w-100"
                labelClass="text-white"
                label="Email address"
                id="formControlLg"
                type="email"
                size="lg"
                autoComplete="off"
                style={{ color: "white" }}
                onChange={handleEmailChange}
              />
              <MDBInput
                wrapperClass="mb-4 mx-5 w-100"
                labelClass="text-white"
                label="Password"
                id="formControlLg"
                type="password"
                size="lg"
                autoComplete="off"
                style={{ color: "white" }}
                onChange={handlePasswordChange}
              />

              <p className="small mb-3 pb-lg-2">
                <NavLink to="/forgetPassword">forget Password</NavLink>
              </p>

              <MDBBtn
                outline
                className="mx-2 px-5"
                color="white"
                size="lg"
                onClick={handleSubmit}
              >
                Login
              </MDBBtn>

              <div className="d-flex flex-row mt-3 mb-5">
                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-3"
                  style={{ color: "white" }}
                >
                  <MDBIcon fab icon="facebook-f" size="lg" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-3"
                  style={{ color: "white" }}
                >
                  <MDBIcon fab icon="twitter" size="lg" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-3"
                  style={{ color: "white" }}
                >
                  <MDBIcon fab icon="google" size="lg" />
                </MDBBtn>
              </div>

              <div>
                <p className="mb-0">
                  Don't have an account? <NavLink to="/signup">Sign up</NavLink>
                </p>
              </div>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}

export default LoginPage;
