import React from 'react';
import Journey from './journey';
import { Provider } from 'react-redux';
import { name as packageName, parent } from './../package.json';
import { createStoreSingleton } from 'omni-journey';
import { FinalJourney } from 'omni-journey';

/**
 * REDUCERS TO IMPORT
 */
import reducerOne from './redux/reducers/one.js';
// import reducerTwo from '...';

let reducersToMerge = { one: reducerOne };
//let reducersToMerge = {one: reducerOne, two: reducerTwo, ...}

let Store = createStoreSingleton(reducersToMerge);

export default class App extends React.Component {
    render = () => {
        return (
            <Provider store={Store}>
                <div style={{ height: 'calc(100vh-24px)' }}>
                    <RenderJourney />
                </div>
            </Provider>
        );
    };
}

let RenderJourney = FinalJourney(Journey, Store, packageName, parent);
