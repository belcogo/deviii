import { useRecoilValue } from "recoil";
import { myBooksAtom } from "../../state/books.atom";
import { CardList } from "../../components";
import { useNavigate } from 'react-router-dom';
import './my-books.style.css'

export function MyBooksPage() {
	const myBooks = useRecoilValue(myBooksAtom)
	const navigate = useNavigate();
	const handlePress = () => navigate('/books/create')
	return (
		<div className="page">
			<CardList books={myBooks} />
			<button onClick={handlePress} className="floatingButton">+</button>
		</div>
	);
}