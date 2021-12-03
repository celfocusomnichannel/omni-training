import React from 'react';
import TextField from '@ui-lib/core/TextField';
import Select from '@ui-lib/core/Select';
import MenuItem from '@ui-lib/core/MenuItem';
import Typography from '@ui-lib/core/Typography';
import { useSelector } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';

const styles = () => {
    return {
        select: {
            marginTop: '5vh'
        }
    };
};

function CustomerInformation({ classes, setDeliveryOption, setAddress, setName, name, address, deliveryOption }) {
    const defaultDeliveryOptions = useSelector((state) => state.products.deliveryOptions);

    return (
        <div>
            <TextField id="Client Name" fullWidth label="Name" value={name} onChange={(e) => setName(e.target.value)} margin="normal" />
            <br />
            <TextField id="Address" fullWidth label="Address" value={address} onChange={(e) => setAddress(e.target.value)} margin="normal" />
            <br />
            <div className={classes.select}>
                <Typography>Delivery Option:</Typography>
                <br />
                <Select
                    value={deliveryOption}
                    onChange={(e) => setDeliveryOption(e.target.value)}
                    inputProps={{
                        name: 'delivery-options',
                        id: 'delivert-options-simple'
                    }}
                >
                    {defaultDeliveryOptions.map((option, i) => (
                        <MenuItem value={option.name} key={option.name + i}>
                            {option.name}
                        </MenuItem>
                    ))}
                </Select>
            </div>
        </div>
    );
}

export default withStyles(styles)(CustomerInformation);
