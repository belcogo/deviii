import { useState } from "react";
import { Button } from "../../button/button.component";
import { Input } from "../../input/input.component";
import './style.css'
import { useRecoilState, useSetRecoilState } from "recoil";
import borrowService from '../../../services/borrowService'
import { modalVisibleAtom } from "../../../state/modal.atom";
import { LoaderAtom } from "../../../state/loader.atom";
import { requestedBookAtom } from "../../../state/books.atom";

export function RequestBookSection() {
  const [address, setAddress] = useState('')
  const [date, setDate] = useState('')
  const [time, setTime] = useState('')
  const setIsModalVisible = useSetRecoilState(modalVisibleAtom)
  const setIsLoading = useSetRecoilState(LoaderAtom)
  const [{ title, id }, setRequestedBook] = useRecoilState(requestedBookAtom)

  const changeAddress = (e) => setAddress(e?.target?.value)
  const changeDate = (e) => setDate(e?.target?.value)
  const changeTime = (e) => setTime(e?.target?.value)

  const handleSubmit = async () => {
    console.debug(address, date, time)
    try {
      setIsLoading(true)
      await borrowService.requestBorrow(id)
      alert('Empréstimo solicitado')
    } catch (e) {
      alert('Ocorreu um erro na solicitação do empréstimo')
      console.debug(e)
    } finally {
      setIsLoading(false)
      setRequestedBook({})
      setIsModalVisible(null)
    }
  }

  const handleCancel = () => {
    setRequestedBook({})
    setIsModalVisible(null)
  }

  return (
    <div className="modalSection">
      <div className="sectionHeader">
        <h2>Solicitar empréstimo</h2>
        <p className="subtitle">{title}</p>
      </div>
      <form className="form">
        <div>
          <Input label="Local de entrega" type="text" onChange={changeAddress} />
          <Input label="Data de entrega" type="date" keepFocusedLayout onChange={changeDate} />
          <Input label="Horário" type="time" keepFocusedLayout onChange={changeTime} />
        </div>
        <div className="modalButtonBox">
          <Button type="button" text="Cancelar" outlined onPress={handleCancel} />
          <Button type="button" text="Solicitar" filled onPress={handleSubmit} />
        </div>
      </form>
    </div>
  );
}
