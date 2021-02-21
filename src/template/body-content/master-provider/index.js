import React, { Component } from 'react';
import PagginationProv from './paggination'
import './style.css'

class Provider extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            // rows: [],
            // count: "",
            error: null,
            isLoaded: false,
            providerItems: [],
            page: 1,
            provider: "",
            update: {},
            updatename: ""
         }
    }

    setValue = el => {
        this.setState({
            [el.target.name]: el.target.value
        })
    }

    page = pageNow => {
        this.setState({
            page: pageNow
        })
    }

    deleteApi = idProvider => {
        let confirmation = window.confirm("Are you sure for deleting this " + idProvider + "")
        if (confirmation) {
            const requestOptions = {
                method: 'DELETE'
              };
            
              // Note: I'm using arrow functions inside the `.fetch()` method.
              // This makes it so you don't have to bind component functions like `setState`
              // to the component.
              fetch("http://localhost:8080/api/provider/id/?id=" + idProvider + "", requestOptions)
                .then((response) => {
                    return response.json();
                })
                .then((result) => {
                    // do what you want with the response here
                    console.log(result.errorMessage)
                    if (result.errorMessage) alert(result.errorMessage)
                    else alert("Success deleting "+idProvider+"")
                })
                .catch();
        } else {
            alert("Data keep in database, thank you!")
        }
    }

    tambahApi = () => {
        let provider = this.state.provider
        console.log("provider : ", provider)

        // Simple POST request with a JSON body using fetch
        const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
            idProvider: '',
            provider: '' + provider +'' })
        };

        fetch('http://localhost:8080/api/provider/', requestOptions)
            .then(response => response.json())
    }

    updateApi = () => {
        let updatename = this.state.updatename
        let idProviderTemp = this.state.update.idProvider
        
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                idProvider: '',
                provider: '' + updatename + '' })
        };
        fetch('http://localhost:8080/api/provider/id/?id='+idProviderTemp+'', requestOptions)
            .then(response => response.json())
            .then((result) => {
                // do what you want with the response here
                if (result.errorMessage) alert(result.errorMessage)
                else alert("Success updating , now to be "+updatename+"")
            })
            .catch();
    }

    setValueUpdateData = idProvider => {
        console.log("idProvide : ", idProvider)

        //get by id
        fetch("http://localhost:8080/api/provider/id/?id="+idProvider+"")
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

    findByName = el => {
        const providerName = el.target.value;

        if (providerName === "") {
            this.paggination(this.state.page)
        } else {
            fetch("http://localhost:8080/api/provider/name/?name="+providerName+"")
                .then(res => res.json())
                .then(
                    (result) => {
                        let providerItemsTemp = []
                        providerItemsTemp.push(result)
                        this.setState({
                        isLoaded: true,
                        providerItems: providerItemsTemp
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
    }

    paggination = page => {
        console.log("http://localhost:8080/api/provider/page/?page="+page+"&limit=5")

        fetch("http://localhost:8080/api/provider/page/?page="+page+"&limit=5")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                      isLoaded: true,
                      providerItems: result,
                      pageClicked: page
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error,
                        pageClicked: page
                    });
                }
            )
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/provider/page/?page=1&limit=5")
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
                
            //for know how many the rows
            fetch("http://localhost:8080/api/provider/count/")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                      count: result
                    });
                    // this.looping();
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    this.setState({
                        error
                    });
                }
            )
    }

    // looping = () => {
    //     let countTemp = this.state.count
    //     let rowsTemp = this.state.rows
    //     let countTempNew = 0
    //         if (countTemp % 5 === 0) {
    //             countTempNew = countTemp / 5
    //             console.log("masuk sini 1")
    //         } else {
    //             countTempNew = countTemp % 5
    //             console.log("masuk sini 2")
    //         }
    //     console.log(countTempNew);
        
    //     for (var i = 1; i < countTempNew; i++) {
    //         var indeks = i;
    //         rowsTemp.push(
    //         <a className="page-link" onClick={() => this.props.paggination(indeks)}>{i}</a>
    //         );
    //     }
    //     this.setState({
    //         rows: rowsTemp
    //     })
    // }

    render() { 
        
        const { error , isLoaded , providerItems , update , count } = this.state;
        
        //if error

        if (error) {
            return (
                <div>
                    <div className="container-provider">
                        <div className="table-content">
                            <table className="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th className="tText">idProvider</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="3">
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
                                        <th className="tText">idProvider</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colSpan="3">
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
                                    <span className="input-group-text" id="inputGroup-sizing-default">Find Provider</span>
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
                                                <h5 className="modal-title" id="exampleModalLabel">Input For New Provider</h5>
                                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div className="modal-body">
                                                {/* modal-container */}
                                                <div className="container-input">
                                                    <div className="label-input-i">
                                                        <label className="label-input"><span className="title-input">New Provider</span></label>
                                                    </div>
                                                        <input className="form-control inputan2" name="provider" type="text" placeholder="Input New Provider" aria-label="default input example" onChange={this.setValue}/>
                                                </div>
                                            </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="button" className="btn btn-primary" onClick={() => {this.tambahApi();this.paggination(this.state.page)}} data-bs-dismiss="modal">Save changes</button>
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
                                        <th className="tText">idProvider</th>
                                        <th className="tText">Provider</th>
                                        <th className="tText">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    { providerItems.map((item,key) => (
                                            <tr key={key}>
                                                <td>{item.idProvider}</td>
                                                <td>{item.provider}</td>
                                                <td>
                                                    <button type="button" className="btn btn-warning edit-provider" data-bs-toggle="modal" data-bs-target="#exampleModal9" onClick={() => this.setValueUpdateData(item.idProvider)}>Edit</button>

                                                    {/* <!-- Modal --> */}
                                                    <div className="modal fade" id="exampleModal9" tabIndex="-1" aria-labelledby="exampleModalLabel9" aria-hidden="true">
                                                        <div className="modal-dialog">
                                                            <div className="modal-content">
                                                                <div className="modal-header">
                                                                    <h5 className="modal-title" id="exampleModalLabel9">Update Data</h5>
                                                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div className="modal-body">
                                                                    {/* modal-container */}
                                                                    <div className="container-input">
                                                                        <div>
                                                                            <div className="label-input-i">
                                                                                <label className="label-input"><span className="title-input">Last Name Provider</span></label>
                                                                            </div>
                                                                                <input className="form-control inputan2" value={this.state.update.provider} name="updatename" type="text" aria-label="default input example" disabled/>
                                                                        </div>
                                                                        <div>
                                                                            <div className="label-input-i">
                                                                                <label className="label-input"><span className="title-input">New Name Provider</span></label>
                                                                            </div>
                                                                                <input className="form-control inputan2" name="updatename" onChange={this.setValue} type="text" aria-label="default input example"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div className="modal-footer">
                                                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                    <button type="button" className="btn btn-primary" onClick={() => {this.updateApi();this.paggination(this.state.page)}} data-bs-dismiss="modal">Update</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    {/* Last of Modal */}

                                                    <button type="button" className="btn btn-danger hapus-provider" onClick={() => this.deleteApi(item.idProvider)}>Hapus</button>
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
 
export default Provider;