import { useRecoilValue } from "recoil";
import { myBooksAtom } from "../../state/books.atom";

export function HomePage() {
	const myBooks = useRecoilValue(myBooksAtom)
	console.debug(myBooks)
	return (
		<div>
			<p>Livros</p>
			<div>
				{myBooks.map((book) => (
					<div>
						<p>{book.title}</p>
						<p>{book?.genre?.name}</p>
					</div>
				))}
			</div>
		</div>
	);
}