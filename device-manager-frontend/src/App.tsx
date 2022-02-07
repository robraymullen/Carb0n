import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { fetchIds } from './services/DeviceService';
import SockJsClient from 'react-stomp';
import { Status } from './Types';
import DeviceStatus from './components/DeviceStatus/DeviceStatus';

function App() {

  const [ids, setIds] = useState<string[]>([]);
  // const devices = new Map<string, Status>();

  const [devices, setDevices] = useState(new Map());

  const runUpdate = (ids: string[]) => {
    setIds(ids);
    ids.forEach((id) => {
      console.log("SETTING device: "+id);
      devices.set(id, {
        id: id,
        status: '',
        preFilter: 0,
        postFilter: 0
      });
    });
  };

  useEffect(() => {
    fetchIds(runUpdate, ()=> {
      console.log("ERROR fetching ids")
      console.log("HERE");
    });
    
  }, [])

  const updateDevice = (id: string, status: Status) => {
    // setState((prev) => new Map(prev).set(key, value));
    const device = devices.get(id);
    if (device) {
      setDevices((previous) => new Map(previous).set(id, {
        status: status.status,
        postFilter: status.postFilter,
        preFilter: status.preFilter,
        id: status.id
      }));
    }
  };

  return (
    <div className="App">
      <SockJsClient url='http://localhost:7070/sensor-data' topics={['/topic/error']}
            onMessage={(message: any) => {
              updateDevice(message.id, message);
                console.log(message);
                }}/>
      <SockJsClient url='http://localhost:7070/sensor-data' topics={['/topic/success']}
            onMessage={(message: any) => {
              updateDevice(message.id, message);
                console.log(message);
                }}/>
      {
        ids.map(id => {
          return <DeviceStatus id={id} status={devices.get(id)} ></DeviceStatus>
        })
      }
    </div>
  );
}

export default App;
