import React, { Component } from 'react';
import './style.css'

class PagginationProv extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }


    

    render() { 
        // console.log(this.props.rows);
        return (
          <div>
              <div className="container-paggination">
                  <nav aria-label="...">
                    <ul className="pagination pagination-sm">
                        {/* {
                            this.props.rows.map((item,key) => (
                                <div key={key}>
                                    <div>
                                        AAA
                                    </div>
                                </div>
                            ))
                        } */}

                        <li className="page-item" aria-current="page">
                            <a className="page-link" onClick={() => this.props.paggination(1)}>1</a>
                        </li>
                        <li className="page-item">
                            <a className="page-link" onClick={() => this.props.paggination(2)}>2</a>
                        </li>
                        <li className="page-item">
                            <a className="page-link" onClick={() => this.props.paggination(3)}>3</a>
                        </li>
                        <li className="page-item">
                            <a className="page-link" onClick={() => this.props.paggination(4)}>4</a>
                        </li>
                        <li className="page-item">
                            <a className="page-link" onClick={() => this.props.paggination(5)}>5</a>
                        </li>
                    </ul>
                  </nav>
              </div>
          </div>
        )
    }
}
 
export default PagginationProv;