/**
 * c'est quoi EJS ? 
 * c'est du javascript à l'intérieur du html pour faire un serveur (on enverra du html qui est fabriqué par le client)
 */
// ma page ejs : se trouve dans un repertoire views ( ou se trouve tt les ejs)

/******* Ici (dans le fichier .js) que du javascript du côté serveur, si je veux rajouter du js coté client ( on l'ajoute dans la page ejs dans le html) */
var express= require('express');
var serv=express();
serv.set('view engine','ejs');
serv.use(express.static('public'));
serv.listen(8080);
console.log("Serveur a démarré l'écoute sur le port 8080");


serv.use('/user/:nom/:prenom/:age',function(req,res){
   
    res.render('mapageavecboucle.ejs',{user : {nom:req.params.nom, prenom: req.params.prenom, age:req.params.age}});
})

serv.use('/user/:nom',function(req,res){
    // params.nom : valeur que je recup et je passe en argument
    res.render('mapage.ejs', {nom: req.params.nom});

})
// <!-- <%= nom %>: recuperer la valeur de v_nom et l'insérer dans html -->




// pour charger l'image : 
serv.get('image.png',function(req,res){
    res.sendFile("image.png", {root: 'public'});
}); 
// faut faire le truc pour '/' pour mettre en défaut ce qu'on doit avoir

serv.use('/', function(req,res){
    console.log("requete");
    res.send('Erreur 404').status(404).end();

});


