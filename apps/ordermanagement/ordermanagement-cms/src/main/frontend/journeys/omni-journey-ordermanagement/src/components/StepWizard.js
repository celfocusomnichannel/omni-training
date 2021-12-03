import React from 'react';
import { withStyles } from '@ui-lib/core/styles';
import Stepper from '@ui-lib/core/Stepper';
import Step from '@ui-lib/core/Step';
import StepLabel from '@ui-lib/core/StepLabel';
import Button from '@ui-lib/core/Button';
import Typography from '@ui-lib/core/Typography';
import ProductList from './ProductList';
import CustomerInformation from './CustomerInformation';
import OrderConfirmartion from './OrderConfirmation';
import Details from './Details';
import WaitingForStore from './WaitingForStore';
import { useSelector, useDispatch } from 'react-redux';
import { createOrder, updateCustomerInfo, submitOrder } from '../redux/products/httpActions';
import { reset } from '../redux/products/dataActions';

const styles = (theme) => {
    return {
        root: {
            width: '94vw',
            marginLeft: '3vw',
            marginRight: '3vw',
            marginTop: '1vh',
            marginBottom: '1vh',
            height: '85vh'
        },
        navigationButtons: {
            position: 'absolute',
            right: '5vw',
            bottom: '5vh'
        }
    };
};

function StepWizard({ classes }) {
    const dispatch = useDispatch();
    const { instance, UserPreferences, User, preferences, defaultDeliveryOptions } = useSelector((state) => {
        return {
            instance: state.products.instance,
            preferences: state.products.preferences,
            defaultDeliveryOptions: state.products.deliveryOptions,
            UserPreferences: state.journey.services.UserPreferences,
            User: state.journey.services.User
        };
    });
    const [activeStep, setActiveStep] = React.useState(0);

    const [name, setName] = React.useState('');
    const [address, setAddress] = React.useState('');
    const [deliveryOption, setDeliveryOption] = React.useState(defaultDeliveryOptions[0].name);

    const steps = [
        { name: 'New Order', component: <ProductList /> },
        {
            name: 'Customer Information',
            component: <CustomerInformation setName={setName} setAddress={setAddress} setDeliveryOption={setDeliveryOption} name={name} address={address} deliveryOption={deliveryOption} />
        },
        { name: 'Submit Order', component: <OrderConfirmartion /> },
        { name: 'Waiting for Store', component: <WaitingForStore /> },
        { name: 'Finish', component: <Details /> }
    ];

    React.useEffect(() => {
        function getActiveStepGivenJWE() {
            if (instance.jwcontext.status === 'PRODUCT CHOSEN') {
                return 0;
            } else if (instance.jwcontext.status === 'SUBMIT ORDER' && !instance.customer) {
                return 1;
            } else if (instance.jwcontext.status === 'SUBMIT ORDER') {
                return 2;
            } else if (instance.jwcontext.status === 'WAITING FOR \nORDER IN STORE') {
                return 3;
            } else if (instance.jwcontext.status === 'END') {
                return 4;
            }
        }

        setActiveStep(getActiveStepGivenJWE());
    }, [instance]);

    function handleNext() {
        if (instance.jwcontext.status === 'PRODUCT CHOSEN') {
            dispatch(createOrder(instance.jwcontext.id));
        } else if (instance.jwcontext.status === 'SUBMIT ORDER' && !instance.customer) {
            dispatch(updateCustomerInfo(instance.jwcontext.id, { customer: { name, address }, deliveryOption }));
        } else if (instance.jwcontext.status === 'SUBMIT ORDER') {
            dispatch(submitOrder(instance.jwcontext.id));
        } else if (instance.jwcontext.status === 'END') {
            User.requestUser().then((user) => {
                UserPreferences.deleteUserPreference(user.id, preferences.id).then(() => {
                    window.location.reload();
                });
            });
        }
    }

    function getButtonLabel(step) {
        switch (step) {
            case 0:
                return 'Create Order';
            case 1:
                return 'Submit Customer Information';
            case 2:
                return 'Submit Order';
            case 3:
                return;
            case 4:
                return 'Create a new order';
            default:
                return;
        }
    }

    function buttonDisabled(step) {
        switch (step) {
            case 0:
                if (instance.products.length > 0) return false;
                return true;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return false;
            default:
                return;
        }
    }

    return (
        <div className={classes.root}>
            <Stepper activeStep={activeStep}>
                {steps.map((e) => {
                    return (
                        <Step key={e.name}>
                            <StepLabel>{e.name}</StepLabel>
                        </Step>
                    );
                })}
            </Stepper>
            <div>
                {activeStep === steps.length ? (
                    <div>
                        <Typography>All steps completed - you&apos;re finished</Typography>
                        <Button onClick={this.handleReset}>New Order</Button>
                    </div>
                ) : (
                    <div>
                        {steps[activeStep].component}
                        <div className={classes.navigationButtons}>
                            {activeStep !== 3 && (
                                <Button disabled={buttonDisabled(activeStep)} variant="contained" color="primary" onClick={handleNext}>
                                    {getButtonLabel(activeStep)}
                                </Button>
                            )}
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default withStyles(styles)(StepWizard);
