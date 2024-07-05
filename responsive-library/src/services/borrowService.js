import axios from 'axios';

const API_URL = 'http://localhost:8099/borrows';

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

const requestBorrow = async (idBook) => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.post(API_URL, { idBook });
    return response.data;
  } catch (error) {
    console.error('Error creating user:', error);
    throw error;
  }
}

const borrowService = {
  requestBorrow,
};

export default borrowService;
