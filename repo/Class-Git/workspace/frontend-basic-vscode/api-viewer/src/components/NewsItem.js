import styled from "styled-components";
// .css
// .scss ( or .sass )
// css-in-js : styled-components

const NewsItemBlock = styled.div`
    display: flex;

    .thumbnail {
        margin-right: 1rem;
        img {
            display: block;
            width: 160px;
            height: 100px;
            object-fit: cover;
        }
    }
    .contents {
        h2 {
            margin: 0;
            a {
            color: black;
            }
        }
        p {
            margin: 0;
            line-height: 1.5;
            margin-top: 0.5rem;
            white-space: normal;
        }
    }
    & + & {
        margin-top: 3rem;
    }
`;

const NewsItem = (props) => {
    const { article } = props;
    const { url, urlToImage, title, description } = article;

    return (
        <NewsItemBlock>
            <div className="thumbnail">
                <a href={ url }>
                    <img src={ urlToImage } alt="news thumbnail" />
                </a>
            </div>
            <div className="contents">
                <h2>
                    <a href={ url }>{ title }</a>
                </h2>
                <p>{ description }</p>
            </div>
        </NewsItemBlock>
    );

};

export default NewsItem;