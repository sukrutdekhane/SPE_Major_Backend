import { React } from "react";
import { useNavigate } from "react-router-dom";

function NavBar() {

  const navigate=useNavigate();
  const homehandler= ()=>{
    navigate("/moviespage");
}

const logout = () => {
  window.localStorage.removeItem('sessionUser')
  navigate("/login");
}


  return (
    <div>
      <nav class="navbar navbar-dark bg-dark justify-content-between">
        <a class="navbar-brand" style={{ color: "white" }}>
          Movie Review
        </a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item active">
            <a href="#" class="nav-link" onClick={homehandler}>
              Home 
            </a>
          </li>
        </ul>
          <button className="btn btn-outline-success my-2 my-sm-0" onClick={logout}>Logout</button>
      </nav>
    </div>
  );
}

export default NavBar;
