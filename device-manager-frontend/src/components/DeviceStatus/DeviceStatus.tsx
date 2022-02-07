import { useState } from 'react';
import {Device, Status} from '../../Types';
import './DeviceStatus.css';

const DeviceStatus = (device: Device) => {

    const [message, setMessage] = useState<Device>();

    return (
        <div className={`device ${device.status?.status=="ERROR" ? "errorDevice" : "successDevice"}`}>
            <h3>id: {device.id}</h3>
            <p>Status: {device.status?.status}</p>
            <p>PreFilter: {device.status?.preFilter}</p>
            <p>PostFilter: {device.status?.postFilter}</p>
        </div>
    );

};

export default DeviceStatus;