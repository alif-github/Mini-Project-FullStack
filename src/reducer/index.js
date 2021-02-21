import { createStore , combineReducers } from "redux"
import AuthReducer from "./auth"

let store = createStore(combineReducers({
    AuthReducer
}))

export default store