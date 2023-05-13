import { React, useState } from "react";
import "./Stylesheet/Signup.css";
import signUpService from "../Service/SignUpService";
import {useNavigate} from "react-router-dom";
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

function SignUpPage() {
  const [showOtpVerification, setShowOtpVerification] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobileNumber, setMobileNumber] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [otp, setOtp] = useState("");
  const navigate =useNavigate();

  const signUpHandler = async (mobileNumber) => {
    try {
      const flag = await signUpService.signup(mobileNumber);
      if (flag===1) {
        
        alert("OTP send Successfully to your Register Mobile Number!");
      } 
      else 
      {
          if(flag===0) alert("Mobile number is empty!");
          else if(flag===2)
          {
            alert("You already have account with this mobile number");
            navigate("/login"); 
          }
      }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  };

  const registerUserhandler = async (userDetails,otp) => {
    
    try {
      const flag = await signUpService.register(userDetails,otp);
      if (flag===1) {
        alert("OTP verfied Successfully.Your Registration is successful");
        navigate("/login");

      } else {
        if(flag===0) alert("Invalid OTP, please try again!");
        else if(flag===2)alert("You already have account with this mobile number");
      }
    } catch (exception) {
      alert("Error occcured, please try again!");
    }
  };
  


  const handleFirstNameChange = (e) => {
    setFirstName(e.target.value);
  };

  const handleLastNameChange = (e) => {
    setLastName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlemobileNumberChange = (e) => {
    setMobileNumber(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleOTPInput = (e) => {
    setOtp(e.target.value);
  };

  const handleSignUp = (e) => {
    e.preventDefault();

    signUpHandler(mobileNumber);
    setShowOtpVerification(true);
  };

  const handleOtpVerification = (e) => {
    e.preventDefault();

    const userDetails= {
      firstName,
      lastName,
      mobileNumber,
      email,
      password,
    };
    registerUserhandler(userDetails,otp);
    
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
            

              {!showOtpVerification && (
                <>
                  <h2 className="fw-bold mb-2 text-uppercase">Sign Up</h2>
                  <p className="text-white-50 mb-5">Please enter your Details!</p>
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="First Name"
                    id="formControlLg"
                    type="text"
                    size="lg"
                    autoComplete={false}
                    style={{ color: "white" }}
                    onChange={handleFirstNameChange}
                    
                  />
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Last Name"
                    id="formControlLg"
                    type="text"
                    size="lg"
                    autoComplete={false}
                    style={{ color: "white" }}
                    onChange={handleLastNameChange}
                    
                  />
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Email Address"
                    id="formControlLg"
                    type="email"
                    size="lg"
                    autoComplete={false}
                    style={{ color: "white" }}
                    onChange={handleEmailChange}
                    
                  />
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Phone Number"
                    id="formControlLg"
                    type="tel"
                    size="lg"
                    autoComplete="off"
                    style={{ color: "white" }}
                    onChange={handlemobileNumberChange}
                    
                  />
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Password"
                    id="formControlLg"
                    type="password"
                    size="lg"
                    autoComplete={false}
                    style={{ color: "white" }}
                    onChange={handlePasswordChange}
                    
                  />
                  <MDBBtn
                    outline
                    className="mx-2 px-5"
                    color="white"
                    size="lg"
                    onClick={handleSignUp}
                  >
                    Sign Up
                  </MDBBtn>
                </>
              )}

              {showOtpVerification && (
                <>
                  <h2 className="fw-bold mb-2 text-uppercase">Enter OTP</h2>
                  <p className="text-white-50 mb-5">Please enter your Details!</p>
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Enter OTP"
                    id="formControlLg"
                    type="password"
                    size="lg"
                    autoComplete="off"
                    style={{ color: "white" }}
                    onChange={handleOTPInput}
                    required
                  />

                  <MDBBtn
                    outline
                    className="mx-2 px-5"
                    color="white"
                    size="lg"
                    onClick={handleOtpVerification}
                  >
                    Verify OTP
                  </MDBBtn>
                </>
              )}

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
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}

export default SignUpPage;
