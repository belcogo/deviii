import { bookStatus } from "../../enums/books.enum";
import { Card } from "./card.component";
import './card.style.css'

export function HistoryCard({ borrowStatus, title, genre, ...props }) {
  return (
    <div className="cardWrapper withStyle columnWrapper historyCard">
      <Card title={title} subtitle={genre?.name} key={props?.key} removeCardStyle onClick={props?.onClick} />
      <div className="subtitle">
        {bookStatus[borrowStatus]}
      </div>
    </div>
  )
}