import { Card } from '../card/card.component'
import './card-list.style.css'

export function CardList({ books, showIcon, handleCardPress }) {

  const handlePress = (id, title) => {
    if (handleCardPress) handleCardPress(id, title)
  }

  const getTitle = (book) => {
    return book.bookRequested?.title ?? book?.title;
  }

  const getGenre = (book) => {
    return book.bookRequested?.genre?.name ?? book.genre?.name;
  }

  return (
    <div className="pageContent list">
      {books?.map((book, idx) => (
        <Card
          key={`${getTitle(book)} ${idx}`}
          title={getTitle(book)}
          subtitle={getGenre(book)}
          showIcon={showIcon}
          onClick={() => handlePress(book.id, getTitle(book))}
        />
      ))}
    </div>
  )
}
