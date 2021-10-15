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
import { useSelector, useDispatch } from 'react-redux';
import { createOrder, updateCustomerInfo, submitOrder } from '../redux/products/httpActions';

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
    const instance = useSelector((state) => state.products.instance);
    const [activeStep, setActiveStep] = React.useState(getActiveStepGivenJWE());

    const [name, setName] = React.useState('');
    const [address, setAddress] = React.useState('');
    const defaultDeliveryOptions = useSelector((state) => state.products.deliveryOptions);
    const [deliveryOption, setDeliveryOption] = React.useState(defaultDeliveryOptions[0].name);

    const steps = [
        { name: 'New Order', component: <ProductList /> },
        {
            name: 'Customer Information',
            component: <CustomerInformation setName={setName} setAddress={setAddress} setDeliveryOption={setDeliveryOption} name={name} address={address} deliveryOption={deliveryOption} />
        },
        { name: 'Submit Order', component: <OrderConfirmartion /> },
        { name: 'Waiting for Store', component: null },
        { name: 'Finish', component: null }
    ];

    React.useEffect(() => {
        setActiveStep(getActiveStepGivenJWE());
    }, [instance]);

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

    function handleNext() {
        if (instance.jwcontext.status === 'PRODUCT CHOSEN') {
            dispatch(createOrder(instance.jwcontext.id));
        } else if (instance.jwcontext.status === 'SUBMIT ORDER' && !instance.customer) {
            dispatch(updateCustomerInfo(instance.jwcontext.id, { customer: { name, address }, deliveryOption }));
        } else if (instance.jwcontext.status === 'SUBMIT ORDER') {
            dispatch(submitOrder(instance.jwcontext.id));
        } else if (instance.jwcontext.status === 'END') {
            return 4;
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
                            <Button variant="contained" color="primary" onClick={handleNext}>
                                {activeStep === 0 ? 'Create Order' : activeStep === 1 ? 'Submit Customer Information' : 'Submit Order'}
                            </Button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default withStyles(styles)(StepWizard);
