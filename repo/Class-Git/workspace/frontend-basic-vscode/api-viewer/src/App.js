import './App.css';
import HttpRequestDemo from './components/HttpRequestDemo';
import NewsApiDemo from './components/NewsApiDemo';
import NewsCategories2 from './components/NewsCategories2';
import NewsCategories from './components/NewsCategories';
import NewsList from './components/NewsList';
import { useCallback, useState } from 'react';
import NewsPage from './components/NewsPage';
import { Route, Routes } from 'react-router-dom';

function App() {

  const [category, setCategory] = useState('all');
  const onSelectCategory = useCallback(
    (category) => setCategory(category),
    []
  )

  return (
    <div>
      {/* <HttpRequestDemo /> */}
      {/* <NewsApiDemo /> */}
      {/* <NewsCategories2 /> */}
      {/* 
      <NewsCategories category={ category } onSelectCategory={ onSelectCategory } />
      <NewsList category={ category } /> 
      */}
      <Routes>
        <Route path="/" element={ <NewsPage /> } />
        <Route path="/:category" element={ <NewsPage /> } />
      </Routes>
    </div>
  );
}

export default App;
