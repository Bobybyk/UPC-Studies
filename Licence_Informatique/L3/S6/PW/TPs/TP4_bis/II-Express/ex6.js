var express= require('express');
var serv=express();
console.log("Le serveur commence l'écoute sur le port 8080");
serv.listen(8080);


serv.get('/', function(req,res){
    res.send('Error 404').status(404).end();
});
serv.get('/pictures', function(req,res){
    res.download('./fichier/fichier.pdf','fichier.pdf',function(err){
        if(err){
            console.log(err);
            console.log('Current directory: ' + process.cwd());
            res.send('Can not get file').status(404).end();

        }
        else {
            res.status(200).end()

        }
    });
});

serv.get('/private', function(req,res){
    res.send("vous n'avez pas les permissions").status(404).end();
});
serv.get('/:machin',function(req,res){
    console.log("requête reçue   "+  Date.now());
});
