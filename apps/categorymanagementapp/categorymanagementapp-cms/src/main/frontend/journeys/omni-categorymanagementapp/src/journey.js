import React, { useEffect, useState } from 'react';
import { deleteCategory, addCategory, updateCategory } from './services/categoriesService';
import { withRootHoc } from 'omni-journey';
import { useSelector } from 'react-redux';
import CategoryList from './Components/CategoryList';
import { getCategories } from './services/categoriesService';

function Journey() {
    const { HttpClient } = useSelector((store) => {
        return {
            HttpClient: store.journey.services.HttpClient
        };
    });

    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = () => {
        getCategories(HttpClient).then((categories) => {
            setData(categories);
        });
    };

    const handleDelete = (selectedId) => {
        deleteCategory(HttpClient, selectedId).then(() => {
            console.log('deleted category');
            fetchData();
        });
    };

    const handleEdit = (id, name) => {
        updateCategory(HttpClient, id, name).then(() => {
            console.log('updated category');
            fetchData();
        });
    };

    const handleAdd = (name) => {
        addCategory(HttpClient, name).then(() => {
            console.log('added category');
            fetchData();
        });
    };

    return <CategoryList data={data} handleDelete={handleDelete} handleAdd={handleAdd} handleEdit={handleEdit} />;
}

export default withRootHoc(Journey);
