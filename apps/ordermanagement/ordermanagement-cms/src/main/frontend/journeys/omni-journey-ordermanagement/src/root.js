import React from 'react';
import StepWizard from './components/StepWizard';
import { useSelector } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';

const styles = (theme) => ({});

function Root({ classes }) {
    const [readyInstance, setReadyInstance] = React.useState(false);
    const instance = useSelector((state) => state.products.instance);
    const preferences = useSelector((state) => state.products.preferences);

    const UserPreferences = useSelector((state) => state.journey.services.UserPreferences);
    const User = useSelector((state) => state.journey.services.User);

    React.useEffect(() => {
        User.requestUser().then((user) => {
            if (preferences.properties.length < 1) {
                UserPreferences.createProperty(user.id, preferences.id, { name: 'instanceId', description: 'instanceId of ordermanagement', value: instance.jwcontext.id }).then(() => {
                    setReadyInstance(true);
                });
            } else {
                setReadyInstance(true);
            }
            console.log(instance);
            console.log(preferences);
        });
    }, []);

    return <div>{readyInstance ? <StepWizard /> : <div>loading</div>}</div>;
}

export default withStyles(styles)(Root);
