import { useState } from 'react';

const FunctionEventDemo = () => {

    const [username, setUsername] = useState('');
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        if (e.target.name === 'username') {
            setUsername(e.target.value);
            // console.log(username);
        } else {
            setMessage(e.target.value);
            // console.log(message);
        }
    }
    const clickHandler = (e) => {
        console.log(`[${username}]: [${message}]`);
        // document.querySelector('input[name=username]').value = '';
        setUsername('');
        setMessage('');
    }

    return (
        <>
            <input type="text"
                   name="username"
                   value={username}
                   onChange={handleChange}
            ></input>

            <input type="text"
                   name="message"
                   value={message}
                   onChange={handleChange}
            ></input>

            <button onClick={ clickHandler }
            >전송</button>
        </>
    );

};

export default FunctionEventDemo;