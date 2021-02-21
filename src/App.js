import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router } from "react-router-dom"
import Nav from './template/side-bar'
import Header from './template/header'
import Body from './template/body-content';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      currentPage: ""
     }
  }

  goToPage = page => {
    this.setState({
      currentPage: page
    })
  }

  render() { 
    return ( 
        <div>
          <Router>
            <Header/>
            <Body page={this.state.currentPage} changePage={this.goToPage}/>
            <Nav page={this.state.currentPage} changePage={this.goToPage}/>
          </Router>
        </div>
     );
  }
}

export default App;
