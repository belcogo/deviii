import ArrowRightIcon from "../../assets/icons/arrow-right.svg";
import classnames from 'classnames'
import './card.style.css'

export function Card({ title, subtitle, showIcon, key, onClick, removeCardStyle }) {
  return (
    <button type="button" className={classnames(
      'cardWrapper',
      {'withStyle': !removeCardStyle}
      )}
      key={key}
      onClick={onClick}
    >
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