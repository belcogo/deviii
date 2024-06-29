import { styles } from "./icon-button.style";

export function IconButton({ Icon, onPress }) {
  return (
    <button style={styles.buttonWrapper} type="button">
      <img alt="icon" src={Icon} style={styles.image} />
    </button>
  )
}