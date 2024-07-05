import { Button } from "../../button/button.component";
import './style.css'
import { useRecoilState, useSetRecoilState } from "recoil";
import borrowService from '../../../services/borrowService'
import { modalVisibleAtom } from "../../../state/modal.atom";
import { LoaderAtom } from "../../../state/loader.atom";
import { borrowedBooksAtom, requestedBookAtom } from "../../../state/books.atom";
import { bookStatus, bookStatusAPI } from "../../../enums/books.enum";

export function ReviewBookSection() {
  const setIsModalVisible = useSetRecoilState(modalVisibleAtom)
  const setIsLoading = useSetRecoilState(LoaderAtom)
  const [{ title, id }, setRequestedBook] = useRecoilState(requestedBookAtom)
  const [borrowedBooks, setBorrowedBooks] = useRecoilState(borrowedBooksAtom)

  const handleApprove = async (status) => {
    try {
      setIsLoading(true)
      const response = await borrowService.updateRequestBorrow(id, status)
      console.debug(response)
      const updatedBooks = borrowedBooks?.map((book) => {
        if (book.id === response.id) return response
        return book
      })
      setBorrowedBooks(updatedBooks)
      alert(`Empréstimo ${bookStatus[status]}`)
    } catch (e) {
      alert('Ocorreu um erro na atualizaçao do status do empréstimo')
      console.debug(e)
    } finally {
      setIsLoading(false)
      setRequestedBook({})
      setIsModalVisible(null)
    }
  }
  return (
    <div className="modalSection">
      <div className="sectionHeader">
        <h2>Aprovar empréstimo</h2>
        <p className="subtitle">{title}</p>
      </div>
      
      <div className="modalButtonBox">
        <Button type="button" text="Cancelar" outlined onPress={() => handleApprove(bookStatusAPI.REJECTED)} />
        <Button type="button" text="Aprovar" filled onPress={() => handleApprove(bookStatusAPI.ACCEPTED)} />
      </div>
    </div>
  );
}
