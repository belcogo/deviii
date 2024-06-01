-- Inserção dos generos
INSERT INTO genres (id, name) VALUES
(1, 'Romance'),
(2, 'Mistério'),
(3, 'Aventura'),
(4, 'Fantasia'),
(5, 'Ficção Científica'),
(6, 'Literatura Clássica'),
(7, 'Horror'),
(8, 'Humor'),
(9, 'Biografia'),
(10, 'História'),
(11, 'Autobiografia'),
(12, 'Ensaio'),
(13, 'Livros de Referência'),
(14, 'Livros de Autoajuda'),
(15, 'Livros de Culinária'),
(16, 'Livros de Viagem'),
(17, 'Livros Infantis'),
(18, 'Livros Juvenis'),
(19, 'Poesia'),
(20, 'Drama'),
(21, 'Religião e Espiritualidade'),
(22, 'Esporte e Lazer');


-- Inserção dos usuários

-- Senha 1234
INSERT INTO users (id, name, password, email, role) VALUES
(1, 'Bruno', '$2a$10$j8G1nR9JWwZL8/1NB5gN2.QGLGklbwyz.EGPqiJrz4NPTZdc1C2Ny', 'bruno.hoffmann@email.com', 'ROLE_USER');

-- Senha 4321
INSERT INTO users (id, name, password, email, role) VALUES
(2, 'Bel Cogo', '$2a$10$BRUQ1Kb2IUP6sVyBiteCj.iXXK8r/T50YDhMXWs.9gQmot.u1gBv.', 'bel.cogo@email.com', 'ROLE_USER');

--- Senha 1234
INSERT INTO users (id, name, password, email, role)
VALUES (3, 'João Accorsi', '$2a$10$xFY7BpcGaF9TLlHJxK.7feAVR54tVElXBVcoWpNUIDNxHkOBeXVMG', 'joao.accorsi@email.com', 'ROLE_USER');


--- Senha 1234
INSERT INTO users (id, name, password, email, role)
VALUES (4, 'Taylor Swift', '$2a$10$PlAUB9U6scNUSxBhdwDGE.MoSnZLnT2eZf6oBoHOzT4fIImVCuyYG', 'taylor.swift@email.com', 'ROLE_USER');


-- Inserção livros para Bruno
INSERT INTO books (title, author, genre_id, published_date, created_date_time, owner_id) VALUES
('O Diário de Uma Paixão', 'Nicholas Sparks', 1, '1996-10-01', '2022-06-01T12:00:00Z', 1),
('O Código Da Vinci', 'Dan Brown', 2, '2003-03-18', '2022-06-01T12:00:00Z', 1),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 4, '1997-06-26', '2022-06-01T12:00:00Z', 1),
('1984', 'George Orwell', 5, '1949-06-08', '2022-06-01T12:00:00Z', 1),
('O Grande Gatsby', 'F. Scott Fitzgerald', 6, '1925-04-10', '2022-06-01T12:00:00Z', 1),
('O Sol é Para Todos', 'Harper Lee', 6, '1960-07-11', '2022-06-01T12:00:00Z', 1),
('O Apanhador no Campo de Centeio', 'J.D. Salinger', 6, '1951-07-16', '2022-06-01T12:00:00Z', 1),
('O Senhor dos Anéis', 'J.R.R. Tolkien', 4, '1954-07-29', '2022-06-01T12:00:00Z', 1),
('O Hobbit', 'J.R.R. Tolkien', 4, '1937-09-21', '2022-06-01T12:00:00Z', 1);

-- Inserção livros para Bel
INSERT INTO books (title, author, genre_id, published_date, created_date_time, owner_id) VALUES
('Garota Exemplar', 'Gillian Flynn', 2, '2012-06-05', '2022-06-01T12:00:00Z', 2),
('Os Homens que Não Amavam as Mulheres', 'Stieg Larsson', 2, '2005-08-01', '2022-06-01T12:00:00Z', 2),
('Jogos Vorazes', 'Suzanne Collins', 4, '2008-09-14', '2022-06-01T12:00:00Z', 2),
('Correr ou Morrer', 'James Dashner', 3, '2009-10-06', '2022-06-01T12:00:00Z', 2),
('Perdido em Marte', 'Andy Weir', 5, '2011-09-27', '2022-06-01T12:00:00Z', 2),
('O Poder do Agora', 'Eckhart Tolle', 6, '1997-10-06', '2022-06-01T12:00:00Z', 2),
('Educated', 'Tara Westover', 9, '2018-02-20', '2022-06-01T12:00:00Z', 2),
('Homo Deus', 'Yuval Noah Harari', 10, '2015-09-08', '2022-06-01T12:00:00Z', 2),
('A Sutil Arte de Ligar o F*da-se', 'Mark Manson', 14, '2016-09-13', '2022-06-01T12:00:00Z', 2);

-- Associação dos livros ao usuário João Accorsi (ID: 3)
INSERT INTO books (title, author, genre_id, published_date, created_date_time, owner_id) VALUES
('Percy Jackson e o Ladrão de Raios', 'Rick Riordan', 4, '2005-06-28', '2022-06-01T12:00:00Z', 3),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 12, '1943-04-06', '2022-06-01T12:00:00Z', 3),
('Orgulho e Preconceito', 'Jane Austen', 8, '1813-01-28', '2022-06-01T12:00:00Z', 3),
('A Revolução dos Bichos', 'George Orwell', 5, '1945-08-17', '2022-06-01T12:00:00Z', 3),
('Dom Quixote', 'Miguel de Cervantes', 8, '1605-01-16', '2022-06-01T12:00:00Z', 3),
('As Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa', 'C.S. Lewis', 4, '1950-10-16', '2022-06-01T12:00:00Z', 3),
('A Revolução dos Bichos', 'George Orwell', 5, '1945-08-17', '2022-06-01T12:00:00Z', 3),
('Dom Quixote', 'Miguel de Cervantes', 8, '1605-01-16', '2022-06-01T12:00:00Z', 3),
('As Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa', 'C.S. Lewis', 4, '1950-10-16', '2022-06-01T12:00:00Z', 3),
('Moby Dick', 'Herman Melville', 4, '1851-10-18', '2022-06-01T12:00:00Z', 3),
('O Conde de Monte Cristo', 'Alexandre Dumas', 8, '1844-08-28', '2022-06-01T12:00:00Z', 3);

-- Associação dos livros ao usuário Taylor Swift (ID: 4)
INSERT INTO books (title, author, genre_id, published_date, created_date_time, owner_id) VALUES
('Maus: A História de um Sobrevivente', 'Art Spiegelman', 13, '1986-01-01', '2022-06-01T12:00:00Z', 4),
('Cem Anos de Solidão', 'Gabriel García Márquez', 8, '1967-05-30', '2022-06-01T12:00:00Z', 4),
('O Retrato de Dorian Gray', 'Oscar Wilde', 8, '1890-04-15', '2022-06-01T12:00:00Z', 4),
('A Metamorfose', 'Franz Kafka', 5, '1915-10-15', '2022-06-01T12:00:00Z', 4),
('O Nome do Vento', 'Patrick Rothfuss', 4, '2007-03-27', '2022-06-01T12:00:00Z', 4),
('A Rainha Vermelha', 'Victoria Aveyard', 4, '2015-02-10', '2022-06-01T12:00:00Z', 4),
('O Poder do Hábito', 'Charles Duhigg', 9, '2012-02-28', '2022-06-01T12:00:00Z', 4),
('A Cabana', 'William P. Young', 4, '2007-07-01', '2022-06-01T12:00:00Z', 4),
('A Revolução dos Bichos', 'George Orwell', 5, '1945-08-17', '2022-06-01T12:00:00Z', 4),
('Dom Quixote', 'Miguel de Cervantes', 8, '1605-01-16', '2022-06-01T12:00:00Z', 4),
('As Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa', 'C.S. Lewis', 4, '1950-10-16', '2022-06-01T12:00:00Z', 4);
