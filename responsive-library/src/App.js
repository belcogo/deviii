import './App.css';
import { createBrowserRouter, RouterProvider, createRoutesFromElements, Route } from 'react-router-dom'
import { LoginPage } from './pages/login.page';
// import { HomePage } from './pages/home.page';

// const router = createBrowserRouter([
//   {
//     path: "/login",
//     element: <LoginPage />,
//   },
//   {
//     path: "/home",
//     element: <HomePage />,
//   },
// ]);

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      element={<LoginPage />}
      path="/"
      errorElement={<>Error</>}
    />
  )
);

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App;
