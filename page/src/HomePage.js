import React, {Component} from 'react';
import './HomePage.css';
import axios from 'axios'

class HomePage extends Component {

    constructor(props) {
        super(props)
        this.datalogInput = React.createRef();
        this.state = {
            json: ''
        };
    }

    convert = () => {
        axios.post('https://datalog-to-json.appspot.com/toJson', {datalog: this.datalogInput.current.value})
            .then((response) => {
                this.setState({
                    json: JSON.stringify(JSON.parse(response.data.translation), undefined, 1)
                });
            })
            .catch((error) => {
                this.setState({
                    json: error.response.data
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
                    <textarea id="input" ref={this.datalogInput}></textarea>
                    <button onClick={this.convert}>Convert</button>
                </div>
                <hr/>
                <div className="center">
                    <h4>JSON</h4>
                    <pre>{this.state.json}</pre>
                </div>
            </div>
        );
    }
}

export default HomePage;