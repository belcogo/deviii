import classnames from 'classnames'
import './modal.style.css'
import { modalVisibleAtom } from '../../state/modal.atom'
import { useRecoilState } from 'recoil'
import { modalsComponents } from '../../enums/modals.enum'

export function Modal({ children }) {
  const [modalVisible, setModalVisible] = useRecoilState(modalVisibleAtom)
  const Section = modalsComponents[modalVisible]
  return (
    <div className={classnames(
      'overlay',
      {'visible': !!modalVisible}
    )}>
      <div className={classnames(
        'modal',
        {'visible': !!modalVisible}
      )}>
        {!!Section && <Section />}
      </div>
    </div>
  )
}