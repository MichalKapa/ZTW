import { createSchema, createYoga } from "graphql-yoga";
import { createServer } from "node:http";
import { readFileSync } from "node:fs";
import { join } from "node:path";
import axios from "axios";

async function getRestUsersList() {
  const mappedUserList = axios.get("https://jsonplaceholder.typicode.com/users")
  .then((response) => {
    return response.data.map(
      ({ id, name, email, username }) => ({
        id: id,
        name: name,
        email: email,
        login: username,
      })
    );
  })
  .catch(error => {
    throw error;
  })
  return mappedUserList;
}

async function getRestTodoList() {
  const mappedTodoList = axios.get("https://jsonplaceholder.typicode.com/todos")
  .then((response) => {
    return response.data.map(({ id, title, completed, userId }) => ({
      id: id,
      title: title,
      completed: completed,
      user_id: userId,
    }));
  })
  .catch();
  return mappedTodoList;
}

const usersList = await getRestUsersList();
const todosList = await getRestTodoList();

function todoById(todoId) {
  return todosList.find((todo) => todo.id == todoId);
}

function userById(userId) {
  return usersList.find((user) => user.id == userId);
}

function todosOfUser(userId){
  return todosList.filter((todo) => todo.user_id == userId);
}

function userOfTodo(todoId) {
  return usersList.find((user_inner) => user_inner.id == todoId);
}

export const schema = createSchema({
  typeDefs: readFileSync(join("./src/schema.graphql"), "utf8"),
  resolvers: {
    Query: {
      users: async () => usersList,
      todos: async () => todosList,
      todo: (parent, args, context, info) => todoById(args.id),
      user: (parent, args, context, info) => userById(args.id),
    },
    User: {
      todos: (parent, args, context, info) => todosOfUser(parent.id),
    },
    ToDoItem: {
      user: (parent, args, context, info) => userOfTodo(parent.user_id),
    },
  },
});

const yoga = createYoga({ schema });
const server = createServer(yoga);
server.listen(4000, () => {
  console.info("Server is running on http://localhost:4000/graphql");
});