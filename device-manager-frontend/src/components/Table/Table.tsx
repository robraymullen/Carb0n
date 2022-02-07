import React, { useEffect, useState } from 'react';
import SockJsClient from 'react-stomp';

const Table = () => {

    let ids: string[] = [];

    const [success, setSuccess] = useState<string[]>([]);
    const [error, setError] = useState<string[]>([]);

    useEffect(() => {
        // You need to restrict it at some point
        // This is just dummy code and should be replaced by actual
        if (!ids || ids.length == 0) {
            getIds();
            console.log("Here's the fucking ids: "+ids);
        }
        
      }, []);

    const getIds = async () => {
        await fetch('http://localhost:7070/config/virtual/ids')
            .then((response) => response.json())
            .then((json) => {
                ids = json;
            });
    };

    return (
        <div>
            <SockJsClient url='http://localhost:7070/sensor-data' topics={['/topic/error']}
            onMessage={(message: any) => {
                error.push(message);
                console.log(message);
                }}/>
            <SockJsClient url='http://localhost:7070/sensor-data' topics={['/topic/success']}
            onMessage={(message: any) => {
                success.push(message);
                console.log(message);
                }}/>
            {
                ids.map((id) => {
                    <div>
                        <ul>
                            <li>{id}</li>
                        </ul>
                    </div>
                })
            }
        </div>
    );
};

export default Table;