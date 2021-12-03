import { Config } from '../config';

let httpClientService;

export const setHttpClient = (HttpClient) => (httpClientService = HttpClient);
export const createInstance = () => {
    const config = {
        url: Config.CREATE_INSTANCE,
        method: 'POST'
    };
    return httpClientService.request(config);
};

export const getProducts = () => {
    const config = {
        url: Config.GET_PRODUCTS(),
        method: 'GET'
    };
    return httpClientService.request(config);
};
export const getCategories = () => {
    const config = {
        url: Config.GET_CATEGORIES(),
        method: 'GET'
    };
    return httpClientService.request(config);
};
export const getDeliveryOptions = () => {
    const config = {
        url: Config.GET_DELIVERY_OPTIONS(),
        method: 'GET'
    };
    return httpClientService.request(config);
};

export const readUpdateProcess = (instanceId) => {
    const config = {
        url: Config.READ_INSTANCE(instanceId),
        method: 'GET'
    };
    return httpClientService.request(config);
};
export const selectProducts = (instanceId, productId) => {
    const config = {
        url: Config.SELECT_PRODUCTS(instanceId, productId),
        method: 'PUT'
    };
    return httpClientService.request(config);
};

export const createOrder = (instanceId) => {
    const config = {
        url: Config.CREATE_ORDER(instanceId),
        method: 'POST'
    };
    return httpClientService.request(config);
};
export const updateCustomerInfo = (instanceId, customerInformation) => {
    const config = {
        url: Config.UPDATE_CUSTOMER_INFO(instanceId),
        method: 'POST',
        data: customerInformation
    };
    return httpClientService.request(config);
};
export const submitOrder = (instanceId) => {
    const config = {
        url: Config.SUBMIT_ORDER(instanceId),
        method: 'POST'
    };
    return httpClientService.request(config);
};
