import React from 'react';
import { Mutation } from "react-apollo";
import { SAVE_HELLO_WORLD, DELETE_HELLO_WORLDS, GET_ALL_HELLO_WORLDS } from "./Queries"

export class DemoMutationComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { name: "", id: null };
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleIdChange = this.handleIdChange.bind(this);
    }

    handleIdChange(event) {
        this.setState({ id: parseInt(event.target.value, 10), renderMutation: false });
    }

    handleNameChange(event) {
        this.setState({ name: event.target.value, renderMutation: false });
    }

    render() {
        return (
            <div>
                <Mutation mutation={SAVE_HELLO_WORLD}>
                    {saveUpdateMutation => (
                        <Mutation mutation={DELETE_HELLO_WORLDS}>
                            {deleteMutation => {
                                const id = this.state.id;
                                const name = this.state.name;
                                return (
                                    <div>
                                        <h3>Mutation:</h3>
                                        <p>Create a `HelloWorld` by inputting a name and clicking 'Create',</p>
                                        <p>Update a `HelloWorld` by supplying an id and a name and clicking 'Update', or</p>
                                        <p>Delete `HelloWorld`s by supplying a name and clicking 'Delete':</p>
                                        <input type="number" name="id" placeholder="Id" onChange={this.handleIdChange} />
                                        <input type="text" name="name" placeholder="Name" onChange={this.handleNameChange} />
                                        <button onClick={() => saveUpdateMutation({ variables: { name: name }, refetchQueries:[{query: GET_ALL_HELLO_WORLDS}] })}>Create</button>
                                        <button onClick={() => saveUpdateMutation({ variables: { id: id, name: name }, refetchQueries:[{query: GET_ALL_HELLO_WORLDS}]})}>Update</button>
                                        <button onClick={() => deleteMutation({ variables: { name: name }, refetchQueries:[{query: GET_ALL_HELLO_WORLDS}]})}>Delete</button>
                                    </div>)
                            }}
                        </Mutation>
                    )}
                </Mutation>
            </div>
        )
    }
}

export default DemoMutationComponent;
