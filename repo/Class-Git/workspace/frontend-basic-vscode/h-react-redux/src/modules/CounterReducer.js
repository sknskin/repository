import { createAction, handleActions } from 'redux-actions';

const INCREASE = 'CounterReducer/INCREASE';
const DECREASE = 'CounterReducer/DECREASE';

// export const increase = () => ({ type: INCREASE });
// export const decrease = () => ({ type: DECREASE });

export const increase = createAction(INCREASE);
export const decrease = createAction(DECREASE);

const initialState = {
    number: 0
}

// const CounterReducer = (state = initialState, action) => {    
//     switch(action.type) {
//         case INCREASE: return { ...state, number: state.number + 1 }
//         case DECREASE: return { ...state, number: state.number - 1 }
//         default: return state;
//     }
// }

const CounterReducer = handleActions(
    {
        [INCREASE]: (state) => ({ number: state.number + 1 }),
        [DECREASE]: (state) => ({ number: state.number - 1 })
    },
    initialState
);

export default CounterReducer;