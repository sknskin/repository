
import './TodoTemplate.scss'

// const TodoTemplate = (props) => {
//     const { children } = props;
const TodoTemplate = ({ children }) => {

    return (
        <div className="TodoTemplate">
            <div className="app-title">일정 관리</div>
            <div className="content">
                { children }
            </div>
        </div>
    );

};

export default TodoTemplate;