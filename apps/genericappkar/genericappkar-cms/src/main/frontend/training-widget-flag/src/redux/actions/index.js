export const SET_EXAMPLE = 'SET_EXAMPLE';

export function example(value) {
    return { type: SET_EXAMPLE, payload: value };
}
