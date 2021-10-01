import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';
import classNames from '@ui-lib/core/classNames';
import CustomTable from '@ui-lib/custom-components/CustomTable';
import Button from '@ui-lib/core/Button';
import Icon from '@ui-lib/core/Icon';
import AddModal from '../Modals/AddModal';
import EditModal from '../Modals/EditModal';
import DeleteModal from '../Modals/DeleteModal';

import styles from './styles';

function CategoryList({ classes, data, handleDelete, handleAdd, handleEdit }) {
    const { I18nProvider } = useSelector((store) => {
        return {
            I18nProvider: store.journey.services.I18nProvider
        };
    });

    const [selectedId, setSelectedId] = useState(null);
    const [openAddModal, setOpenAddModal] = useState(false);
    const [openEditModal, setOpenEditModal] = useState(false);
    const [openDeleteModal, setOpenDeleteModal] = useState(false);
    const [editName, setEditName] = useState('');

    const categories = data.map((item) => {
        return { id: item.categoryId, name: item.categoryName };
    });

    const onSelectPress = (selectedRow) => {
        if (selectedRow === null) {
            setSelectedId(null);
            setEditName('');
            return;
        }
        if (selectedId === selectedRow) {
            setSelectedId(null);
            setEditName('');
        } else {
            const row = categories.findIndex((item) => item.id === selectedRow);
            setSelectedId(selectedRow);
            setEditName(categories[row].name);
        }
    };

    const onPressDelete = () => {
        if (selectedId !== null) {
            setOpenDeleteModal(true);
        }
    };

    const onCloseDeleteModal = () => {
        setOpenDeleteModal(false);
    };

    const handleDeleteCategory = () => {
        handleDelete(selectedId);
        setOpenDeleteModal(false);
        setSelectedId(null);
    };

    const onPressAdd = () => {
        setOpenAddModal(true);
    };

    const onCloseAddModal = () => {
        setOpenAddModal(false);
    };

    const handleAddCategory = (name) => {
        handleAdd(name);
        setOpenAddModal(false);
    };

    const onPressEdit = () => {
        if (selectedId !== null) {
            setOpenEditModal(true);
        }
    };

    const onCloseEditModal = () => {
        setOpenEditModal(false);
    };

    const handleEditCategory = (name) => {
        handleEdit(selectedId, name);
        setOpenEditModal(false);
        setSelectedId(null);
    };

    return (
        <div className={classes.listContainer}>
            <div className={classes.buttonsContainer}>
                <Button className={classes.buttonContainer} size="small" variant="contained" color="primary" onClick={onPressAdd}>
                    <span className={classes.button}>
                        <Icon className={classNames(classes.icon, 'icon-add')} />
                        {I18nProvider.Labeli18n('category_management_button_add')}
                    </span>
                </Button>
                <Button className={classes.buttonContainer} size="small" variant="contained" color="primary" onClick={onPressEdit} disabled={!selectedId}>
                    <span className={classes.button}>
                        <Icon className={classNames(classes.icon, 'icon-edit')} />
                        {I18nProvider.Labeli18n('category_management_button_edit')}
                    </span>
                </Button>
                <Button className={classes.buttonContainer} size="small" variant="contained" color="primary" onClick={onPressDelete} disabled={!selectedId}>
                    <span className={classes.button}>
                        <Icon className={classNames(classes.icon, 'icon-trash-o')} />
                        {I18nProvider.Labeli18n('category_management_button_delete')}
                    </span>
                </Button>
            </div>
            <CustomTable
                headers={[
                    { label: I18nProvider.Texti18n('category_management_table_id'), key: 'id' },
                    { label: I18nProvider.Texti18n('category_management_table_category'), key: 'name' }
                ]}
                data={categories}
                showCheckAll={false}
                rowsSelectable={true}
                onSelectPress={onSelectPress}
                selectedRowsIds={[selectedId]}
            />
            <AddModal open={openAddModal} onClose={onCloseAddModal} handleAddCategory={handleAddCategory} />
            <EditModal currentName={editName} open={openEditModal} onClose={onCloseEditModal} handleEditCategory={handleEditCategory} />
            <DeleteModal selectedCategory={editName} open={openDeleteModal} onClose={onCloseDeleteModal} handleDeleteCategory={handleDeleteCategory} />
        </div>
    );
}

export default withStyles(styles)(CategoryList);
