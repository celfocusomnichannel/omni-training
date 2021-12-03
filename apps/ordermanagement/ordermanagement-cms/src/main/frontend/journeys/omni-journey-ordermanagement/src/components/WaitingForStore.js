import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { readUpdateProcess } from '../redux/products/dataActions';

export default function WaitingForStore() {
    const { RealTime, instance } = useSelector((state) => {
        return {
            RealTime: state.journey.services.RealTime,
            instance: state.products.instance
        };
    });
    const [rt, setRT] = React.useState(new RealTime());
    const dispatch = useDispatch();

    React.useEffect(() => {
        rt.addEvent('onopen', handleOnOpen);
        rt.addEvent('onmessage', handleOnMessage);
        rt.addEvent('onerror', handleOnError);
        rt.addEvent('onclose', handleOnClose);

        rt.connect('localhost:4502/ordermanagement/v1/websocket/' + instance.jwcontext.id);
    }, []);

    function handleOnOpen(event) {
        console.log('A connection has been established');
        console.log(event);
    }

    function handleOnMessage(event) {
        console.log('A message has been sent by the server');
        let newInstance = Object.assign({}, {}, instance);
        let data = JSON.parse(event.data);
        if (data.state && data.status && data.updatedBy) {
            newInstance.jwcontext.state = data.state;
            newInstance.jwcontext.status = data.status;
            dispatch(readUpdateProcess(newInstance));
        }

        console.log(event);
    }

    function handleOnError(event) {
        console.log('An error message has been received');
        console.log(event);
    }

    function handleOnClose(event) {
        console.log('The Connection has been closed');
        console.log(event);
    }

    return <div>...Waiting for the store</div>;
}
