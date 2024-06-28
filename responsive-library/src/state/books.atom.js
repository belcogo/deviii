import { atom } from "recoil";

export const myBooksAtom = atom({
  key: 'myBooksAtom',
  default: [],
})

export const othersBooksAtom = atom({
  key: 'othersBooks',
  default: [],
})