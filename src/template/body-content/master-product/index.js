import React, { Component } from 'react';
import PagginationProv from './paggination'
import './style.css'

class Product extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            error: null,
            isLoaded: false,
            productItems: [],
            providerItems: [],
            update: [],
            product: "",
            idProvider: "",
            idType: "",
            value: "",
            stock: "",
            harga: "",
            page: 1
         }
    }

    setValueUpdateData = idProduct => {
        console.log("idProduct : ", idProduct)

        //get by id
        fetch("http://localhost:8080/api/product/id/?id="+ idProduct +"")
                .then(res => res.json())
                .then(
                    (result) => {
                        console.log("check: ", result)
                        this.setState({
                        isLoaded: true,
                        update: result
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
                .catch(
                    console.log("data tidak ada!")
                )
    }

    deleteApi = idProduct => {
        let confirmation = window.confirm("Are you sure for deleting this " + idProduct + "")
        if (confirmation) {
            const requestOptions = {
                method: 'DELETE'
              };
            
              // Note: I'm using arrow functions inside the `.fetch()` method.
              // This makes it so you don't have to bind component functions like `setState`
              // to the component.
              fetch("http://localhost:8080/api/product/id/?id=" + idProduct + "", requestOptions)
                .then((response) => {
                    return response.json();
                })
                .then((result) => {
                    // do what you want with the response here
                    console.log(result.errorMessage)
                    if (result.errorMessage) alert(result.errorMessage)
                    else alert("Success deleting "+idProduct+"")
                })
                .catch();
        } else {
            alert("Data keep in database, thank you!")
        }
    }

    setValue = el => {
        this.setState({
            [el.target.name]: el.target.value
        })
    }

    tambahApi = () => {
        let { product , value , idProvider , idType , stock , harga , expiredDate } = this.state

        // Simple POST request with a JSON body using fetch
        const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            product: '' + product +'',
            value: '' + value + '',
            idProvider: '' + idProvider + '',
            idType: '' + idType + '',
            stock: '' + stock + '',
            harga: '' + harga + '',
            expiredDate: '' + expiredDate + ''
         })
        };

        fetch('http://localhost:8080/api/product/', requestOptions)
            .then(response => response.json())
            .then((result) => {
                // do what you want with the response here
                if (result.errorMessage) alert(result.errorMessage)
                else alert("Success Input "+product+"")
            })
            .catch();
    }

    paggination = page => {
        console.log("http://localhost:8080/api/product/page/?page="+page+"&limit=3")

        fetch("http://localhost:8080/api/product/page/?page="+page+"&limit=3")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                      isLoaded: true,
                      productItems: result,
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error,
                    });
                }
            )
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/product/page/?page=1&limit=3")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                      isLoaded: true,
                      productItems: result
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

        fetch("http://localhost:8080/api/provider/")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        providerItems: result
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
        const {error , isLoaded , providerItems , productItems , page} = this.state
        //if error

        if (error) {
            return (
                <div>
                    <div className="container-provider">
                        <div className="table-content">
                            <table className="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th className="tText">idProduct</th>
                                        <th className="tText">Product</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Type</th>
                                        <th className="tText">Value</th>
                                        <th className="tText">Stock</th>
                                        <th className="tText">Price</th>                                    
                                        <th className="tText">Exp-Date</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="8">
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
                                        <th className="tText">idProduct</th>
                                        <th className="tText">Product</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Type</th>
                                        <th className="tText">Value</th>
                                        <th className="tText">Stock</th>
                                        <th className="tText">Price</th>                                    
                                        <th className="tText">Exp-Date</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="8">
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
                            <div className="find-provider">
                                <div className="input-group mb-3">
                                    <span className="input-group-text" id="inputGroup-sizing-default">Find Product</span>
                                    <input type="text" className="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" onChange={el => this.findByName(el)}/>
                                </div>
                            </div>
                            <div className="find-add">
                                {/* <!-- Button trigger modal --> */}
                                    <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    +
                                    </button>

                                {/* <!-- Modal --> */}
                                <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div className="modal-dialog">
                                        <div className="modal-content">
                                            <div className="modal-header">
                                                <h5 className="modal-title" id="exampleModalLabel">Input For New Product</h5>
                                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div className="modal-body">
                                                {/* modal-container */}
                                                <div className="container-input">
                                                    <div className="container-input-atas">
                                                        <div className="container-input-kiri">
                                                            <div className="label-input-i">
                                                                <label className="label-input"><span className="title-input">Provider:</span></label>
                                                            </div>
                                                            <div>
                                                                <select className="form-select" name="idProvider" onChange={this.setValue} aria-label="Default select example">
                                                                    <option></option>
                                                                    {
                                                                        providerItems.map((provider,idx) => (
                                                                            <option key={idx} value={provider.idProvider}>
                                                                                {provider.provider}
                                                                            </option>
                                                                        ))
                                                                    }
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div className="container-input-kanan">
                                                            <div className="label-input-i">
                                                                <label className="label-input"><span className="title-input">Type:</span></label>
                                                            </div>
                                                            <div>
                                                                <select className="form-select" name="idType" aria-label="Default select example" onChange={this.setValue}>
                                                                    <option value=""></option>
                                                                    <option value="1">Pulsa</option>
                                                                    <option value="2">Paket Data</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="container-input-bawah">
                                                        <div className="container-input-bawah-1">
                                                            <label className="label-input"><span className="title-input">Product:</span></label>
                                                            <input className="form-control inputan2" name="product" type="text" placeholder="Input New Product" aria-label="default input example" onChange={this.setValue}/>
                                                        </div>
                                                        <div className="container-input-bawah-2">
                                                            <label className="label-input"><span className="title-input">Value:</span></label>
                                                            <input className="form-control inputan2" name="value" type="text" placeholder="Input Product Value (GB or Rp)" aria-label="default input example" onChange={this.setValue}/>
                                                        </div>
                                                        <div className="container-input-bawah-a">
                                                            <div className="container-input-bawah-3">
                                                                <label className="label-input"><span className="title-input">Stock:</span></label>
                                                                <input className="form-control inputan2" name="stock" type="number" min="0" aria-label="default input example" onChange={this.setValue}/>
                                                            </div>
                                                            <div className="container-input-bawah-4">
                                                                <label className="label-input"><span className="title-input">Price:</span></label>
                                                                <input className="form-control inputan2" name="harga" type="number" min="0" aria-label="default input example" onChange={this.setValue}/>
                                                            </div>
                                                        </div>
                                                        <div className="container-input-bawah-5">
                                                            <label className="label-input"><span className="title-input">Exp-Date:</span></label>
                                                            <input className="form-control inputan2" name="expiredDate" type="date" aria-label="default input example" onChange={this.setValue}/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="button" className="btn btn-primary" onClick={() => {this.tambahApi();this.paggination(page)}} data-bs-dismiss="modal">Save changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <table className="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th className="tText">idProduct</th>
                                        <th className="tText">Product</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Type</th>
                                        <th className="tText">Value</th>
                                        <th className="tText">Stock</th>
                                        <th className="tText">Price</th>                                    
                                        <th className="tText">Exp-Date</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    { productItems.map((item,key) => (
                                            <tr key={key}>
                                                <td>{item.idProduct}</td>
                                                <td>{item.product}</td>
                                                <td>{item.provider}</td>
                                                <td>{item.type}</td>
                                                <td>{item.value}</td>
                                                <td>{item.stock}</td>
                                                <td>{item.harga}</td>
                                                <td>{item.expiredDate}</td>
                                                <td>
                                                    <button type="button" className="btn btn-warning edit-provider" data-bs-toggle="modal" data-bs-target="#exampleModal9" onClick={() => this.setValueUpdateData(item.idProduct)}>Edit</button>

                                                        {/* <!-- Modal --> */}
                                                        <div className="modal fade" id="exampleModal9" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div className="modal-dialog">
                                                                <div className="modal-content">
                                                                    <div className="modal-header">
                                                                        <h5 className="modal-title" id="exampleModalLabel">Update Product</h5>
                                                                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div className="modal-body">
                                                                        {/* modal-container */}
                                                                        <div className="container-input">
                                                                            <div className="container-input-atas">
                                                                                <div className="container-input-kiri">
                                                                                    <div className="label-input-i">
                                                                                        <label className="label-input"><span className="title-input">Provider:</span></label>
                                                                                    </div>
                                                                                    <div>
                                                                                        <select className="form-select" name="idProvider" onChange={this.setValue} aria-label="Default select example">
                                                                                            <option></option>
                                                                                            {
                                                                                                providerItems.map((provider,idx) => (
                                                                                                    <option key={idx} value={provider.idProvider}>
                                                                                                        {provider.provider}
                                                                                                    </option>
                                                                                                ))
                                                                                            }
                                                                                        </select>
                                                                                    </div>
                                                                                </div>
                                                                                <div className="container-input-kanan">
                                                                                    <div className="label-input-i">
                                                                                        <label className="label-input"><span className="title-input">Type:</span></label>
                                                                                    </div>
                                                                                    <div>
                                                                                        <select className="form-select" name="idType" aria-label="Default select example" onChange={this.setValue}>
                                                                                            <option value=""></option>
                                                                                            <option value="1">Pulsa</option>
                                                                                            <option value="2">Paket Data</option>
                                                                                        </select>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div className="container-input-bawah">
                                                                                <div className="container-input-bawah-1">
                                                                                    <label className="label-input"><span className="title-input">Product:</span></label>
                                                                                    <input className="form-control inputan2" name="product" type="text" placeholder="Input New Product" value={this.state.update.product} aria-label="default input example" onChange={this.setValue}/>
                                                                                </div>
                                                                                <div className="container-input-bawah-2">
                                                                                    <label className="label-input"><span className="title-input">Value:</span></label>
                                                                                    <input className="form-control inputan2" name="value" type="text" placeholder="Input Product Value (GB or Rp)" aria-label="default input example" onChange={this.setValue}/>
                                                                                </div>
                                                                                <div className="container-input-bawah-a">
                                                                                    <div className="container-input-bawah-3">
                                                                                        <label className="label-input"><span className="title-input">Stock:</span></label>
                                                                                        <input className="form-control inputan2" name="stock" type="number" min="0" aria-label="default input example" onChange={this.setValue}/>
                                                                                    </div>
                                                                                    <div className="container-input-bawah-4">
                                                                                        <label className="label-input"><span className="title-input">Price:</span></label>
                                                                                        <input className="form-control inputan2" name="harga" type="number" min="0" aria-label="default input example" onChange={this.setValue}/>
                                                                                    </div>
                                                                                </div>
                                                                                <div className="container-input-bawah-5">
                                                                                    <label className="label-input"><span className="title-input">Exp-Date:</span></label>
                                                                                    <input className="form-control inputan2" name="expiredDate" type="date" aria-label="default input example" onChange={this.setValue}/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div className="modal-footer">
                                                                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                        <button type="button" className="btn btn-primary" onClick={() => {this.tambahApi();this.paggination(page)}} data-bs-dismiss="modal">Update</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    <button type="button" className="btn btn-danger hapus-provider" onClick={() => {this.deleteApi(item.idProduct)}}>Hapus</button>
                                                </td>
                                            </tr>
                                        ))}
                                </tbody>
                            </table>
                            <div className="paggination-section">
                                <PagginationProv page={this.page} paggination={this.paggination}/>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}
 
export default Product;