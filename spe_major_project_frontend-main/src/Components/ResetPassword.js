import { React, useState } from "react";
import ResetPassword from "../Service/ResetPassword";
import { useNavigate,useLocation } from "react-router-dom";
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
  const [newpassword, setNewPassword] = useState("");
  const navigate = useNavigate();
  let data = useLocation();
  const {mobileNumber} = { ...data.state };

  const handlePasswordChange = (e) => {
    setNewPassword(e.target.value);
  };

  const resetPassword = async (mobileNumber,newpassword) =>{
    try {
      const flag = await ResetPassword.passwordReset(mobileNumber,newpassword);
      if (flag) {
        
        alert("Password change Successfully!");
        navigate("/login");
      } else {
        alert("Error occured, please try again!");
      }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  }

  const changePassword =(e) =>{
    e.preventDefault();
    resetPassword(mobileNumber,newpassword);
    setNewPassword("");

  }

  return (
    <MDBContainer fluid>
      <MDBRow className="d-flex justify-content-center align-items-center h-100">
        <MDBCol col="12">
          <MDBCard
            className="bg-dark text-white my-5 mx-auto"
            style={{ borderRadius: "1rem", maxWidth: "400px" }}
          >
            <MDBCardBody className="p-5 d-flex flex-column align-items-center mx-auto w-100">
                  <h2 className="fw-bold mb-2 text-uppercase">Reset Password</h2>
                  <p className="text-white-50 mb-5">
                    Enter New Password!
                  </p>

                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    labelClass="text-white"
                    label="Password"
                    id="formControlLg"
                    type="password"
                    size="lg"
                    autoComplete="off"
                    onChange={handlePasswordChange}
                    style={{ color: "white" }}
                    required  
                  />
                  <MDBBtn outline className="mx-2 px-5" color="white" size="lg" onClick={changePassword}>
                    Change Password
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
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}

export default ForgetPassword;
