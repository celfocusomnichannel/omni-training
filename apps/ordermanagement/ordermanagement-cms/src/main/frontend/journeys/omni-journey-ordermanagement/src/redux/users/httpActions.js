import * as api from '../../api';
import { addUser, removeUser, setUsers, updateUser } from './dataActions.js';

export const initializeUsersHttpClientService = () => (dispatch, getState) => {
    api.setHttpClient(getState().journey.services.HttpClient);
};

export const getUsers = () => async (dispatch) => {
    try {
        const { data } = await api.fetchUsers();

        dispatch(setUsers(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const createUser = (user) => async (dispatch) => {
    try {
        const { data } = await api.createUser(user);

        dispatch(addUser(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const patchUser = (id, user) => async (dispatch) => {
    try {
        const { data } = await api.updateUser(id, user);

        dispatch(updateUser(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const deleteUser = (id) => async (dispatch) => {
    try {
        await api.deleteUser(id);

        dispatch(removeUser(id));
    } catch (error) {
        console.log(error.message);
    }
};
