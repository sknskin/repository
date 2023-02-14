import { Component } from 'react';
import PropTypes from 'prop-types';

class ClassComponent extends Component {

    static defaultProps = {
        name : "Anonymous",
        email: "anonymous@example.com",
        age: 0
    }

    static propTypes = {
        name: PropTypes.string,
        email: PropTypes.string,
        age: PropTypes.number
    }

    render() {
        const { name, email, age } = this.props;
        return (
            <div>
                <h1>This is class component</h1>
                <h2>[{this.props.name}][{this.props.email}][{this.props.age}]</h2>
                <h2>[{name}][{email}][{age}]</h2>
            </div>
        );
    }

}

export default ClassComponent