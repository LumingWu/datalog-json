import React, {Component} from 'react';
import './AboutPage.css';

class AboutPage extends Component {
    render() {
        return (
            <div>
                <div class="center">
                    <h2>About</h2>
                </div>
                <hr/>
                <div class="center">
                    <h4>Github</h4>
                    Link: <a href="https://github.com/LumingWu/datalog-json">https://github.com/LumingWu/datalog-json</a>
                </div>
            </div>
        );
    }
}

export default AboutPage;