import { useRecoilValue } from "recoil";
import { othersBooksAtom } from "../../state/books.atom";
import { CardList } from "../../components";
import { useNavigate } from 'react-router-dom';

export function SearchPage() {
	const othersBooks = useRecoilValue(othersBooksAtom)
	const navigate = useNavigate();
	const handlePress = () => navigate('/books/create')
	return (
		<div className="page">
			<CardList books={othersBooks} />
			<button onClick={handlePress} className="floatingButton">+</button>
		</div>
	);
}