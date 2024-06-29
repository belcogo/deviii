import React, { useState } from 'react';
import authService from '../../services/authService';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { userAtom } from '../../state/user.atom';
import { useNavigate } from 'react-router-dom';
import bookService from '../../services/bookService';
import { myBooksAtom, othersBooksAtom } from '../../state/books.atom';
import { Button, Input, Loader } from '../../components';
import { Header } from '../../components/header/header.component';
import './login.style.css'
import { LoaderAtom } from '../../state/loader.atom';

const Login = () => {
  const [email, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useRecoilState(LoaderAtom);
  const shouldDisableButton = !email || !password || isLoading
  const setUser = useSetRecoilState(userAtom);
  const setMyBooks = useSetRecoilState(myBooksAtom)
  const setOthersBooks = useSetRecoilState(othersBooksAtom)
  const navigate = useNavigate();

  const navigateToRegistration = () => {
    navigate('/register')
  }

  const handleLogin = async (e) => {
    e.preventDefault();
    setIsLoading(true)
    try {
      await authService.login(email, password);
      const userInfo = await authService.userDetails();
      setUser(userInfo)
      const books = await bookService.fetchBooks();
      const { myBooks, othersBooks } = books?.reduce(({ myBooks, othersBooks }, book) => {
        if (book?.owner?.name === userInfo?.name) {
          myBooks.push(book)
        } else {
          othersBooks.push(book)
        }

        return {myBooks, othersBooks}
      }, { myBooks: [], othersBooks: []})
      setMyBooks(myBooks)
      setOthersBooks(othersBooks)
      navigate('/home')
    } catch (error) {
      console.debug(error)
      setError('Invalid username or password');
    } finally {
      setIsLoading(false)
    }
  };

  return (
    <div className="page">
      <Header title="LOGIN" />
      <form className="form pageContent" onSubmit={handleLogin}>
        <div className='columnWrapper'>
          <Input
            label="Email"
            type="text"
            value={email}
            onChange={(e) => setUsername(e.target.value)}
          />
          <Input
            label="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {error && <p className='error'>{error}</p>}
        </div>
        <div className="columnWrapper">
          <Button text="Entrar" type="submit" filled  disabled={shouldDisableButton} />
          <Button text="Não tenho login" type="button" outlined onPress={navigateToRegistration} />
        </div>
      </form>
    </div>
  );
};

export const LoginPage = () => (
  <>
    <Login />
    <Loader />
  </>
)
