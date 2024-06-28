import './App.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { CreateBookPage, HomePage, LoginPage, RegisterPage } from './pages';

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: '/home',
    element: <HomePage />
  },
  {
    path: "/books/create",
    element: <CreateBookPage />,
  },
  {
    path: "/register",
    element: <RegisterPage />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
