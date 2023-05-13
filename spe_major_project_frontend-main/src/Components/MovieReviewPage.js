import { React, useState,useEffect } from "react";
import NavBar from "./NavBar";
import "./Stylesheet/MovieReview.css";
import { useLocation, useNavigate } from "react-router-dom";
import movieService from "../Service/MovieService";
import Comment from "./Comment";

function MoviesReviewPage() {
  let data = useLocation();
  const [review, setReview] = useState("");
  const [allComments, setAllComments] = useState([]);
  const navigate = useNavigate();
  const { movieName, movieLink } = { ...data.state };

  const setReviewHandler = async (movieName, review) => {
    const reviewstatus = await movieService.sendReview(movieName, review);
    try {
      if (reviewstatus == true) {
        alert("your comment saved successfully!!");
        getListOfAllCommentsHandler(movieName);
      } else {
        alert("Error occured, please try again!");
      }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  };

  const getListOfAllCommentsHandler = async (movieName) => {
    try {

      const Comments = await movieService.getAllComments(movieName);
      if (Comments != null) {
        setAllComments(Comments);
      } else {
        alert("Error occured, please try again!");
      }
    } catch (exception) {
      alert("Error occured, please try again!");
    }
  };

  const sendReview = (e) => {
    e.preventDefault();
    setReviewHandler(movieName, review);
    setReview("");
    
  };

  const handleMessageChange = (e) => {
    setReview(e.target.value);
  };



  return (
    <div>
      <NavBar />
      <div className="container-fluid">
        <div className="row" style={{ height: "100vh" }}>
          <div className="col-lg-7 col-md-7 col-sm-7 col-xs-7">
            <div className="d-flex flex-column mb-2">
              <div style={{ margin: "10px" }}>
                <iframe
                  width="100%"
                  height="600"
                  src={movieLink}
                  title="YouTube video player"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                  style={{ borderRadius: "7px" }}
                ></iframe>
              </div>
              <div style={{ width: "500px", height: "50px", margin: "auto" }}>
                <div class="card">
                  <div class="card-body ">
                    <div class="d-flex flex-start w-100">
                      <div class="w-100">
                        <h5>Add a comment</h5>
                        <div class="form-outline">
                          <textarea
                            class="form-control"
                            rows="3"
                            id="review"
                            name="review"
                            value={review}
                            onChange={handleMessageChange}
                            style={{ backgroundColor: "#F1F1F1" }}
                          ></textarea>
                        </div>
                        <div class="d-flex justify-content-between mt-3">
                          <button
                            type="button"
                            class="btn btn-success"
                            onClick={sendReview}
                          >
                            Send
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-lg-5 col-md-5 col-sm-5 col-xs-5" >
              <div className="d-flex flex-column">
                  <div className="mt-2">
                      <text class="btn btn-primary btn-lg btn-block">
                             See All Comments
                      </text>
                  </div>
                  <div className="mt-3">
                        {allComments.map((comment) => (
                            <Comment comment={comment} />
                        ))} 
                  </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MoviesReviewPage;
