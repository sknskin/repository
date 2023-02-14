import { useLocation, useSearchParams } from "react-router-dom";

const Search = (props) => {

    const location = useLocation();
    const [ searchParams, setSearchParams ] = useSearchParams();

    return (
        <div>
            <h1>This is Search for { location.search } !!!!!</h1>
            <h1>This is Search for { searchParams.get("keyword") } !!!!!</h1>
        </div>
    );
}

export default Search;