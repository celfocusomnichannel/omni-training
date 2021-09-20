import React from 'react';
import Journey from './journey';
import { Provider } from 'react-redux';
import { name as packageName, parent } from './../package.json';
import { FinalJourney } from 'omni-journey';
import store from './redux/store';

const RenderJourney = FinalJourney(Journey, store, packageName, parent);

export default class App extends React.Component {
    handleReceiveMessage() {}

    render() {
        return (
            <Provider store={store}>
                <div>
                    <RenderJourney handleReceiveMessage={this.handleReceiveMessage} />
                </div>
            </Provider>
        );
    }
}
