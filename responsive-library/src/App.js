import './App.css';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import { CreateBookPage, HomePage, LoginPage, RegisterPage } from './pages';

const router = createBrowserRouter(createRoutesFromElements(
  <Route path='/'>
    <Route path='/login' element={<LoginPage />} />
    <Route path='/home' element={<HomePage />} />
    <Route path='/books/create' element={<CreateBookPage />} />
    <Route path='/register' element={<RegisterPage />} />
  </Route>
))

function App() {
  return <RouterProvider router={router} />;
}

export default App;
