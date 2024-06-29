import ArrowLeftIcon from "../../assets/icons/arrow-left.svg";
import { IconButton } from "../button/icon-button.component";
import { styles } from "./header.style";

export function Header({ title, showIcon }) {
  return (
    <div style={styles.wrapper}>
      {showIcon && <IconButton Icon={ArrowLeftIcon} />}
      <h1 style={styles.title}>{title}</h1>
    </div>
  )
}