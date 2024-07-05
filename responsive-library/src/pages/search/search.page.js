import { useRecoilValue, useSetRecoilState } from "recoil";
import { othersBooksAtom, requestedBookAtom } from "../../state/books.atom";
import { CardList, Input } from "../../components";
import { useEffect, useMemo, useState } from "react";
import { modalVisibleAtom } from "../../state/modal.atom";
import { modals } from "../../enums/modals.enum";

function checkFilters(title, author, genre, term) {
	const titleToCompare = title?.toLowerCase()
	const authorToCompare = author?.toLowerCase()
	const genreToCompare = genre?.toLowerCase()
	const termToCompare = term?.toLowerCase()

	return (titleToCompare?.includes(termToCompare)
		|| authorToCompare.includes(termToCompare)
		|| genreToCompare?.includes(termToCompare))
}

export function SearchPage() {
	const [searchTerm, setSearchTerm] = useState('')
	const [filterTerm, setFilterTerm] = useState('')
	const setIsVisible = useSetRecoilState(modalVisibleAtom)
	const setRequestedBook = useSetRecoilState(requestedBookAtom)
	const othersBooks = useRecoilValue(othersBooksAtom)
	const booksToShow = useMemo(() => {
		if (!filterTerm) return othersBooks
		return othersBooks?.filter(({ title, author, genre}) => {
			return checkFilters(title, author, genre.name, filterTerm)
		})
	}, [othersBooks, filterTerm])

	const handleChange = (e) => setSearchTerm(e?.target?.value)

	useEffect(() => {
		const timeout = setTimeout(() => setFilterTerm(searchTerm), 1500)

		return () => {
			clearTimeout(timeout)
		}
	}, [searchTerm])

	
  const handleCardPress = (id, title) => {
		setRequestedBook({ id, title })
    setIsVisible(modals.REQUEST_BOOK)
  }

	return (
		<div className="page">
			<Input label="Busca" value={searchTerm} onChange={handleChange} />
			<CardList books={booksToShow} showIcon handleCardPress={handleCardPress} />
		</div>
	);
}