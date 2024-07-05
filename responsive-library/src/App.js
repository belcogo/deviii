import './App.css';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import { CreateBookPage, LoginPage, MyBooksPage, RegisterPage, ProfilePage, SearchPage } from './pages';
import { FooterTab } from './components/footer-tab/footer-tab.component';
import { Header } from './components/header/header.component';
import { Loader } from './components';
import { Modal } from './components/modal/modal.component';

const Element = ({ children, showFooter }) => (
  <>
    <Header />
    {children}
    {showFooter && <FooterTab />}
    <Loader />
    <Modal/>
  </>
)

const router = createBrowserRouter(createRoutesFromElements(
  <Route path='/'>
    <Route path='/home' />
    <Route path='/search' element={<Element showFooter><SearchPage /></Element>} />
    <Route path='/my-books' element={<Element showFooter><MyBooksPage /></Element>} />
    <Route path='/history' />
    <Route path='/profile' element={<Element showFooter><ProfilePage /></Element>} />
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
