import React, {Component} from 'react';
import './HomePage.css';
import axios from 'axios'

class HomePage extends Component {
    state = {
        json: ''
    };

    convert = () => {
        axios.post('https://datalog-to-json.appspot.com/toJson', {
            datalog: document.getElementById('input').value
        })
            .then((response) => {
                this.setState({
                    json: JSON.stringify(response.data, null, '\t')
                });
            })
            .catch((error) => {
                this.setState({
                    json: error.response
                });
            });
    };

    render() {
        return (
            <div>
                <div className="center">
                    <h2>Home</h2>
                </div>
                <hr/>
                <div className="center">
                    <h4>Datalog</h4>
                    <textarea id="input"></textarea>
                    <button onClick={this.convert}>Convert</button>
                </div>
                <hr/>
                <div className="center">
                    <h4>JSON</h4>
                    <code>{this.state.json}</code>
                </div>
            </div>
        );
    }
}

export default HomePage;