import { React} from "react";

function comment({comment}){


    return(
      <div className="bg bg-white shadow-sm my-3 rounded p-3">
        <div>
          <h4 className="text text-center text-primary">{comment.movieName}</h4>
          <hr/>
          <p className="">{comment.review}</p>
        </div>
      </div>
    )
}

export default comment;