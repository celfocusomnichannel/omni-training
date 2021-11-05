import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';
import classNames from '@ui-lib/core/classNames';
import Dialog from '@ui-lib/core/Dialog';
import DialogTitle from '@ui-lib/core/DialogTitle';
import DialogContent from '@ui-lib/core/DialogContent';
import DialogActions from '@ui-lib/core/DialogActions';
import Button from '@ui-lib/core/Button';
import IconButton from '@ui-lib/core/IconButton';
import Icon from '@ui-lib/core/Icon';
import Typography from '@ui-lib/core/Typography';

import styles from './styles';

function DeleteModal({ open, onClose, handleDeleteCategory, selectedCategory, classes }) {
    const { I18nProvider } = useSelector((store) => {
        return {
            I18nProvider: store.journey.services.I18nProvider
        };
    });

    const [name, setName] = useState('');

    const handleDelete = () => {
        handleDeleteCategory(name);
        onClose();
        setName('');
    };

    return (
        <Dialog fullWidth open={open} onClose={onClose}>
            <DialogTitle>
                <Typography className={classes.title} component={'div'} variant={'h1'} readOnly>
                    {I18nProvider.Labeli18n('category_management_dialog_delete_title')}
                </Typography>
                <IconButton className={classes.close} onClick={onClose}>
                    <Icon className={classNames('icon-close', classes.icon)} />
                </IconButton>
            </DialogTitle>
            <DialogContent>
                <Typography className={classes.title} component={'div'} variant={'body1'} readOnly>
                    {I18nProvider.Labeli18n('category_management_dialog_delete_text1')}
                    <b>{selectedCategory}</b>
                    {I18nProvider.Labeli18n('category_management_dialog_delete_text2')}
                </Typography>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" color="primary" onClick={handleDelete}>
                    {I18nProvider.Texti18n('category_management_dialog_delete_button')}
                </Button>
            </DialogActions>
        </Dialog>
    );
}

export default withStyles(styles)(DeleteModal);
