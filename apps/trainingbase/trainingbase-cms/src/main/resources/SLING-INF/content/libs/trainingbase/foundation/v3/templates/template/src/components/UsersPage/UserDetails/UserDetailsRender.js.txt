import { withStyles } from '@ui-lib/core/styles';
import React from 'react';
import styles from './UserDetailsStyles';
import MobileStepper from '@ui-lib/core/MobileStepper';
import Paper from '@ui-lib/core/Paper';
import Typography from '@ui-lib/core/Typography';
import Button from '@ui-lib/core/Button';
import Icon from '@ui-lib/core/Icon';

function UserDetailsRender({ classes, users }) {
    const [activeStep, setActiveStep] = React.useState(0);
    const maxSteps = users.length;

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    return (
        <div className={classes.root}>
            {users && users.length > 0 && (
                <div className={classes.root}>
                    <Paper square elevation={0} className={classes.header}>
                        <Typography>{users[activeStep].username}</Typography>
                    </Paper>
                    <div>
                        Name: {users[activeStep].name}
                        Username: {users[activeStep].username}
                        CreatedAt: {users[activeStep].createdAt}
                        <img className={classes.img} src={users[activeStep].avatar} alt={users[activeStep].username} />
                    </div>
                    <MobileStepper
                        steps={maxSteps}
                        position="static"
                        variant="text"
                        activeStep={activeStep}
                        nextButton={
                            <Button size="small" onClick={handleNext} disabled={activeStep === maxSteps - 1}>
                                Next
                                <Icon className={'icon-sort-right'} />
                            </Button>
                        }
                        backButton={
                            <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                                <Icon className={'icon-sort-left'} />
                                Back
                            </Button>
                        }
                    />
                </div>
            )}
        </div>
    );
}

export default withStyles(styles, { withName: 'UserDetails' })(UserDetailsRender);
