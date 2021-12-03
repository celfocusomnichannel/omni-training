import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';
import classNames from '@ui-lib/core/classNames';
import Dialog from '@ui-lib/core/Dialog';
import DialogTitle from '@ui-lib/core/DialogTitle';
import DialogContent from '@ui-lib/core/DialogContent';
import DialogActions from '@ui-lib/core/DialogActions';
import TextField from '@ui-lib/core/TextField';
import Button from '@ui-lib/core/Button';
import IconButton from '@ui-lib/core/IconButton';
import Icon from '@ui-lib/core/Icon';
import Typography from '@ui-lib/core/Typography';

import styles from './styles';

function EditModal({ open, onClose, handleEditCategory, currentName, classes }) {
    const { I18nProvider } = useSelector((store) => {
        return {
            I18nProvider: store.journey.services.I18nProvider
        };
    });

    const [name, setName] = useState(currentName);

    useEffect(() => {
        setName(currentName);
    }, [currentName]);

    const handleChange = (evt) => {
        setName(evt.target.value);
        console.log(name);
    };

    const handleUpdate = () => {
        handleEditCategory(name);
        onClose();
        setName('');
    };

    return (
        <Dialog fullWidth open={open} onClose={onClose}>
            <DialogTitle>
                <Typography className={classes.title} component={'div'} variant={'h1'} readOnly>
                    {I18nProvider.Labeli18n('category_management_dialog_edit_title')}
                </Typography>
                <IconButton className={classes.close} onClick={onClose}>
                    <Icon className={classNames('icon-close', classes.icon)} />
                </IconButton>
            </DialogTitle>
            <DialogContent>
                <TextField fullWidth id="category-name" label={I18nProvider.Texti18n('category_management_dialog_edit_input')} margin="normal" required value={name} onChange={handleChange} />
            </DialogContent>
            <DialogActions>
                <Button variant="contained" color="primary" onClick={handleUpdate}>
                    {I18nProvider.Labeli18n('category_management_dialog_edit_button')}
                </Button>
            </DialogActions>
        </Dialog>
    );
}

export default withStyles(styles)(EditModal);