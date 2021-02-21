import React, { Component } from 'react';
import { Route , Router , Switch , useHistory } from 'react-router-dom'
import Home from './home'
import Provider from './master-provider'
import Product from './master-product'
import AppXtra from '../../AppXtra';
import Login from '../../login';

class Body extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        console.log("page saat ini : ", this.props.page)
        console.log("change page : ", this.props.changePage);
        const { page } = this.props
        const { changePage } = this.props
        //exact--> halaman pertama di buka
        return (
            <Switch>
                <Route path="/home" component={
                    () => {
                        let history = useHistory()
                        return <Home history={history}/>
                    }
                }></Route>
                <Route path="/provider" component={
                    () => {
                        let history = useHistory()
                        return <Provider history={history}/>
                    }
                }></Route>
                <Route path="/product" component={
                    () => {
                        let history = useHistory()
                        return <Product history={history}/>
                    }
                }></Route>

            </Switch>
        )
    }
}
 
export default Body;