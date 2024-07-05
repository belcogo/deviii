import ArrowRightIcon from "../../assets/icons/arrow-right.svg";
import './card.style.css'

export function Card({ title, subtitle, showIcon, key, onClick }) {
  return (
    <button type="button" className="cardWrapper" key={key} onClick={onClick}>
      <div className="cardContent">
        <div className="cardTitle">{title}</div>
        <p className="cardSubtitle">{subtitle}</p>
      </div>
      {showIcon && (
        <div className="iconWrapper">
          <img className="cardIcon" alt="icon" src={ArrowRightIcon} />
        </div>
      )}
    </button>
  )
}