let defaultState = {
    isLogin: false,
    username: "",
}

//combineReducer
const authReducer = (state = defaultState, action) => {
    console.warn("state : ", state);
    console.warn("action:", action);
    switch (action.type) {
        case "LOGIN_SUCCESS":
            console.log("username nya : ", action.payload.username)
            return {
                isLogin: true,
                username: action.payload.username
            }
        
        case "LOGOUT_SUCCESS":
            return defaultState
        
        //@@@INIT
        default:
            return state
    }
}

export default authReducer