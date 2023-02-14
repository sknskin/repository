import { useState } from 'react';
import axios from 'axios';

const categories = [
    {
        name: 'all',
        text: '전체보기',
    },
    {
        name: 'business',
        text: '비즈니스',
    },
    {
        name: 'entertainment',
        text: '엔터테인먼트',
    },
    {
        name: 'health',
        text: '건강',
    },
    {
        name: 'science',
        text: '과학',
    },
    {
        name: 'sports',
        text: '스포츠',
    },
    {
        name: 'technology',
        text: '기술',
    },    
];

const NewsCategories = (props) => {

    const [news, setNews] = useState(null);
    const loadNews = (category) => {
        const country = "kr";
        const apiKey = "db4defe7779a4b8c9d3d65b8ce37798e";
        const url = `https://newsapi.org/v2/top-headlines?country=${country}&category=${category}&apiKey=${apiKey}`;
        axios.get(url)
             .then((response) => { setNews(response.data) });
    } 

    return (
        <div>
            <div>
            {
                categories.map( (category) => {
                    return (
                        <button onClick={ (e) => loadNews(category.name) }>{category.text}</button>
                    );
                })
            }
            </div>
            <hr />
            <div>
                <ul>
                {
                    news ? 
                        news.articles.map( (article, idx) => {
                            return (
                                <li key={idx}>
                                    <h3>{ article.title }</h3>
                                    <p>{ article.description }</p>
                                    <hr />
                                </li>
                            )
                        })
                        :                    
                        "카테고리 버튼을 클릭하세요"
                }
                </ul>
            </div>
        </div>
    );
};

export default NewsCategories;