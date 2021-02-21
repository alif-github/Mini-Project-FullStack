import React, { Component } from 'react';
import { connect } from "react-redux"
import { Router } from 'react-router-dom';
import App from './App';
import Login from './login'

class AppXtra extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            username: "",
            password: ""
         }
    }

    findIndex = (username,users) => {
        //findIndex untuk mencari user
        const indexing = users.findIndex((element) => {
            if(element.username === username) {
                return true
            }
        })
        return indexing
    }

    doLogin = obj => {
        console.log("masuk sini")
        const { username , password , users } = obj
        let indeksnya = this.findIndex(username , users)
        console.log("indeksnya adalah : ", indeksnya)
        if (indeksnya > -1 && password === "admin") {
            this.props.submitLogin({ username })
            localStorage.setItem("username",username)
            alert("Success login : Hello "+username)
        } else alert("Invalid username/password!!")
    }

    showPage = () => {
        if (this.props.checkLogin) {
            return (
                <><App/></>
            )
        }
        return (
            <>
                <Login login={this.doLogin}/>
            </>
        )
    }

    render() { 
        return ( 
            <div className="App">
                {this.showPage()}
            </div>
         );
    }
}

const mapStateToProps = state => ({
    checkLogin: state.AuthReducer.isLogin,
    userLogin: state.AuthReducer.username
})

const mapDispatchToProps = dispatch => {
    return {
        submitLogin: payload => dispatch({ type: "LOGIN_SUCCESS", payload })
    }
}
 
export default connect(mapStateToProps, mapDispatchToProps)(AppXtra);