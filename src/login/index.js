import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './style.css'

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            username: "",
            password: "",
            users: []
         }
    }

    setValue = el => {
        this.setState({
            [el.target.name]: el.target.value
        })
    }

    componentDidMount() {
        //user
        fetch(`https://jsonplaceholder.typicode.com/users`)
            .then(results => results.json())
            .then(json => {
                this.setState({
                    // isLoaded: true,
                    users: json
                })
            })
    }

    render() { 
        const { username , password , users } = this.state
        console.log(this.state)
        return ( 
            <div>
                <div className="container-login">
                    <div className="title-login">
                        <h3 className="login-title">Login User</h3>
                    </div>
                    <div className="username-container">
                        <div className="label">
                            <label for="inputPassword" class="col-sm-2 col-form-label">Username</label>
                        </div>
                        <div className="input-username">
                            <input type="text" name="username" class="form-control" id="inputPassword" onChange={this.setValue}/>                    
                        </div>
                    </div>
                    <div className="password-container">
                        <div className="label">
                            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                        </div>
                        <div className="input-password">
                            <input type="password" name="password" class="form-control" id="inputPassword" onChange={this.setValue}/>                    
                        </div>
                    </div>
                    <div className="button-container">
                        <button type="button" class="btn btn-success" onClick={() => this.props.login({ username , password , users })}>Login</button>
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Login;