import { SET_EXAMPLE } from '../actions';

const initialState = {
    example: ''
};

export default function reducerOne(state, action) {
    if (state === undefined) return initialState;

    switch (action.type) {
        case SET_EXAMPLE:
            return { ...state, example: action.example };
        default:
            return state;
    }
}
