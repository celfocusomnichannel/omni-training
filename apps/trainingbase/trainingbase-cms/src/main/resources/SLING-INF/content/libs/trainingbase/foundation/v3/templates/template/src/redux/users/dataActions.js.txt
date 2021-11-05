import { SET_USERS, ADD_USER, UPDATE_USER, DELETE_USER } from './actionTypes';

export const setUsers = (users) => ({ type: SET_USERS, users });

export const addUser = (user) => ({ type: ADD_USER, user });

export const updateUser = (user) => ({ type: UPDATE_USER, user });

export const removeUser = (id) => ({ type: DELETE_USER, id });
