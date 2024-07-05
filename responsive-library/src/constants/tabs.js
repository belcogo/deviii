import HomeIcon from '../assets/icons/home.svg'
import SearchIcon from '../assets/icons/search.svg'
import MyBooksIcon from '../assets/icons/list.svg'
import HistoryIcon from '../assets/icons/history.svg'
import ProfileIcon from '../assets/icons/user.svg'

export const tabs = [
  // { path: '/home', Icon: HomeIcon },
  { path: '/search', Icon: SearchIcon },
  { path: '/my-books', Icon: MyBooksIcon },
  { path: '/history', Icon: HistoryIcon },
  { path: '/profile', Icon: ProfileIcon },
]

export const paths = [
  { path: '/home', title: 'HOME', showIcon: false },
  { path: '/search', title: 'BUSCA', showIcon: true },
  { path: '/my-books', title: 'MEUS LIVROS', showIcon: true },
  { path: '/history', title: 'HISTÓRICO', showIcon: true },
  { path: '/profile', title: 'PERFIL', showIcon: true },
  { path: '/login', title: 'LOGIN', showIcon: false },
  { path: '/books/create', title: 'CADASTRO LIVRO', showIcon: true },
  { path: '/register', title: 'CADASTRO', showIcon: false },
]