import { Component } from 'react'

class ClassComponent2 extends Component {

    constructor(props) {
        super(props);
        this.state = {
            cnt: 0
        }
    }
    render() {
       const { cnt } = this.state;
        return (
            <div>
                <h2>{ cnt }</h2>
                <button onClick={ () => {
                    // this.state.cnt++; // state를 직접 변경하는 것은 허용되지 않습니다.
                    this.setState({cnt: cnt + 1})
                } }>+</button>
                <button onClick={ () => {
                    this.setState({cnt: cnt - 1})
                } }>-</button>
            </div>
        );
    }

}

export default ClassComponent2