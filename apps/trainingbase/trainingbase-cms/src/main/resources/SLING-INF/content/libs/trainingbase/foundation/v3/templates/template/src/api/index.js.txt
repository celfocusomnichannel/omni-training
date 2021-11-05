import { Config } from '../config';

let httpClientService;

export const setHttpClient = (HttpClient) => (httpClientService = HttpClient);
export const fetchUsers = () => {
    const config = {
        url: Config.API_URL,
        method: 'get'
    };
    return httpClientService.request(config);
};
export const createUser = (newUser) => {
    const config = {
        url: Config.API_URL,
        method: 'post',
        data: newUser
    };
    return httpClientService.request(config);
};
export const updateUser = (id, updatedUser) => {
    const config = {
        url: `${Config.API_URL}/${id}`,
        method: 'post',
        data: updatedUser
    };
    return httpClientService.request(config);
};
export const deleteUser = (id) => {
    const config = {
        url: `${Config.API_URL}/${id}`,
        method: 'delete'
    };
    return httpClientService.request(config);
};
