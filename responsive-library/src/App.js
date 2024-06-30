import './App.css';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import { CreateBookPage, LoginPage, MyBooksPage, RegisterPage } from './pages';
import { FooterTab } from './components/footer-tab/footer-tab.component';
import { Header } from './components/header/header.component';
import { Loader } from './components';

const Element = ({ children, showFooter }) => (
  <>
    <Header />
    {children}
    {showFooter && <FooterTab />}
    <Loader />
  </>
)

const router = createBrowserRouter(createRoutesFromElements(
  <Route path='/'>
    <Route path='/home' />
    <Route path='/search' />
    <Route path='/my-books' element={<Element showFooter><MyBooksPage /></Element>} />
    <Route path='/history' />
    <Route path='/profile' />
    <Route path='/login' element={<Element><LoginPage /></Element>} />
    <Route path='/books/create' element={<Element><CreateBookPage /></Element>} />
    <Route path='/register' element={<Element><RegisterPage /></Element>} />
  </Route>
))

function App() {
  return (
    <RouterProvider router={router}/>
  );
}

export default App;
