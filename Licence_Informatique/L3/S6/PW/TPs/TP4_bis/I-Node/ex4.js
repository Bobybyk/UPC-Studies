const http = require('http');
const fs = require('fs');

const server = 
//create a server object:
http.createServer(function (req, res) {
    
       
          fs.readFile('test.txt', 'utf8',function (err,data){
            res.writeHead(200, {
              'Content-Type': 'text/html; charset=utf-8'
              });
            res.write('Bonjour\n'); //write a response to the client
            console.log("Be patient I am working on it, please wait");
            res.write(data);
            return res.end(); //end the response

          });
           
         
      

        }).listen(8080); //the server object listens on port 8080 
        
  console.log("Le serveur écoute sur lr port 8080");
