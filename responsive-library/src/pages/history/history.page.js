import './history.style.css'
import { CardList } from "../../components";
import { borrowedBooksAtom, myBooksAtom } from "../../state/books.atom";
import { useEffect } from "react";
import { userAtom } from "../../state/user.atom";
import { useRecoilState } from "recoil";
import { useRecoilValue } from "recoil";
import borrowService from '../../services/borrowService';

export const HistoryPage = () =>{
    const user = useRecoilValue(userAtom)
    const [books, setBorrowedBooks] = useRecoilState(borrowedBooksAtom);

    useEffect(() => {
        // TODO: user.id is null
        borrowService.getBorrows(user.id)
            .then((books) => setBorrowedBooks(books))
            .catch((error) => console.error("Error fetching books:", error));
    }, []);

    return (
        <div className="page">
            <CardList books={books} />
        </div>
    );
}