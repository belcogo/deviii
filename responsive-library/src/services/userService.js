import axios from 'axios';

const API_URL = 'http://localhost:8099/users';

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

const createUser = async (name = '', password = '', email = '') => {
  try {
    const response = await axios.post(API_URL, {
      name,
      password,
      email,
      role: 'ROLE_USER'
    });
    return response.data;
  } catch (error) {
    console.error('Error creating user:', error);
    throw error;
  }
}

const userService = {
  setAuthToken,
  createUser
};

export default userService;
