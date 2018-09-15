import React, { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>Demo Spring Boot / React Js Application:</h1>
        <h2>Realtime CRUD with GraphQL Querys, Mutations, and Subscriptions utilizing ReactiveX & Apollo</h2>
        <h3>Resources: </h3>
        <ul>
            <li><a href='https://graphql.org/learn/schema/'>https://graphql.org/learn/schema/</a></li>
            <li><a href='https://github.com/graphql-java/graphql-spring-boot'>https://github.com/graphql-java/graphql-spring-boot</a></li>
            <li><a href='https://www.apollographql.com/'>https://www.apollographql.com/</a></li>
            <li><a href='https://www.youtube.com/watch?v=f3acAsSZPhU'>https://www.youtube.com/watch?v=f3acAsSZPhU</a></li>
        </ul>
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
