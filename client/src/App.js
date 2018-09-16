import React, { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>Demo Spring Boot / React Js Application:</h1>
        <h2>Realtime CRUD with GraphQL Querys, Mutations, and Subscriptions utilizing ReactiveX & Apollo</h2>
        <h3>Mutation:</h3>
        <p>Save a `HelloWorld`:</p>
        <p>Update a `HelloWorld`:</p>
        <p>Delete a `HelloWorld`:</p>
        <h3>Query:</h3>
        <p>Search for `HelloWorld`s`:</p>
        <h3>Subscription:</h3>
        <p>Stream of CRUD on `HelloWorld`s`:</p>
      </div>
    );
  }
}

export default App;
