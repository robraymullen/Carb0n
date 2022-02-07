export const fetchIds = (resolve: Function, reject: Function) => {
    const headers = { 'Content-Type': 'application/json' };
    fetch('http://localhost:7070/config/virtual/ids', {headers})
        .then(response => response.json())
        .then(data => {
            resolve(data)
        })
        .catch((error) => {
            console.error(error);
            reject();
        });
};