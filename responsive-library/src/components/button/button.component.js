import classnames from 'classnames'
import './button.style.css'
export const Button = ({ text, filled, outlined, ...props}) => {
  return (
    <button
      className={classnames(
        'button',
        {'filled-button': filled},
      )}
      {...props}
    >
      {text}
    </button>
  )
}