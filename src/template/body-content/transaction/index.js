import React, { Component } from 'react';
import './style.css'

class Transaction extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            error: null,
            isLoaded: false,
            transactionItems: []
         }
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/transaction/page/?page=1&limit=4")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                      isLoaded: true,
                      transactionItems: result
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() { 
        const { error , isLoaded , transactionItems } = this.state

        //if error

        if (error) {
            return (
                <div>
                    <div className="container-provider">
                        <div className="table-content">
                            <table className="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th className="tText">idTransaction</th>
                                        <th className="tText">transactionDate</th>
                                        <th className="tText">phoneNumber</th>
                                        <th className="tText">productList</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="4">
                                            Error for fetching data, connection refused.
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            )
        } 

        //if Loading..

        else if (!isLoaded) {
            return (
                <div>
                    <div className="container-provider">
                        <div>
                            <table className="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th className="tText">idTransaction</th>
                                        <th className="tText">transactionDate</th>
                                        <th className="tText">phoneNumber</th>
                                        <th className="tText">productList</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="4">
                                            Data Loading, Please Wait ...
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            )
        }

        //if success..

        else {
            return (
                <div>
                    <div className="container-provider">
                        <div className="atas-provider">

                        </div>
                        <div>
                            <div>
                                <table className="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th className="tText">idTransaction</th>
                                            <th className="tText">transactionDate</th>
                                            <th className="tText">phoneNumber</th>
                                            <th className="tText">productList</th>
                                            <th className="tText">price</th>
                                            <th className="tText">qty</th>
                                            <th className="tText">total</th>
                                            <th className="tText">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        { transactionItems.map((item,key) => (
                                                <tr key={key}>
                                                    <td>{item.idTransaction}</td>
                                                    <td>{item.transactionDate}</td>
                                                    <td>{item.phoneNumber}</td>
                                                    <td>{item.productList[0].idProduct}</td>
                                                    <td>{item.productList[0].harga}</td>
                                                    <td>{item.productList[0].qty}</td>
                                                    <td>{item.productList[0].total}</td>
                                                    <td>
                                                        <button type="button" className="btn btn-warning edit-provider" data-bs-toggle="modal" data-bs-target="#exampleModal9">Edit</button>
                                                        <button type="button" className="btn btn-danger hapus-provider">Hapus</button>
                                                    </td>
                                                </tr>
                                            ))}
                                    </tbody>
                                </table>
                                <div className="paggination-section">
                                    {/* <PagginationProv page={this.page} paggination={this.paggination}/> */}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }
}
 
export default Transaction;