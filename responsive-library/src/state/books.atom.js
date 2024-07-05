import { atom } from "recoil";

export const myBooksAtom = atom({
  key: 'myBooksAtom',
  default: [],
})

export const othersBooksAtom = atom({
  key: 'othersBooks',
  default: [],
})

export const borrowedBooksAtom = atom({
  key: 'borrowedBooks',
  default: [],
})

export const requestedBookAtom = atom({
  key: 'requestedBookAtom',
  default: {
    id: null,
    title: null,
  }
})  