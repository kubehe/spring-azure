import Axios from "axios";


export const getFood = (name) => {
  return Axios.get(`/api/food/${name}`, {withCredentials: true});
};

export const getFoods = () => {
  return Axios.get('/api/food');
};

export const addFood = ({name, calories}) => {
  return Axios.post('/api/food', {name, calories}, {withCredentials: true});
};

export const removeFood = (name) => {
  return Axios.delete(`/api/food/${name}`, {withCredentials: true});
};

export const getUser = (name) => {
  return Axios.get(`/api/user/${name}`, {withCredentials: true});
};

export const getUsers = () => {
  return Axios.get('/api/user');
};

export const addUser = ({name, password, roles}) => {
  return Axios.post('/api/user', {name, password, roles}, {withCredentials: true});
};

export const removeUser = (name) => {
  return Axios.delete(`/api/user/${name}`, {withCredentials: true});
};

export const getCurrentUserName = () => {
  return Axios.post('/api/user/current', undefined, {withCredentials: true});
};

export const addFoodHistory = ({dateOfConsumption, food, name}) => {
  return Axios.post('/api/user-food-history', {dateOfConsumption, food, name}, {withCredentials: true});
};

export const removeFoodHistory = ({dateOfConsumption, food, name}) => {
  return Axios.delete('/api/user-food-history', {
    data: {dateOfConsumption, food, name},
    withCredentials: true
  });
};

