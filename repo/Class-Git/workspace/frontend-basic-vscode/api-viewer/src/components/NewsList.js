import { useEffect, useState } from 'react';
import styled from 'styled-components';
import NewsItem from './NewsItem';
import axios from 'axios';

const NewsListBlock = styled.div`
    box-sizing: border-box;
    padding-bottom: 3rem;
    width: 768px;
    margin: 0 auto;
    margin-top: 2rem;
    @media screen and (max-width: 768px) {
        width: 100%;
        padding-left: 1rem;
        padding-right: 1rem;
    }
`;

const NewsList = (props) => {

    const { category } = props;
    const [articles, setArticles] = useState(null);

    // useEffect : mount(초기화), update(상태변화) 이벤트 처리기 등록
    useEffect( () => {
            const loadNews = async (e) => {
                const countryQs = "country=kr";
                const apiKeyQs = "&apiKey=db4defe7779a4b8c9d3d65b8ce37798e";
                const categoryQs = category === 'all' ? '' : `&category=${category}`;
                const url = `https://newsapi.org/v2/top-headlines?${countryQs}${categoryQs}${apiKeyQs}`;
                const response = await axios.get(url)
                
                setArticles(response.data.articles);
            }
            loadNews();
        }, [category]
    );

    if (!articles) {
        return;
    }

    return (
        <NewsListBlock>
            {
                articles.map( (article) => {
                    return (<NewsItem key={article.url} article={article} />);
                })
            }
        </NewsListBlock>
    );

};

export default NewsList;