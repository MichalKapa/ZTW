const express = require('express');
const { writeFile } = require('fs');
const app = express();
const http = require('http');
const { Server } = require("socket.io");
const server = http.createServer(app);
const io = new Server(server, {maxHttpBufferSize: 1e8});

const roomsHistory = {};
var privateRooms = [];

app.get('/', (req, res) => {
  res.sendFile(__dirname+'/index.html');
});

io.on('connection', (socket) => {
    // console.log(`a user ${socket.username} connected`);
    socket.on('join room', (roomname) => {
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
        console.log(`"${socket.username}" wrote "${msg.content}"`);
    });

    socket.on('started typing', name =>{
      socket.rooms.forEach((room, _)=>{
        socket.to(room).emit("started typing", name);
      });
      console.log(`${name} is typing`);
    });

    socket.on('stopped typing', name =>{
      socket.rooms.forEach((room, _)=>{
        socket.to(room).emit("stopped typing", name);
      });
      console.log(`${name} is not typing anymore`);
    });

    socket.on('upload', (file, callback) =>{
        writeFile("/tmp/upload", file, (err)=>{
          callback({ message: err ? "failure" : "success" });
        });
    });
  });

io.use((socket, next) => {
    const username = socket.handshake.auth.username;
    if (!username) {
        return next(new Error("invalid username"));
    }
    socket.username = username;
    next();
    privateRooms.push(socket.id);
    io.of("/").adapter.rooms.forEach((k, v)=>{
      if(!privateRooms.includes(v)){
        io.to(socket).emit("created room", v);
      }
    });
});

io.of("/").adapter.on("create-room", (room) => {
    if (roomsHistory[room] === undefined){
      roomsHistory[room] = [];
    }
    console.log(`Room "${room}" created`);
    io.emit('created room', room);
});
  
io.of("/").adapter.on("delete-room", (room) => {
    console.log(`Room "${room}" deleted`);
    io.emit('deleted room', room);
});

io.of("/").adapter.on("join-room", (room, id) => {
    const joiningSocket = io.sockets.sockets.get(id);
    console.log(`"${joiningSocket.username}" joined room "${room}"`);
    const msg = {"id": id, "username": joiningSocket.username, "type":"join", "content": `${joiningSocket.username} has joined`, "time": Date.now()};
    io.to(id).emit('chat', roomsHistory[room]);
    roomsHistory[room].push(msg);
    io.in(room).emit('chat', msg);
});

io.of("/").adapter.on("leave-room", (room, id) => {
    const leavinSocket = io.sockets.sockets.get(id);
    console.log(`"${leavinSocket.username}" left room "${room}"`);
    const msg = {"id": id, "username": leavinSocket.username, "type":"left", "content": `${leavinSocket.username} has left`, "time": Date.now()};
    io.in(room).emit('chat', msg);
    roomsHistory[room].push(msg);
});

server.listen(3000, () =>
  console.log('server listening at http://localhost:3000')
);
