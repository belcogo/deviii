import axios from 'axios';

const API_URL = 'http://localhost:8099/books';

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

const fetchBooks = async (title = '', genre = '') => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.get(API_URL, {
      params: { title, genre },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
    throw error;
  }
};

const createBook = async (title = '', idGenre = '', author = '', publishedDate = '') => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.post(API_URL, {
      title,
      idGenre,
      author,
      publishedDate
    });
    return response.data;
  } catch (error) {
    console.error('Error creating books:', error);
    throw error;
  }
}

const bookService = {
  setAuthToken,
  fetchBooks,
};

export default bookService;
