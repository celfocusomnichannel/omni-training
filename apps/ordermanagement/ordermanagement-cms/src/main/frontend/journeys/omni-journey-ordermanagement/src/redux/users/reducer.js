import { SET_USERS, ADD_USER, UPDATE_USER, DELETE_USER } from './actionTypes';

export default (users = [], action) => {
    switch (action.type) {
        case SET_USERS:
            return action.users;
        case ADD_USER:
            return [...users, action.user];
        case UPDATE_USER:
            return users.map((user) => (user.id === action.user.id ? action.user : user));
        case DELETE_USER:
            return users.filter((user) => user.id !== action.id);
        default:
            return users;
    }
};
