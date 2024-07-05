import { Card } from '../card/card.component'
import './card-list.style.css'

export function CardList({ books, showIcon, handleCardPress }) {

  const handlePress = (id, title) => {
    if (handleCardPress) handleCardPress(id, title)
  }
  return (
    <div className="pageContent list">
      {books?.map(({ title, genre, id }, idx) => (
        <Card
          key={`${title} ${idx}`}
          title={title}
          subtitle={genre?.name}
          showIcon={showIcon}
          onClick={() => handlePress(id, title)}
        />
      ))}
    </div>
  )
}