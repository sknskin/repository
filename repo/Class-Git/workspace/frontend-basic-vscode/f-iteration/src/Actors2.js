import { useState } from 'react';


const Actors2 = (props) => {

    const removeActor = (id) => {
        const newActors = actors.filter( (actor) => actor.id !== id );
        setActors(newActors);
    }
    
    const [actors, setActors] = useState([]);    
    const actorsLi = actors.map( (actor) => <li key={ actor.id }
                                                onDoubleClick={ (e) => removeActor(actor.id) }
                                            >{ actor.name }</li> );

    const [name, setName] = useState("");
    const [nextId, setNextId] = useState(1);

    return (
        <>
            <h3>좋아하는 영화 배우 2</h3>
            <input type="text"
                   value={ name }
                   onChange={ 
                        (e) => {
                            setName(e.target.value);
                        }
                   }
            ></input>
            <button onClick={
                        (e) => {
                            const id = nextId;
                            setNextId(id + 1);
                            const actor = { id: id, name: name };
                            const newActors = actors.concat(actor);
                            setActors(newActors);
                            setName("");
                        }
                    }
            >등록</button>
            <ul>
                { actorsLi }
            </ul>
        </>
    );

};

export default Actors2;