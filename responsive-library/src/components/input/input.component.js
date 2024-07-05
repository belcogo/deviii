import { useMemo, useState } from "react";
import classnames from 'classnames'
import './input.style.css';

export function Input({ label, value, onChange, placeholder, keepFocusedLayout, ...props }) {
  const isInputEmpty = useMemo(() => !value, [value])
  const [isInputFocused, setIsInputFocused] = useState(false)

  const onFocus = () => setIsInputFocused(true)
  const onBlur = () => setIsInputFocused(false)

  const useInitialStyle = (isInputEmpty && !isInputFocused)
  return (
    <div
      className="inputWrapper">
      {label && (
        <label
          className={classnames(
            'label',
            {'emptyInputLabel': useInitialStyle && !keepFocusedLayout },
            {'inputWithValueLabel': !useInitialStyle || keepFocusedLayout}
          )}
        >
          {label}
        </label>
      )}
      <input
        className="input"
        onFocus={onFocus}
        onBlur={onBlur}
        {...props}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
    </div>
  );
}