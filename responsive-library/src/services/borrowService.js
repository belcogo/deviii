import axios from 'axios';

const URLs = {
  GET: (userId) => `http://localhost:8099/users/${userId}/borrows`,
  POST: () => "http://localhost:8099/borrows",
};

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Basic ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

const getBorrows = async (id) => {
  try {
    setAuthToken(localStorage.getItem("authToken"));
    const response = await axios.get(URLs.GET(id));
    console.log(response.data)
    return response.data;
  } catch (error) {
    console.error("Error fetching borrows:", error);
    throw error;
  }
};

const requestBorrow = async (idBook) => {
  try {
    setAuthToken(localStorage.getItem('authToken'));
    const response = await axios.post(URLs.POST(), { idBook });
    return response.data;
  } catch (error) {
    console.error('Error creating user:', error);
    throw error;
  }
}

const borrowService = {
  getBorrows,
  requestBorrow,
};

export default borrowService;
