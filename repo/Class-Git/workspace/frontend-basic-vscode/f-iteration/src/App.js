import logo from './logo.svg';
import './App.css';
import Actors from './Actors';
import Actors2 from './Actors2';

function App() {
  return (
    <div style={ { marginLeft: 20 } }>
      <Actors />
      <hr />
      <Actors2 />
    </div>
  );
}

export default App;
