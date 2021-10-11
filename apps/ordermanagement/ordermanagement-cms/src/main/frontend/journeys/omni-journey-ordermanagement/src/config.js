export const Config = {
    CREATE_INSTANCE: '/bin/mvc.do/ordermanagement/v1/app/',
    GET_INSTANCE_URL: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}`,
    GET_PRODUCTS: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/products`,
    GET_CATEGORIES: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/category`,
    GET_DELIVERY_OPTIONS: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/delivery-options`,
    SELECT_PRODUCTS: (instanceId, productId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/products/${productId}`,
    CREATE_ORDER: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/order-create`,
    UPDATE_CUSTOMER_INFO: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/customer-info`,
    SUBMIT_ORDER: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}/order-submit`,
    READ_INSTANCE: (instanceId) => `/bin/mvc.do/ordermanagement/v1/app/${instanceId}`
};
