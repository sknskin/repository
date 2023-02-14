import { useSelector, useDispatch } from 'react-redux';

import Counter from "../components/Counter";
import { increase, decrease } from "../modules/CounterReducer";

const CounterContainer2 = () => {

    const number = useSelector(state => state.number);
    const dispatch = useDispatch();

    const doIncrease = () => dispatch(increase());
    const doDecrease = () => dispatch(decrease());

    return (
        <Counter number={ number } 
                 onIncrease={ doIncrease } 
                 onDecrease={ doDecrease } />
    );

};

export default CounterContainer2;