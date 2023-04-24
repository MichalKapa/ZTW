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


app.get('/', (req, res) => {
  res.sendFile(__dirname+'/index.html');
});

io.on('connection', (socket) => {
    console.log('a user connected');
    console.log(socket.id);
    // console.log(socket.rooms);
    socket.join("test_room");
    // console.log(socket.rooms);
    
    socket.on('disconnect', () => {
        socket.rooms.forEach((callback) => {

        })
      console.log('user disconnected');
    });

    socket.on('chat message', (msg) => {
        console.log('message: ' + msg);
    });

    socket.on('abc', (msg) => {
        console.log("abc message" + msg);
        io.emit('abc', msg);
    });

    // socket.join("abc");
    // io.to("abc").emit({"who": "nie wiemy", "content": "dołączył"});

  });

// io.use((socket, next) => {
//     const username = socket.handshake.auth.username;
//     if (!username) {
//         return next(new Error("invalid username"));
//     }
//     socket.username = username;
//     next();
// });

// const rooms = io.of("/").adapter.rooms;
// const sids = io.of("/").adapter.sids;

io.of("/").adapter.on("create-room", (room) => {
    console.log(`room ${room} was created`);
});
  
io.of("/").adapter.on("delete-room", (room) => {
    console.log(`room ${room} was deleted`);
});

io.of("/").adapter.on("join-room", (room, id) => {
    console.log(`socket ${id} has joined room ${room}`);
    io.in(room).emit('users', `socket ${id} has joined`);
});

io.of("/").adapter.on("leave-room", (room, id) => {
    console.log(`socket ${id} has joined room ${room}`);
    io.in(room).emit('users', `socket ${id} has left`);
});


server.listen(3000, () =>
  console.log('server listening at http://localhost:3000')
);

// io.of("/").adapter.on("create-room", (room) => {
//     console.log(`room ${room} was created`);
//   });
  
//   io.of("/").adapter.on("join-room", (room, id) => {
//     console.log(`socket ${id} has joined room ${room}`);
//   });

  
// const app = require('express')();
// const http = require('http').Server(app);
// const io = require('socket.io')(http);
// const port = process.env.PORT || 3000;

// app.get('/', (req, res) => {
//   res.sendFile(__dirname + '/index.html');
// });

// io.on('connection', (socket) => {
//   socket.on('chat message', msg => {
//     io.emit('chat message', msg);
//   });
// });

// http.listen(port, () => {
//   console.log(`Socket.IO server running at http://localhost:${port}/`);
// });

// create-room
// delete-room
// join-room
// leave-room