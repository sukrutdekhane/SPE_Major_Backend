import { React, useState, useEffect } from "react";
import NavBar from "./NavBar";
import SingleCard from "./SingleCard";
import movieService from "../Service/MovieService";
import { MDBRow } from "mdb-react-ui-kit";
import { useLocation } from "react-router-dom";


function MoviesPage() {
  const [allMovies, setAllMovies] = useState([]);


  const getListOfMovieshandler = async () => {
    try {
      if (allMovies.length > 0) {
        return allMovies;
      }
      const movies = await movieService.getAllMovies();
      if (movies != null) {
        setAllMovies(movies);
      } else {
        alert("Error occured, please try again!");
      }

    } catch (exception) {
      alert("Error occured, please try again!");
    }
  };

  useEffect(() => {
    getListOfMovieshandler();
  }, []);

  return (
    <div>
      <NavBar />
      <MDBRow
        className="row-cols-1 row-cols-md-4 g-4"
        style={{ margin: "15px" }}
      >
        {allMovies.map((movie) => (
          <SingleCard movie={movie}/>
        ))}
      </MDBRow>
    </div>
  );
}

export default MoviesPage;
