import { useRecoilValue } from "recoil";
import { myBooksAtom } from "../../state/books.atom";
import { Card } from "../../components";
import { useNavigate } from 'react-router-dom';
import './my-books.style.css'

export function MyBooksPage() {
	const myBooks = useRecoilValue(myBooksAtom)
	const navigate = useNavigate();
	console.debug(myBooks)
	const handlePress = () => navigate('/books/create')
	return (
		<div className="page">
			<div className="pageContent">
				{myBooks.map(({ title, genre }) => <Card title={title} subtitle={genre?.name} showIcon />)}
			</div>
			<button onClick={handlePress} className="floatingButton">+</button>
		</div>
	);
}