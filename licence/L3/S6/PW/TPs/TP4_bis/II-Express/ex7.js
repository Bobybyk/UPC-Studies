var express= require('express');
var serv=express();

console.log("Le serveur commence à fonctionner");

serv.get('/cours/:titre/descr',function(req,res){
    res.type('html');
    res.send('Vous avez demandé le cours: ' +req.params.titre );
})

serv.get('/', function(req,res){
    res.send('Error 404, \n Veuillez demander un cours svp').stauts(404).end();
});
serv.listen(8080);