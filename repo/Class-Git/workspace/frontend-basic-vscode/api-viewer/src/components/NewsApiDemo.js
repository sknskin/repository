import axios from 'axios';
import { useState } from 'react';

const NewsApiDemo = (props) => {

    const [news, setNews] = useState(null);
    const clickHandler = async (e) => {
        const country = "kr";
        const category = "sports"
        const apiKey = "db4defe7779a4b8c9d3d65b8ce37798e";
        const url = `https://newsapi.org/v2/top-headlines?country=${country}&category=${category}&apiKey=${apiKey}`;
        const response = await axios.get(url);
        setNews(response.data);
    };

    return (
        <div>
            <button onClick={ clickHandler }>뉴스 가져오기</button>
            <hr />
            <div>
                { news ? JSON.stringify(news) : "뉴스 가져오기 버튼을 클릭해 주세요" }
            </div>
        </div>
    );

};

export default NewsApiDemo;