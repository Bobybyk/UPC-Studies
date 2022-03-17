const express = require("express");
const server = express();

server.get("/",function(req,res) { 
        console.log("envoi des infos");
        res.send("text qlq"); 
});

server.use(function(req,res,next) {
        console.log("error"); 
        res.status(500).end("error");
});

server.listen(8080);