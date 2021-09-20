import React, { useEffect } from 'react';
import UserDetailsRender from './UserDetailsRender';
import { useSelector, useDispatch } from 'react-redux';
import { getUsers } from '../../../redux/users/httpActions';

function UserDetails({}) {
    const users = useSelector((store) => store.users);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getUsers());
    }, []);

    return (
        <div>
            <UserDetailsRender users={users} />
        </div>
    );
}

export default UserDetails;
