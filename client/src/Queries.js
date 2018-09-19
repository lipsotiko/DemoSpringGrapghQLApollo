import gql from "graphql-tag";

export const GET_HELLO_WORLDS_BY_NAME = gql`
    query GetHelloWorldsByName($name: String!) {
        getHelloWorldsByName(name: $name) {
            id
            name
        }
    }
`;

export const GET_ALL_HELLO_WORLDS = gql`
    query {
        getHelloWorlds {
            id
            name
        }
    }
`;

export const SAVE_HELLO_WORLD = gql`
    mutation SaveHelloWorld($id: Int, $name: String!) {
        saveHelloWorld(helloWorldInput: {id: $id, name: $name}) {
            id
            name
        }
    }
`;

export const DELETE_HELLO_WORLDS = gql`
    mutation DeleteHelloWorlds($name: String!) {
        deleteHelloWorlds(name: $name) {
            id
            name
        }
    }
`;
