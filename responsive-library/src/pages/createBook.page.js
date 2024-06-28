import React, { useState, useEffect } from 'react';
import bookService from '../services/bookService';
import genreService from '../services/genreService';

const CreateBookPage = () => {
  const [title, setTitle] = useState('');
  const [idGenre, setIdGenre] = useState('');
  const [author, setAuthor] = useState('');
  const [publishedDate, setPublishedDate] = useState('');
  const [genres, setGenres] = useState([]);
  const [error, setError] = useState('');

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
    <div style={styles.container}>
      <h1>Add Book</h1>
      <form onSubmit={handleAddBook} style={styles.form}>
        <div style={styles.formGroup}>
          <label>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            style={styles.input}
          />
        </div>
        <div style={styles.formGroup}>
          <label>Genre:</label>
          <select
            value={idGenre}
            onChange={(e) => setIdGenre(e.target.value)}
            style={styles.input}
          >
            <option value="">Select Genre</option>
            {genres.map((genre) => (
              <option key={genre.id} value={genre.id}>
                {genre.name}
              </option>
            ))}
          </select>
        </div>
        <div style={styles.formGroup}>
          <label>Author:</label>
          <input
            type="text"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            style={styles.input}
          />
        </div>
        <div style={styles.formGroup}>
          <label>Published Date:</label>
          <input
            type="date"
            value={publishedDate}
            onChange={(e) => setPublishedDate(e.target.value)}
            style={styles.input}
          />
        </div>
        <button type="submit" style={styles.button}>Add Book</button>
        {error && <p style={styles.error}>{error}</p>}
      </form>
    </div>
  );
};

const styles = {
  container: {
    padding: '20px',
    backgroundColor: '#EAE2D7',
    minHeight: '100vh',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
  },
  formGroup: {
    marginBottom: '15px',
  },
  input: {
    padding: '10px',
    fontSize: '16px',
    borderRadius: '5px',
    border: '1px solid #ccc',
  },
  button: {
    padding: '10px',
    fontSize: '16px',
    backgroundColor: '#B18653',
    color: '#fff',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
  error: {
    color: 'red',
    marginTop: '10px',
  },
};

export default CreateBookPage;
