import React, { useState, useEffect } from 'react';
import bookService from '../../services/bookService';
import genreService from '../../services/genreService';
import './create-book.style.css'
import { Button, Input } from '../../components';

export const CreateBookPage = () => {
  const [title, setTitle] = useState('');
  const [idGenre, setIdGenre] = useState('');
  const [author, setAuthor] = useState('');
  const [publishedDate, setPublishedDate] = useState('');
  const [genres, setGenres] = useState([]);
  const [error, setError] = useState('');

  const shouldDisableButton = !title || !idGenre || !author || !publishedDate

  useEffect(() => {
    const fetchGenres = async () => {
      try {
        const genresData = await genreService.listGenres();
        setGenres(genresData);
      } catch (error) {
        console.error('Error fetching genres:', error);
      }
    };
    fetchGenres();
  }, []);

  const handleAddBook = async (e) => {
    e.preventDefault();
    try {
      await bookService.createBook(title, idGenre, author, publishedDate);
    } catch (error) {
      setError('Error adding book');
    }
  };

  return (
    <div className="page full">
      <form onSubmit={handleAddBook} className="form">
        <div className="formGroup">
          <Input
            type="text"
            label="Titulo"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <>
            <label>Genre:</label>
            <select
              value={idGenre}
              onChange={(e) => setIdGenre(e.target.value)}
            >
              <option value="">Select Genre</option>
              {genres.map((genre) => (
                <option key={genre.id} value={genre.id}>
                  {genre.name}
                </option>
              ))}
            </select>
          </>
          <Input
            type="text"
            label="Autor"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
          <Input
            type="text"
            label="Data de publicação"
            value={publishedDate}
            onChange={(e) => setPublishedDate(e.target.value)}
          />
        </div>
        <Button type="submit" text="Adicionar livro" filled disabled={shouldDisableButton} />
      </form>
    </div>
  );
};
