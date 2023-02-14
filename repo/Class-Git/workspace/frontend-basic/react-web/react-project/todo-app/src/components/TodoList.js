import './TodoList.scss';
import TodoListItem from './TodoListItem';

// const TodoList = (props) => {
//     const { todos } = props;
const TodoList = ({ todos, onToggle, onDelete }) => {

    return (
        <div className="TodoList">
            {
                todos.map( (todo) => {
                    return <TodoListItem key={todo.id} 
                                         todo={ todo } 
                                         onToggle={ onToggle }
                                         onDelete={ onDelete } />
                })                
            }
        </div>
    );

};

export default TodoList;