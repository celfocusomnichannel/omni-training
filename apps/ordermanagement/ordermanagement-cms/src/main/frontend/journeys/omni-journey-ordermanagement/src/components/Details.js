import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@ui-lib/core/Typography';
import { withStyles } from '@ui-lib/core/styles';

const styles = (theme) => {
    return {};
};

export default function Details() {
    const instance = useSelector((state) => state.products.instance);
    return (
        <>
            <div>
                <Typography variant={'h1'}>Order:</Typography>
                Address: {instance.order.address} <br />
                Delivery: {instance.order.delivery} <br />
                Id: {instance.order.id}
            </div>
            <div>
                <Typography variant={'h1'}>Products:</Typography>
                {instance.order.products.map((product) => {
                    return (
                        <>
                            Name: {product.productName} <br />
                            Price: {product.productPrice} <br />
                        </>
                    );
                })}
            </div>
        </>
    );
}
