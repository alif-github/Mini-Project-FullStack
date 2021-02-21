import React, { Component } from 'react';
import { Link, link } from 'react-router-dom'
import './style.css'


class Nav extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        console.log("currentPage : ", this.props.page)
        const { changePage } = this.props

        return ( 
            <div>
                <nav className="main-menu">
                    <ul>
                        <Link to="/home">
                            <li>
                                <i className="fa fa-home fa-2x"></i>
                                <span className="nav-text">
                                    Home
                                </span>
                            </li>
                        </Link>
                        <Link to="/provider">
                            <hr></hr>
                            <li className="has-subnav">
                                <i className="fa fa-folder fa-2x" aria-hidden="true"></i>
                                <span className="nav-text">
                                    Provider
                                </span>
                            </li>
                        </Link>
                        <Link to="/product">
                            <li className="has-subnav">
                                <i className="fa fa-folder fa-2x" aria-hidden="true"></i>
                                <span className="nav-text">
                                    Product
                                </span>
                            </li>
                        </Link>
                        <Link>                    
                            <hr></hr>
                            <li className="has-subnav">
                                <i className="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
                                <span className="nav-text">
                                    Transaction
                                </span>
                            </li>
                        </Link>
                    </ul>
                    <ul className="logout">
                        <li>
                            <a href="#">
                                <i className="fa fa-power-off fa-2x"></i>
                                <span className="nav-text">
                                    Logout
                                </span>
                            </a>
                        </li> 
                    </ul>
                </nav>
            </div>
         );
    }
}
 
export default Nav;