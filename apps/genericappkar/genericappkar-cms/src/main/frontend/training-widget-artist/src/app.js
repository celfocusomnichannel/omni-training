import React from 'react';
import { Provider } from 'react-redux';
import { FinalWidget } from 'omni-widget';
import { createStoreSingleton } from 'omni-widget';
import { name, parent, contentVersion } from '../globals';
import InnerComponent from './widget';
import reducerOne from './redux/reducers/one.js';

let reducersToMerge = { reducerOne: reducerOne };
let Store = createStoreSingleton(reducersToMerge);
let RenderWidget;

export default class Test extends React.Component {
    constructor(props) {
        super(props);
        RenderWidget = FinalWidget(InnerComponent, Store, name, parent, contentVersion, props);
    }

    render() {
        return (
            <Provider store={Store}>
                <RenderWidget {...this.props} />
            </Provider>
        );
    }
}
