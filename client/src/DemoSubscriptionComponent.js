import React from 'react';
import { Query } from "react-apollo";
import { GET_ALL_HELLO_WORLDS, HELLO_WORLD_UPDATES } from "./Queries"
import HelloWorldCrudComponent from "./HelloWorldCrudComponent"

export class DemoQueryComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = { helloWorldUpdates: [] };
	}

	render() {
		return (
			<div>
				<h3>Subscription:</h3>
				<p>A continuous stream of CRUD on `HelloWorld`s`:</p>

				<Query query={GET_ALL_HELLO_WORLDS} >
					{({ loading, error, data, subscribeToMore }) => {
						if (loading) return <p>Loading...</p>;
						if (error) return <p>Error: {error.message}</p>;

						const subscribeToNewHelloWorlds = () =>
							subscribeToMore({
								document: HELLO_WORLD_UPDATES,
								updateQuery: (prev, { subscriptionData }) => {
									if (!subscriptionData.data) return prev;
									const newHelloWorld = subscriptionData.data.helloWorldUpdates;
								
									return {
										getHelloWorlds: prev.getHelloWorlds.concat(newHelloWorld)
									}

									//This code wont show new entities that are already in the cache...only updates and deletes.
									// return Object.assign({}, prev, {
									// 	entry: {
									// 		getHelloWorlds: [newHelloWorld, ...prev.entry.helloWorldUpdates]
									// 	}
									// })
								}
							})

						return (<HelloWorldCrudComponent data={data} subscribeToNewHelloWorlds={subscribeToNewHelloWorlds} />);

					}}
				</Query>

				{/* The solution below works for displaying the updates one at a time; 
						but to maintain a client side aggregate of the updates occuring 
						during a subscription, the above will do that */}

				{/* <Subscription
					subscription={HELLO_WORLD_UPDATES}
				>
					{({ loading, error, data }) => {
						if (loading) return <p>Perform some CRUD to see some HelloWorlds...</p>;
						if (error) return <p>Error :(</p>;

						const { id, name, dmlType } = data.helloWorldUpdates;

						return (<li key={id}>{`CRUD Type: ${dmlType} ID: ${id} Name: ${name}`}</li>);

					}}
				</Subscription> */}

			</div>
		);
	}
}

export default DemoQueryComponent;
