import React, { Component } from 'react';
import ApolloClient from "apollo-boost";
import { ApolloProvider } from "react-apollo";
import DemoQueryComponent from "./DemoQueryComponent"
import DemoMutationComponent from "./DemoMutationComponent"

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
          
          <DemoMutationComponent />
          <DemoQueryComponent />

           <h3>Subscription:</h3>
          <p>A continuous stream of CRUD on `HelloWorld`s`:</p>
        </div>
      </ApolloProvider>
    );
  }
}

export default App;
