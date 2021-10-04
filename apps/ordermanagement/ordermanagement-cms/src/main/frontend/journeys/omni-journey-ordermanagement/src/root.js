import React from 'react';
import Pricing from './components/Pricing';
import { withStyles } from '@ui-lib/core/styles';

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
    return (
        <div className={classes.root}>
            <Pricing />
        </div>
    );
}

export default withStyles(styles)(Root);
