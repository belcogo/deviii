import ArrowLeftIcon from "../../assets/icons/arrow-left.svg";
import { IconButton } from "../button/icon-button.component";
import './header.style.css'

export function Header({ title, showIcon }) {
  return (
    <div className="headerWrapper">
      {showIcon && <IconButton Icon={ArrowLeftIcon} />}
      <h1 className="headerTitle">{title}</h1>
    </div>
  )
}