import React from 'react';

export class HelloWorldCrudComponent extends React.Component {

    componentDidMount() {
        this.props.subscribeToNewHelloWorlds();
    }

    render() {
        const { data } = this.props;

        const result = removeDuplicates(data.getHelloWorlds, 'id');

        return (
            result.map(hw => {
                return (<li key={hw.id}>{`CRUD Type: ${hw.dmlType} ID: ${hw.id} Name: ${hw.name}`}</li>);
            })
        );
    }
}

//Had to use this function because Apollo is doing some weird double rendering...
//We actually end up with duplicate in the cache...hacky...
function removeDuplicates(myArr, prop) {
    return myArr.filter((obj, pos, arr) => {
        return arr.map(mapObj => mapObj[prop]).indexOf(obj[prop]) === pos;
    });
}

export default HelloWorldCrudComponent;
