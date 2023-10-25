const express = require('express');
const serv= express();
const noms = ['affiche','enleve','Habermehl','Jurski','Padovani'];

serv.use(express.static('public'));

serv.get('/',function (req,res,next) {
    res.sendFile("bienvenue.html",{root:'public'});
});

serv.get('/:nom',function (req,res,next) {
    
    // QUESTION 9->11
    
    //Q.10
    if (req.params.nom == "affiche") {

        const noms_bis = [];
        for (let i = 1 ; i<noms.length ; i++) {
            noms_bis[i-1] = noms[i];
        }
        
        noms_bis.sort(function(a, b) {
            return a.length - b.length;
        });

        console.log("affichage page liste des noms");
        res.render("affiche.ejs", {title: "liste des noms", content: noms_bis});
    }

    //Q.11
    else if (req.params.nom == "enleve") {

        const noms_bis = [];

        for (let i = 2 ; i<noms.length ; i++) {
            noms_bis[i-2] = noms[i];
        }

        noms_bis.sort(function(a, b) {
            return a.length - b.length;
        });

        console.log("affichage page suppression de noms");
        res.render("enleve.ejs", {title: "liste des noms", content: noms_bis});
    }

    //Q.9
    else if (req.params.nom == "Matthieu") {
        console.log("affichage page personnelle " + {nom:req.params.nom});
        res.render("nom_propre.ejs", {nom:req.params.nom});
    }

    //Q.8
    else if (noms.indexOf(req.params.nom) != -1) {
        console.log("affichage page " + {nom:req.params.nom});
        res.render("nom.ejs",{nom:req.params.nom}); 
    } else {
        console.log("chemin vers " + {nom:req.params.nom} + " non existant");
        res.render("err.ejs", {nom:req.params.nom});
    }
});

// Q.11
serv.post('/', function (req, res) {

    console.log("demande suppression noms");

    const tab_enleve = [];

    for (let i = 0 ; i<noms.length-2 ; i++) {
        console.log(req.body.noms[i]);
        tab_enleve[i-2] = req.body.noms[i];
    }

});
	 
serv.listen(8080);
