import './footer-tab.style.css'
import { useLocation, useNavigate } from 'react-router-dom';
import { IconButton } from '../button/icon-button.component'
import { tabs } from '../../constants/tabs';
import classnames from 'classnames';

export function FooterTab() {
  const navigate = useNavigate()
  const { pathname } = useLocation()

  return (
    <div className="footerTab">
      {
        tabs.map(({ path, Icon }) => {
          const isActive = path === pathname
          return (
            <div
              className={classnames(
                'tab',
                {'activeTab': isActive}
              )}
            >
              <IconButton 
                Icon={Icon} 
                onPress={() => navigate(path)}
              />
            </div>
          )
        })
      }
    </div>
  )
}