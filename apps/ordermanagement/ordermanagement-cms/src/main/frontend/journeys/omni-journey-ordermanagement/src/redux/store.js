import { createStoreSingleton } from 'omni-journey';

import products from './products/reducer';

const reducers = { products };

const store = createStoreSingleton(reducers);

export default store;
