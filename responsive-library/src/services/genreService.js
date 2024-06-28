import axios from 'axios';

const API_URL = 'http://localhost:8099/genres';

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

const listGenres = async () => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
    throw error;
  }
};

const genreService = {
  setAuthToken,
  listGenres
};

export default genreService;
