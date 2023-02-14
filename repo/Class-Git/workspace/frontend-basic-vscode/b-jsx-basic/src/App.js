import logo from './logo.svg';
import { Fragment } from 'react'
import './App.css';

function App() {

  const name = "John Doe";
  const style = {
    backgroundColor: 'green',
    color: 'red'
  }

  return (
    <>
      <h1 style={ style }>Hello, React World  !!!!</h1>
      <h1 className='my-style'>My First Component  !!!!</h1>
      <h1>Hello, { name }</h1>
      <input type="text" />
      <div
        // 여기서는 주석을 사용할 수 있습니다.
      >
        {/* This is jsx comment !!! */}
      </div>
    </>
  );
}

export default App;
