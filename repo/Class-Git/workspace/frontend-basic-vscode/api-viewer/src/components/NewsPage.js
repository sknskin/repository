import { useParams } from "react-router-dom";
import NewsCategories from "./NewsCategories";
import NewsList from "./NewsList";

const NewsPage = (props) => {

    const params = useParams();
    // const category = params.category ? params.category : 'all';
    const category = params.category || 'all';

    return (
        <div>
            <NewsCategories />
            <NewsList category={ category } />
        </div>
    );
};

export default NewsPage;