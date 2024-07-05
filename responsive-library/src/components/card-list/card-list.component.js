import { Card } from '../card/card.component'

export function CardList({ books }) {
  return (
    <div className="pageContent">
      {books?.map(({ title, genre }, idx) => <Card key={`${title} ${idx}`} title={title} subtitle={genre?.name} showIcon />)}
    </div>
  )
}