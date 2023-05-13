import { React } from "react";
import "./Stylesheet/Login.css";
import "./Stylesheet/Card.css";
import {useNavigate} from 'react-router-dom';
import {
  MDBCard,
  MDBCardImage,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBCol,
} from "mdb-react-ui-kit";

function SingleCard({ movie }) {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/reviewmoviepage',{
      state: { movieName: movie.movieName, movieLink: movie.movieLink }
    });
  }
  return (
    <MDBCol>
        <MDBCard className="h-100">
          <MDBCardImage
            src={`data:image/jpg;base64,${movie.movieImage}`}
            alt={movie.movieName}
            position="top"
            style={{ width: "100%", height: "23rem", objectFit: "cover" }}
            onClick={handleClick}
          />
          <MDBCardBody>
            <MDBCardTitle>{movie.movieName}</MDBCardTitle>
            <MDBCardText>
              <strong>Movie Type: </strong>
              {movie.movieType}
              <br />
              <strong>Actors: </strong>
              {movie.actors.map((actor) => actor.actorName).join(", ")}
              <br />
              <strong>Directors: </strong>
              {movie.directors
                .map((director) => director.directorName)
                .join(", ")}
            </MDBCardText>
          </MDBCardBody>
        </MDBCard>
    </MDBCol>
  );
}

export default SingleCard;
