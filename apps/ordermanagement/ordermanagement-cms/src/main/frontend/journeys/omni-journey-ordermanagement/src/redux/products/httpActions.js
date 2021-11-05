import * as api from '../../api';
import * as actions from './dataActions.js';

export const initializeUsersHttpClientService = () => (dispatch, getState) => {
    api.setHttpClient(getState().journey.services.HttpClient);
};

export const createInstance = () => async (dispatch) => {
    try {
        const { data } = await api.createInstance();
        dispatch(actions.createInstance(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const getProducts = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.getProducts(instanceId);
        dispatch(actions.getProducts(data));
    } catch (error) {
        console.log(error.message);
    }
};
export const getCategories = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.getCategories(instanceId);
        dispatch(actions.getCategories(data));
    } catch (error) {
        console.log(error.message);
    }
};
export const getDeliveryOptions = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.getDeliveryOptions(instanceId);
        dispatch(actions.getDeliveryOptions(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const readUpdateProcess = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.readUpdateProcess(instanceId);
        dispatch(actions.readUpdateProcess(data));
    } catch (error) {
        console.log(error.message);
    }
};
export const selectProducts = (instanceId, productId) => async (dispatch) => {
    try {
        const { data } = await api.selectProducts(instanceId, productId);
        dispatch(actions.selectProducts(data));
    } catch (error) {
        console.log(error.message);
    }
};

export const createOrder = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.createOrder(instanceId);
        dispatch(actions.createOrder(data));
    } catch (error) {
        console.log(error.message);
    }
};
export const updateCustomerInfo = (instanceId, customerInformation) => async (dispatch) => {
    try {
        const { data } = await api.updateCustomerInfo(instanceId, customerInformation);
        dispatch(actions.updateCustomerInfo(data));
    } catch (error) {
        console.log(error.message);
    }
};
export const submitOrder = (instanceId) => async (dispatch) => {
    try {
        const { data } = await api.submitOrder(instanceId);
        dispatch(actions.submitOrder(data));
    } catch (error) {
        console.log(error.message);
    }
};
