import { useLocation, useNavigate } from "react-router-dom";
import ArrowLeftIcon from "../../assets/icons/arrow-left.svg";
import { IconButton } from "../button/icon-button.component";
import './header.style.css'
import { paths } from "../../constants/tabs";

export function Header() {
  const { pathname } = useLocation()
  const { title, showIcon } = paths?.find(({ path }) => path === pathname)
  const navigate = useNavigate()

  return (
    <div className="headerWrapper">
      {showIcon && <IconButton Icon={ArrowLeftIcon} absoluteButton onClick={() => navigate(-1)} />}
      <h1 className="headerTitle">{title}</h1>
    </div>
  )
}