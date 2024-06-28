import './App.css';
import { createBrowserRouter, RouterProvider, createRoutesFromElements, Route } from 'react-router-dom'
import LoginPage from './pages/login.page';
import CreateBookPage from './pages/createBook.page';
import RegisterPage from './pages/register.page';
// import { HomePage } from './pages/home.page';

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
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

// const router = createBrowserRouter(
//   createRoutesFromElements(
//     <Route
//       element={<LoginPage />}
//       path="/"
//       errorElement={<>Error</>}
//     />
//   )
// );

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App;
