import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRootHoc } from 'omni-journey';
import { initializeUsersHttpClientService, getProducts, getCategories, getDeliveryOptions, readUpdateProcess } from './redux/products/httpActions';
import { setInstanceId, setPreferences } from './redux/products/dataActions';
import Root from './root';
import qs from 'qs';
import { createInstance } from './redux/products/httpActions';

const Journey = () => {
    const UserPreferences = useSelector((state) => state.journey.services.UserPreferences);
    const User = useSelector((state) => state.journey.services.User);
    const dependencies = useSelector((state) => state.products.dependencies);
    const [readyDependencies, setReadyDependencies] = React.useState(false);

    const dispatch = useDispatch();
    React.useState(() => {
        dispatch(initializeUsersHttpClientService());
    });

    React.useEffect(() => {
        dispatch(getProducts(1));
        dispatch(getCategories(1));
        dispatch(getDeliveryOptions(1));

        let params = {
            expression: 'path==*ordermanagement*',
            limit: 1,
            sort: 'path;asc'
        };
        let query = qs.stringify(params, { arrayFormat: 'repeat' });
        User.requestUser().then((user) => {
            UserPreferences.queryUserPreferences(user.id, query)
                .then((preference) => {
                    debugger;
                    /**
                     * No preferences found
                     */
                    if (preference && preference.data && preference.data.length < 1) {
                        UserPreferences.createPreference(user.id, { path: 'ordermanagement' }).then((createdPreference) => {
                            dispatch(createInstance());
                            dispatch(setPreferences(createdPreference.data));
                        });
                    } else {
                        dispatch(setPreferences(preference.data[0]));
                        if (preference && preference.data && preference.data[0].properties && preference.data[0].properties.length > 0) {
                            dispatch(setInstanceId(preference.data[0].properties[0].value));
                            dispatch(readUpdateProcess(preference.data[0].properties[0].value));
                        } else {
                            dispatch(createInstance());
                        }
                    }
                })
                .catch((e) => {
                    console.log(e);
                });
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    React.useEffect(() => {
        if (dependencies.fetchedListProducts && dependencies.fetchedCategories && dependencies.fetchedDeliveryOptions && dependencies.fetchedInstance && dependencies.fetchedPreferences) {
            setReadyDependencies(true);
        }
    }, [dependencies]);

    return <div>{readyDependencies ? <Root /> : <div>loading</div>}</div>;
};

export default withRootHoc(Journey);
