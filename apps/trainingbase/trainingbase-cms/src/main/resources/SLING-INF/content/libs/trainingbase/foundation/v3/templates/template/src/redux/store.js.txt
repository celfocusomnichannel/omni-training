import { createStoreSingleton } from 'omni-journey';

import users from './users/reducer';

const reducers = { users };

const store = createStoreSingleton(reducers);

export default store;
