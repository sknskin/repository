import { Link, Route, Routes } from 'react-router-dom';
import './App.css';

import Home from "./Home";
import About from "./About";
import Profile from "./Profile";
import Search from "./Search";

function App() {
  return (
    <div style={{padding:20}}>

      [<Link to="/">Home</Link>]
      [<Link to="/about">About</Link>]
      [<Link to="/profile/johndoe">John Doe's Profile</Link>]
      [<Link to="/profile/janedoe">Jane Doe's Profile</Link>]
      [<Link to="/search?keyword=react">Search React</Link>]
      <hr />
      <Routes>
        <Route path="/" element={ <Home /> }></Route>
        <Route path="/about" element={ <About /> }></Route>
        <Route path="/profile/:username" element={ <Profile /> }></Route>
        <Route path="/search" element={ <Search /> }></Route>
      </Routes>
    </div>
  );
}

export default App;
