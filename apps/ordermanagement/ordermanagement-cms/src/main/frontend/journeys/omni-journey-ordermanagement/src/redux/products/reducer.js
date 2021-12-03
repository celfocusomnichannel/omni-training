import {
    CREATE_INSTANCE,
    CREATE_ORDER,
    GET_CATEGORIES,
    GET_DELIVERY_OPTIONS,
    GET_PRODUCTS,
    READ_UPDATE_PROCESS,
    SELECT_PRODUCTS,
    SUBMIT_ORDER,
    UPDATE_CUSTOMER_INFO,
    SET_INSTANCE_ID,
    SET_PREFERENCES,
    RESET
} from './actionTypes';

const INITIAL_STATE = {
    listProducts: [],
    categories: '',
    instance: {},
    deliveryOptions: [],
    instanceId: undefined,
    preferences: undefined,
    dependencies: {
        fetchedListProducts: false,
        fetchedCategories: false,
        fetchedInitialInstanceId: false,
        fetchedDeliveryOptions: false,
        fetchedInstance: false,
        fetchedPreferences: false
    }
};

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case SET_INSTANCE_ID:
            return { ...state, instanceId: action.instanceId, dependencies: { ...state.dependencies, fetchedInitialInstanceId: true } };
        case CREATE_INSTANCE:
            return { ...state, instance: action.instance, dependencies: { ...state.dependencies, fetchedInstance: true } };
        case CREATE_ORDER:
            return { ...state, instance: action.instance };
        case GET_CATEGORIES:
            return { ...state, categories: action.categories, dependencies: { ...state.dependencies, fetchedCategories: true } };
        case GET_DELIVERY_OPTIONS:
            return { ...state, deliveryOptions: action.deliveryOptions, dependencies: { ...state.dependencies, fetchedDeliveryOptions: true } };
        case GET_PRODUCTS:
            return { ...state, listProducts: action.products, dependencies: { ...state.dependencies, fetchedListProducts: true } };
        case READ_UPDATE_PROCESS:
            return { ...state, instance: action.instance, dependencies: { ...state.dependencies, fetchedInstance: true } };
        case SELECT_PRODUCTS:
            return { ...state, instance: action.instance };
        case SUBMIT_ORDER:
            return { ...state, instance: action.instance };
        case UPDATE_CUSTOMER_INFO:
            return { ...state, instance: action.instance };
        case SET_PREFERENCES:
            return { ...state, preferences: action.preferences, dependencies: { ...state.dependencies, fetchedPreferences: true } };
        case RESET:
            return INITIAL_STATE;
        default:
            return state;
    }
};
