import { RequestBookSection } from "../components/modal/sections/request-book.section"
import { ReviewBookSection } from "../components/modal/sections/review-request.section"

export const modals = {
  REQUEST_BOOK: 'REQUEST_BOOK',
  REVIEW_REQUEST: 'REVIEW_REQUEST',
}

export const modalsComponents = {
  [modals.REQUEST_BOOK]: RequestBookSection,
  [modals.REVIEW_REQUEST]: ReviewBookSection,
}