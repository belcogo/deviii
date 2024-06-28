import axios from 'axios';

const API_URL = 'http://localhost:8099/authenticate';

const login = async (email, password) => {
  try {
    const response = await axios.post(API_URL, { email, password }, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const { token } = response.data;
    localStorage.setItem('authToken', token);
    return token;
  } catch (error) {
    console.error('Error logging in:', error);
    throw error;
  }
};

const logout = () => {
  localStorage.removeItem('authToken');
};

const authService = {
  login,
  logout,
};

export default authService;
