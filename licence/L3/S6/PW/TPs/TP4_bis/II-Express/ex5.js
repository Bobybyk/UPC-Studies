var express = require('express')
var serv = express()
serv.listen(8080); // on aurait pu le faire plus tard
// ce qui ce pass '/' root 
serv.get('/'),function(req,res){ // y' un lien dans cette page vers la page toto et on mets la page 
    console.log('envoi des infos');
    serv.send('<!doctype html><html lang = "fr"><body><p>Requete de type GET vers la homepage</p><p>');
} // erreur  si on mets qq chose d'inconnu 

// ça va écrire toto
serv.get('/toto'),function(req,res){
    console.log('envoi des infos');
    serv.send('<!doctype html><html lang = "fr"><body><p>Toto</p><p>');
}
serv.get('/toto'),function(req,res){
    console.log('envoi des infos');
    serv.send('<!doctype html><html lang = "fr"><body><p>Toto</p><p>');
}
serv.all('/user/:id', function(req, res, next) {
    if (req.params.id == 0) res.send('OK');
    else next();
});
// les :nom on tape qq chose après le nom : ajouter un nom à la page
// next : on pase au prochain mgware, si la id est égale à 0 on envoie OK sinon on passe au prochain wood
serv.status(40).send('Erreur 404');