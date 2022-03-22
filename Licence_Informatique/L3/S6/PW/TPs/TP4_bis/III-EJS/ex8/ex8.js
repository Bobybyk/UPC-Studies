var express= require('express');
var serv=express();
serv.set('view engine','ejs');
serv.use(express.static('public'));
serv.listen(8080);
console.log("Serveur a démarré");
const cours=[];
cours [0]= {titre: "Ocaml", 
desc:"cours de programmation fonctionnelle", 
profs: ["letouzey","giovani"], 
responsable: "Pierre Letouzey"};

cours [1]= {titre: "java", 
desc:"cours de programmation orientée objet", 
profs: ["Jurski","victor"],
responsable: "Yan Jurski"};

// dans le repertoire views on va mettre tout les fichiers ejs et dans le repertoire public on va mettre tout les images

serv.get('/cours/:id', function(req, res) {
    let n = parseInt(req.params.id);
    if((0<=n) && (n < cours.length)){
        //render : html + javascript , il prend la page et fabrique du html
        res.render('cours.ejs',cours[n]);
    }else{
        res.type('html');
        res.status(500).end("Cours inexistant");
    }
    
});
serv.get('/image.jpg',function(req,res){
    res.sendFile("image.jpg", {root: 'public'});

})
serv.use('/', function(req,res){
    console.log("requete");
    res.send('Erreur 404').status(404).end();

});