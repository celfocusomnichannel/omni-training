import {
    CREATE_INSTANCE,
    GET_PRODUCTS,
    GET_CATEGORIES,
    GET_DELIVERY_OPTIONS,
    READ_UPDATE_PROCESS,
    CREATE_ORDER,
    UPDATE_CUSTOMER_INFO,
    SUBMIT_ORDER,
    SELECT_PRODUCTS,
    SET_INSTANCE_ID,
    SET_PREFERENCES
} from './actionTypes';

export const setInstanceId = (instanceId) => ({ type: SET_INSTANCE_ID, instanceId });
export const createInstance = (instance) => ({ type: CREATE_INSTANCE, instance });

export const getProducts = (products) => ({ type: GET_PRODUCTS, products });
export const getCategories = (categories) => ({ type: GET_CATEGORIES, categories });
export const getDeliveryOptions = (deliveryOptions) => ({ type: GET_DELIVERY_OPTIONS, deliveryOptions });

export const readUpdateProcess = (instance) => ({ type: READ_UPDATE_PROCESS, instance });
export const selectProducts = (instance) => ({ type: SELECT_PRODUCTS, instance });

export const createOrder = (instance) => ({ type: CREATE_ORDER, instance });
export const updateCustomerInfo = (instance) => ({ type: UPDATE_CUSTOMER_INFO, instance });
export const submitOrder = () => ({ type: SUBMIT_ORDER });

export const setPreferences = (preferences) => ({ type: SET_PREFERENCES, preferences });
