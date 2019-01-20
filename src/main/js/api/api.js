import Axios from "axios";


export const getFood = async (name) => {
  const res = await Axios.get(`/api/food/${name}`, {withCredentials: true});
  return await res;
};

export const getFoods = async () => {
  const res = await Axios.get('/api/food');
  return await res;
};

export const addFood = async ({name, calories}) => {
  const res = await Axios.post('/api/food', {name, calories}, {withCredentials: true});
  return await res;
};

export const removeFood = async (name) => {
  const res = await Axios.delete(`/api/food/${name}`, {withCredentials: true});
  return await res;
};

export const getUser = async (name) => {
  const res = await Axios.get(`/api/user/${name}`, {withCredentials: true});
  return await res;
};

export const getUsers = async () => {
  const res = await Axios.get('/api/user');
  return await res;
};

export const addUser = async ({name, password, roles}) => {
  const res = await Axios.post('/api/user', {name, password, roles}, {withCredentials: true});
  return await res;
};

export const removeUser = async (name) => {
  const res = await Axios.delete(`/api/user/${name}`, {withCredentials: true});
  return await res;
};

export const addFoodHistory = async ({dateOfConsumption, food, name}) => {
  const res = await Axios.post('/api/user-food-history', {dateOfConsumption, food, name}, {withCredentials: true});
  return await res;
};

export const removeFoodHistory = async ({dateOfConsumption, food, name}) => {
  const res = await Axios.delete('/api/user-food-history', {
    data: {dateOfConsumption, food, name},
    withCredentials: true
  });
  return await res;
};

export default useApi;
