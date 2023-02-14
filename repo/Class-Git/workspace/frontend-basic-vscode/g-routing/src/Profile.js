import { useParams } from "react-router-dom";

const Profile = (props) => {

    const params = useParams();    

    return (
        <div>
            <h1>This is { params.username }'s Profile !!!!!</h1>
        </div>
    );
}

export default Profile;