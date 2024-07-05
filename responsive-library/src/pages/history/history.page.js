import './history.style.css'
import { CardList } from "../../components";
import { borrowedBooksAtom, requestedBookAtom } from "../../state/books.atom";
import { useEffect } from "react";
import { userAtom } from "../../state/user.atom";
import { useRecoilState, useSetRecoilState } from "recoil";
import { useRecoilValue } from "recoil";
import borrowService from '../../services/borrowService';
import { HistoryCard } from '../../components/card/history-card.component';
import { modalVisibleAtom } from '../../state/modal.atom';
import { modals } from '../../enums/modals.enum';

export const HistoryPage = () =>{
    const user = useRecoilValue(userAtom)
    const [books, setBorrowedBooks] = useRecoilState(borrowedBooksAtom);
    const setRequestedBook = useSetRecoilState(requestedBookAtom)
    const setIsVisible = useSetRecoilState(modalVisibleAtom)

    useEffect(() => {
        // TODO: user.id is null
        borrowService.getBorrows(user.id)
            .then((books) => setBorrowedBooks(books))
            .catch((error) => console.error("Error fetching books:", error));
    }, []);

    const handleCardPress = (id, title, owner) => {
        console.debug(title, id, owner)
        if (owner?.name !== user?.name) return

        setRequestedBook({id, title})
        setIsVisible(modals.REVIEW_REQUEST)
    }

    return (
        <div className="page">
            <CardList books={books} CardComponent={HistoryCard} handleCardPress={handleCardPress} />
        </div>
    );
}