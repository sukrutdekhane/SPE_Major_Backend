import { React, useState } from "react";
import OtpService from "../Service/OtpService";
import { useNavigate } from "react-router-dom";
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

function ForgetPassword() {
  const [showOtpVerification, setShowOtpVerification] = useState(false);
  const [mobileNumber, setMobileNumber] = useState("");
  const [otp, setOtp] = useState("");
  const navigate = useNavigate();
  

  const ForgetPasswordHandler = async (mobileNumber) =>{
    try {
      const flag = await OtpService.otp(mobileNumber);
      if (flag===1) {
        
        alert("OTP send Successfully to your Register Mobile Number!");
      }
       else 
      {
          if(flag===0)alert("Mobile number is empty!");
          else if(flag===2)
          {
            alert("user is not exist please register first");
            navigate("/signup");
          }     
     }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  }

  const otpVerification = async (mobileNumber,otp) =>{
    try {
      const flag = await OtpService.otpVerification(mobileNumber,otp);
      if (flag) {
        
        alert("OTP verified Successfully!");
      } else {
        alert("Error occured, please try again!");
      }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  }

  const handlemobileNumberChange = (e) => {
    setMobileNumber(e.target.value);
  };

  const clickHere = () => {

    ForgetPasswordHandler(mobileNumber);
    setShowOtpVerification(true);
  };

  const handleOtpVerification = () => {
    otpVerification(mobileNumber,otp);
    
    navigate('/resetPassword',{
      state: { mobileNumber:mobileNumber}
    });
    
  };

  const handleOtpInput = (e) => {
     setOtp(e.target.value);
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
                  <h2 className="fw-bold mb-2 text-uppercase">Mobile Number</h2>
                  <p className="text-white-50 mb-5">
                    Enter your Details to change Password!
                  </p>

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
                    required  
                  />
                  <MDBBtn outline className="mx-2 px-5" color="white" size="lg" onClick={clickHere}>
                    Click here
                  </MDBBtn>
                </>
              )}

              {showOtpVerification && (
                <>
                  <h2 className="fw-bold mb-2 text-uppercase">Enter OTP</h2>
                  <p className="text-white-50 mb-5">
                    Please enter your Details!
                  </p>
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Enter OTP"
                    id="formControlLg"
                    type="password"
                    size="lg"
                    autoComplete="off"
                    style={{ color: "white" }}
                    onChange={handleOtpInput}
                    required
                  />

                  <MDBBtn outline className="mx-2 px-5" color="white" size="lg" onClick={handleOtpVerification}>
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

export default ForgetPassword;
