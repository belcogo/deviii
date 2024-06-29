import classnames from 'classnames'
import './button.style.css'
export const Button = ({ text, filled, outlined, onPress, disabled, error, ...props}) => {
  return (
    <button
      disabled={disabled}
      onClick={onPress}
      className={classnames(
        'button',
        {'filled-button': filled},
        {'outlined-button': outlined},
        {'buttonWithError': error > 0},
        {'disabled': disabled}
      )}
      {...props}
    >
      {text}
    </button>
  )
}