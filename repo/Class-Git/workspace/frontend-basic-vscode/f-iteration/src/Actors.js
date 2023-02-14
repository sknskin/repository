import { useState } from 'react';


const Actors = (props) => {

    // const [actors, setActors] = useState(["송강호", "장동건", "황정민", "류준열", "김고은"]);    
    // const actorsLi = actors.map( (actor, idx) => <li key={idx}>{actor}</li> );

    const [actors, setActors] = useState([
        { id: 1, name: "송강호" }, 
        { id: 2, name: "장동건" }, 
        { id: 3, name: "황정민" }, 
        { id: 4, name: "류준열" }, 
        { id: 5, name: "김고은" }
    ]);    
    const actorsLi = actors.map( (actor) => <li key={actor.id}>{actor.name}</li> );

    return (
        <>
            <h3>좋아하는 영화 배우</h3>
            <ul>
                { actorsLi }
            </ul>
        </>
    );

};

export default Actors;