import React, { useState } from 'react';
import authService from '../../services/authService';
import { useSetRecoilState } from 'recoil';
import { userAtom } from '../../state/user.atom';
import { useNavigate } from 'react-router-dom';
import bookService from '../../services/bookService';
import { myBooksAtom, othersBooksAtom } from '../../state/books.atom';
import { Button, Input } from '../../components';
import { Header } from '../../components/header/header.component';
import './login.style.css'

export const LoginPage = () => {
  const [email, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const setUser = useSetRecoilState(userAtom);
  const setMyBooks = useSetRecoilState(myBooksAtom)
  const setOthersBooks = useSetRecoilState(othersBooksAtom)
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
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
    }
  };

  return (
    <div className="page">
      <Header title="LOGIN" />
      <form className="form" onSubmit={handleLogin}>
        <div className='inputsWrapper'>
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
        </div>
        <Button text="Entrar" type="submit" filled />
        {error && <p>{error}</p>}
      </form>
    </div>
  );
};
