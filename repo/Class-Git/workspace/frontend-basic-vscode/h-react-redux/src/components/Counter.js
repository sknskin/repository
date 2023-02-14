
// const Counter = (props) => {
//     const { number, onIncrease, onDecrease } = props;
const Counter = ({ number, onIncrease, onDecrease }) => {

    return (
        <div>
            <button onClick={ onIncrease }>+</button>
            <button onClick={ onDecrease }>-</button>
            <hr />
            <h1>{ number }</h1>

        </div>
    );
};

export default Counter;