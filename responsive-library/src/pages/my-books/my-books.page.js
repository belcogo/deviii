import { useRecoilValue } from "recoil";
import { myBooksAtom } from "../../state/books.atom";
import { Card } from "../../components";

export function MyBooksPage() {
	const myBooks = useRecoilValue(myBooksAtom)
	console.debug(myBooks)
	return (
		<div className="page">
			<div className="pageContent">
				{myBooks.map(({ title, genre }) => <Card title={title} subtitle={genre?.name} showIcon />)}
			</div>
		</div>
	);
}