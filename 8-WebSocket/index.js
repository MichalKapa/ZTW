const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);

// const httpServer = require("http").createServer();
// const io = require("socket.io")(httpServer, {
//   cors: {
//     origin: "http://localhost:3000",
//   },
// });

const roomsHistory = {};

app.get('/', (req, res) => {
  res.sendFile(__dirname+'/index.html');
});

io.on('connection', (socket) => {
    console.log(`a user ${socket.id} connected`);

    socket.rooms.forEach((name, _) =>{
      socket.leave(name);
      // console.log(`${socket.id} left room ${name}`);
    });
    
    socket.on('join room', (roomname) => {
      socket.rooms.forEach((v1, v2, set) =>{
        socket.leave(v1);
      })
      socket.join(roomname);
    });

    socket.on('leave room', (roomname) =>{
      socket.leave(roomname);
    });

    socket.on('disconnect', () => {
      console.log('user disconnected');
    });

    socket.on('to server', (msg) => {
      socket.rooms.forEach((room, _)=>{
        socket.to(room).emit('chat', msg);
        roomsHistory[room].push(msg);
      });
        console.log('message: ' + msg);
    });

    socket.on('started typing', name =>{
      console.log(`${name} is typing`);
    });

    socket.on('stopped typing', name =>{
      console.log(`${name} is not typing anymore`);
    });
  });

// io.use((socket, next) => {
//     const username = socket.handshake.auth.username;
//     if (!username) {
//         return next(new Error("invalid username"));
//     }
//     socket.username = username;
//     send available rooms to user
//     next();
// });

io.of("/").adapter.on("create-room", (room) => {
    if (roomsHistory[room] === undefined){
      roomsHistory[room] = [];
    }
    console.log(`room ${room} was created`);
    io.emit('created room', room);
});
  
io.of("/").adapter.on("delete-room", (room) => {
    console.log(`room ${room} was deleted`);
    io.emit('deleted room', room);
});

io.of("/").adapter.on("join-room", (room, id) => {
    const joiningSocket = io.sockets.sockets.get(id);
    console.log(`${joiningSocket.username} has joined room ${room}`);
    io.to(id).emit('chat', roomsHistory[room]);
    io.in(room).emit('chat', {"id": id, "username": joiningSocket.username, "type":"join", "content": `${joiningSocket.username} has joined`});
});

io.of("/").adapter.on("leave-room", (room, id) => {
    const leavinSocket = io.sockets.sockets.get(id);
    console.log(`${leavinSocket.username} has left room ${room}`);
    io.in(room).emit('chat', {"id": id, "username": leavinSocket.username, "type":"left", "content": `${leavinSocket.username} has left`});
});


server.listen(3000, () =>
  console.log('server listening at http://localhost:3000')
);
