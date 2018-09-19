import React from 'react';
import { Query } from "react-apollo";
import { GET_HELLO_WORLDS_BY_NAME, GET_ALL_HELLO_WORLDS } from "./Queries"

export class DemoQueryComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { name: "", search: false, gql: "" };
        this.handleChange = this.handleChange.bind(this);
        this.setRenderQuery = this.setRenderQuery.bind(this);
    }

    handleChange(event) {
        this.setState({ name: event.target.value, renderQuery: false });
    }

    setRenderQuery() {
        if (this.state.name.length > 0) {
            this.setState({ renderQuery: true, gql: GET_HELLO_WORLDS_BY_NAME});
        } else {
            this.setState({ renderQuery: true, gql: GET_ALL_HELLO_WORLDS});
        }
    }

    render() {
        const queryForm = <div>
            <h3>Query:</h3>
            <p>Read `HelloWorld`s` by inputting a name, or a blank name to return all of them:</p>
            <input type="text" name="name" placeholder="Name" value={this.state.name} onChange={this.handleChange} />
            <button onClick={this.setRenderQuery} >Read</button>
        </div>

        const queryContents = <Query
            query={this.state.gql}
            variables={{ name: this.state.name }}
        >
            {({ loading, error, data }) => {
                if (loading) return <p>Loading...</p>;
                if (error) return <p>Error :(</p>;
                if(this.state.name.length > 0) {
                    return data.getHelloWorldsByName.map(({ id, name }) => (
                        <li key={id}>{`ID: ${id} Name: ${name}`}</li>
                    ));
                } else {
                    return data.getHelloWorlds.map(({ id, name }) => (
                        <li key={id}>{`ID: ${id} Name: ${name}`}</li>
                    ));
                }
            }}
        </Query>;

        if (this.state.renderQuery === true) {
            return (<div>{queryForm} {queryContents}</div>);
        } else {
            return (<div>{queryForm}</div>);
        }
    }
}

export default DemoQueryComponent;
