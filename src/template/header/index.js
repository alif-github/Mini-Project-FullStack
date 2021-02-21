import React, { Component } from 'react';
import './style.css'

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = { }
    }
    render() { 
        return ( 
            <div>
                <div className="container-header">
                    <div className="title-header">
                        <h4 className="title-header-content">APLIKASI PENJUALAN PULSA</h4>
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Header;