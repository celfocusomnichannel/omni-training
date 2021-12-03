import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@ui-lib/core/Typography';
import { withStyles } from '@ui-lib/core/styles';

const styles = () => {
    return {
        userInformation: {
            marginTop: '2vh',
            marginBottom: '2vh'
        },
        productInformation: {
            marginTop: '2vh',
            marginBottom: '2vh'
        },
        productSeparation: {
            marginBottom: '1vh'
        },
        price: {
            marginLeft: '85vw',
            marginTop: '40vh'
        }
    };
};

function OrderConfirmartion({ classes }) {
    const instance = useSelector((state) => state.products.instance);

    return (
        <div>
            <div className={classes.userInformation}>
                <Typography variant={'h2'}>Customer Information:</Typography>
                <div>
                    <Typography>Name: {instance.customer.name}</Typography>
                    <Typography>Address: {instance.customer.address}</Typography>
                </div>
            </div>
            <div className={classes.productInformation}>
                <Typography variant={'h2'}>Products:</Typography>
                {instance.products.map((product, i) => (
                    <div className={classes.productSeparation} key={product.productName + i}>
                        <Typography>Product Name: {product.productName}</Typography>
                        <Typography>Price: {product.productPrice}€</Typography>
                    </div>
                ))}
            </div>
            <div className={classes.price}>
                <Typography variant={'h2'}>Total Price: {instance.products.reduce((prev, current) => prev + current.productPrice, 0)}€</Typography>
            </div>
        </div>
    );
}

export default withStyles(styles)(OrderConfirmartion);
