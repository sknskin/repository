import { useState } from 'react';

const FunctionComponent2 = () => {

    const [cnt, setCnt] = useState(0);

    return (
        <div>
            <h2>{ cnt }</h2>
                <button onClick={ () => {
                    // this.state.cnt++; // state를 직접 변경하는 것은 허용되지 않습니다.
                    setCnt(cnt + 1);
                } }>+</button>
                <button onClick={ () => {
                    setCnt(cnt - 1);
                } }>-</button>
        </div>
    );
}

export default FunctionComponent2;