import { SET_MESSAGE } from '../actions';

const initialState = {
    message: 'Initial Message'
};

export default function reducerOne(state, action) {
    if (state === undefined) return initialState;

    switch (action.type) {
        case SET_MESSAGE:
            return { ...state, message: action.message };

        default:
            return state;
    }
}
