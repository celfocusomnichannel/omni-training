import React from 'react';
import { useDispatch } from 'react-redux';
import { withRootHoc } from 'omni-journey';
import { initializeUsersHttpClientService } from './redux/users/httpActions';
import Root from './root';

const Journey = () => {
    const dispatch = useDispatch();
    React.useState(() => {
        dispatch(initializeUsersHttpClientService());
    });

    return <Root />;
};

export default withRootHoc(Journey);
    