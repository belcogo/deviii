import axios from 'axios';

const API_URL = 'http://localhost:8099/authenticate';

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

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

const userDetails = async () => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.get('http://localhost:8099/auth/user-details', {
      headers: {
        'Content-Type': 'application/json',
      }
    })

    return response?.data;
  } catch (e) {
    console.error('Error logging in:', e);
    throw e;
  }
}

const logout = () => {
  localStorage.removeItem('authToken');
};

const authService = {
  login,
  logout,
  userDetails,
};

export default authService;
