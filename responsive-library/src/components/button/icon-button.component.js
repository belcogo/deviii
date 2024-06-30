import classnames from 'classnames'
import './icon-button.style.css'
export function IconButton({ Icon, absoluteButton, ...props }) {
  return (
    <button
      className={classnames(
        'buttonWrapper',
        { 'absoluteButton': absoluteButton }
      )}
      type="button"
      {...props}
    >
      <img alt="icon" src={Icon} className='image' />
    </button>
  )
}