import { Component } from 'react';

class ClassEventDemo extends Component {

    state = {
        message: ''
    }

    render() {
        return (
            <div>
                <input value={ this.state.message } 
                       onChange={ (e) => {
                    this.setState({message: e.target.value});
                } }></input>
                <button onClick={ (e) => {
                    console.log(this.state.message);
                    this.setState({message: ''});
                } }>전송</button>
            </div>
        );
    }

}

export default ClassEventDemo;