import React, {Component} from 'react';
import './App.css';
import HomePage from './HomePage';
import AboutPage from './AboutPage';

class App extends Component {

    state = {
        page: "Home"
    };

    goToHome = () => {
        this.setState({
            page: "Home"
        });
    };

    goToAbout = () => {
        this.setState({
            page: "About"
        });
    };

    render() {
        let page;

        switch (this.state.page) {
            case "Home":
                page = <HomePage/>;
                break;
            case "About":
                page = <AboutPage/>;
        }

        return (
            <div className="App">
                <nav>
                    <ul>
                        <li>
                            <button onClick={this.goToHome}>Home</button>
                        </li>
                        <li>
                            <button onClick={this.goToAbout}>About</button>
                        </li>
                    </ul>
                </nav>
                {page}
            </div>
        );
    }

}

export default App;
