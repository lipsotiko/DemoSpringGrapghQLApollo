import React, { Component } from 'react';
import { ApolloProvider } from 'react-apollo';
import { split } from 'apollo-link';
import { WebSocketLink } from 'apollo-link-ws';
import { getMainDefinition } from 'apollo-utilities';
import { ApolloClient } from 'apollo-client';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { HttpLink } from 'apollo-link-http';
import { onError } from 'apollo-link-error';
import { ApolloLink } from 'apollo-link';

import DemoQueryComponent from "./DemoQueryComponent"
import DemoMutationComponent from "./DemoMutationComponent"
import DemoSubscriptionComponent from "./DemoSubscriptionComponent"

const link = split(
  // split based on operation type
  ({ query }) => {
    const { kind, operation } = getMainDefinition(query);
    return kind === 'OperationDefinition' && operation === 'subscription';
  },
  new WebSocketLink({
    uri: `ws://localhost:8080/subscriptions`,
    options: {
      reconnect: true,
      reconnectionAttempts: 5,
      lazy: false,
      inactivityTimeout: 20
    }
  }),
  new HttpLink({
    uri: '/graphql',
    credentials: 'same-origin'
  }),
);

const client = new ApolloClient({
  link: ApolloLink.from([
    onError(({ graphQLErrors, networkError }) => {
      if (graphQLErrors)
        graphQLErrors.map(({ message, locations, path }) =>
          console.log(
            `[GraphQL error]: Message: ${message}, Location: ${locations}, Path: ${path}`,
          ),
        );
      if (networkError) console.log(`[Network error]: ${networkError}`);
    }),
    link
  ]),
  cache: new InMemoryCache()
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
          <DemoSubscriptionComponent />
        </div>
      </ApolloProvider>
    );
  }
}

export default App;
