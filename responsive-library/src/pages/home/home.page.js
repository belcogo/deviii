import { useRecoilValue } from "recoil";
// import ArrowRightIcon from '../../assets/icons/arrow-right.svg'
import { myBooksAtom } from "../../state/books.atom";
import { Header } from "../../components/header/header.component";
import { Card } from "../../components";

export function HomePage() {
	const myBooks = useRecoilValue(myBooksAtom)
	console.debug(myBooks)
	return (
		<div className="page">
			<Header title="MEUS LIVROS" />
			<div className="pageContent">
				{myBooks.map(({ title, genre }) => <Card title={title} subtitle={genre?.name} showIcon />)}
			</div>
		</div>
	);
}