import { useRecoilValue } from "recoil";
import { LoaderAtom } from "../../state/loader.atom";
import './loader.styles.css'

export function Loader() {
  const isLoading = useRecoilValue(LoaderAtom)

  return (
    <>
      {
        isLoading && (
          <div className="loaderContainer">
            <p className="loaderText">Carregando..</p>
          </div>
        )
      }
    </>
  )
}