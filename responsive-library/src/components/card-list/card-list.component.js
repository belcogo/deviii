import { Card } from '../card/card.component'
import './card-list.style.css'

export function CardList({ books, showIcon, handleCardPress, CardComponent = Card }) {

  const handlePress = (id, title, owner) => {
    if (handleCardPress) handleCardPress(id, title, owner)
  }

  const getTitle = (book) => {
    return book.bookRequested?.title ?? book?.title;
  }

  const getGenre = (book) => {
    return book.bookRequested?.genre?.name ?? book.genre?.name;
  }

  const getOwner = (book) => {
    console.debug(book)
    return book.bookRequested?.owner ?? book.owner;
  }

  return (
    <div className="pageContent list">
      {books?.map((book, idx) => (
        <CardComponent
          key={`${getTitle(book)} ${idx}`}
          title={getTitle(book)}
          subtitle={getGenre(book)}
          showIcon={showIcon}
          onClick={() => handlePress(book.id, getTitle(book), getOwner(book))}
          {...book}
          {...book?.bookRequested}
        />
      ))}
    </div>
  )
}
