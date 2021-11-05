import React from 'react';
import UserDetails from './components/UsersPage';
import Typography from '@ui-lib/core/Typography';
import Icon from '@ui-lib/core/Icon';
import { withStyles } from '@ui-lib/core/styles';
import { useSelector } from 'react-redux';

const styles = (theme) => ({
    rootHeader: {
        marginTop: '5vh'
    },
    root: {
        textAlign: 'center'
    },
    bodyResume: {
        marginTop: '5vh',
        marginLeft: '10vw',
        marginRight: '10vw'
    },
    links: {
        color: theme.palette.primary.main,
        '&:hover': {
            cursor: 'pointer'
        }
    },
    introductionExample: {
        marginLeft: '10vw',
        marginRight: '10vw',
        marginTop: '10vh'
    }
});

function Root({ classes }) {
    const JourneyActions = useSelector((store) => store.journey.services.JourneyActions);

    function openJourney(id) {
        JourneyActions.openJourney(id);
    }

    return (
        <div className={classes.root}>
            <div className={classes.rootHeader}>
                <Typography variant={'h1'}>Welcome to the development of journeys inside the Unified Frontend</Typography>
                <Typography variant={'h2'}>This application is an inicial seed where you can start developing.</Typography>
                <Typography variant={'body1'} className={classes.bodyResume}>
                    Please checkout the{' '}
                    <span className={classes.links} onClick={() => openJourney(12)}>
                        Application Manual
                    </span>{' '}
                    and{' '}
                    <span className={classes.links} onClick={() => openJourney(2)}>
                        Shell Services
                    </span>{' '}
                    (for more information about development and journeys) and the{' '}
                    <span className={classes.links} onClick={() => openJourney(4)}>
                        UI Components Library
                    </span>{' '}
                    (for more information about the default components that already exist). Additionally remeber to check the code and the comments that exist in this application.
                </Typography>
            </div>
            <Typography variant={'h2'} color={'primary'} className={classes.introductionExample}>
                Below, there is an example (using mocks) on how to use the http client and redux together to manage data. Take a look at the code, structure and comments and try it out:
            </Typography>
            <UserDetails />
        </div>
    );
}

export default withStyles(styles)(Root);
