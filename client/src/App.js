import React, { Component } from 'react';
import ApolloClient from "apollo-boost";
import { ApolloProvider } from "react-apollo";

const client = new ApolloClient({
  uri: "/graphql"
});

class App extends Component {
  render() {
    return (
        <ApolloProvider client={client}>
          <div className="App">
            <h1>Demo Spring Boot / React Js Application:</h1>
            <h2>Real-time CRUD with GraphQL Queries, Mutations, and Subscriptions utilizing ReactiveX & Apollo</h2>
            <h3>Mutation:</h3>
            <p>Save a `HelloWorld`:</p>
            <p>Update a `HelloWorld`:</p>
            <p>Delete a `HelloWorld`:</p>
            <h3>Query:</h3>
            <p>Search for `HelloWorld`s`:</p>
            <h3>Subscription:</h3>
            <p>Stream of CRUD on `HelloWorld`s`:</p>
          </div>
        </ApolloProvider>
    );
  }
}

export default App;
