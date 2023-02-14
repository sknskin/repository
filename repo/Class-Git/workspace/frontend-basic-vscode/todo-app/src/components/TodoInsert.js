import { useState } from 'react';

import "./TodoInsert.scss";
import { MdAdd } from 'react-icons/md';

const TodoInsert = ({ onInsert }) => {

    const [todoText, setTodoText] = useState("");

    return (
        <form className="TodoInsert">
            <input type="text"
                   value={ todoText }
                   onChange={ 
                        (e) => {
                            setTodoText(e.target.value);
                        }
                   }                  
            ></input>
            <button type="submit"
                    onClick={ 
                        (e) => { 
                            onInsert(todoText); 
                            setTodoText("");
                            e.preventDefault();
                        } 
                    }
            >
                <MdAdd />
            </button>
        </form>
    );

};

export default TodoInsert;