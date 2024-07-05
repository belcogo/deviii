import { Card } from '../card/card.component'
import './card-list.style.css'

export function CardList({ books, showIcon }) {
  return (
    <div className="pageContent list">
      {books?.map(({ title, genre }, idx) => <Card key={`${title} ${idx}`} title={title} subtitle={genre?.name} showIcon={showIcon} />)}
    </div>
  )
}