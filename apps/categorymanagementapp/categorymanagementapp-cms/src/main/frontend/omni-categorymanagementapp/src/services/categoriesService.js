export const getCategories = function (HttpClient) {
    let config = {
        method: 'get',
        url: '/bin/mvc.do/categorymanagementapp/v1/app/category'
    };
    return new Promise((resolve, reject) => {
        HttpClient.request(config)
            .then((result) => {
                resolve(result.data || []);
            })
            .catch((error) => {
                console.log(error, 'error');
                reject(error);
            });
    });
};

export const deleteCategory = function (HttpClient, categoryId) {
    let config = {
        method: 'delete',
        url: `/bin/mvc.do/categorymanagementapp/v1/app/category/${categoryId}`
    };
    return new Promise((resolve, reject) => {
        HttpClient.request(config)
            .then((result) => {
                resolve(result.data || []);
            })
            .catch((error) => {
                console.log(error, 'error');
                reject(error);
            });
    });
};

export const addCategory = function (HttpClient, categoryName) {
    let config = {
        method: 'post',
        url: `/bin/mvc.do/categorymanagementapp/v1/app/category`,
        data: { categoryName }
    };
    return new Promise((resolve, reject) => {
        HttpClient.request(config)
            .then((result) => {
                resolve(result.data || []);
            })
            .catch((error) => {
                console.log(error, 'error');
                reject(error);
            });
    });
};

export const updateCategory = function (HttpClient, categoryId, categoryName) {
    let config = {
        method: 'put',
        url: `/bin/mvc.do/categorymanagementapp/v1/app/category`,
        data: { categoryId, categoryName }
    };
    return new Promise((resolve, reject) => {
        HttpClient.request(config)
            .then((result) => {
                resolve(result.data || []);
            })
            .catch((error) => {
                console.log(error, 'error');
                reject(error);
            });
    });
};
