const express = require('express');
const server = express();
server.set('view engine','ejs');
server.use(express.urlencoded({extended: true}));

// la base de donée 
user=[{nom: 'amnasr' , mot_de_passe: 'lol123'} , {nom: 'ankasp' , mot_de_passe: 'kjn11' }];
// l'existance du user


server.get('/', function (req, res) {

    console.log("je suis ici1"); 

    res.render('form.ejs',{titre:"Veuillez remplir ce formulaire", nom:'', mot_de_passe:''});
    

});

function do_treatemnt(usr,n,pwd,res){
    exist=false;
    for(var i=0 ; i<usr.length ; i++){
        // L'utilisateur existe dans la BDD
      if (n === usr[i].nom){
          exist=true;
          usr[i].mot_de_passe=pwd;
          console.log(usr[i].nom);
          res.render('login.ejs', {nom: n});
         
                      
      }
  }

  if (exist===false){

       // L'utilisateur n'exists pas, on rajoute le nom ainsi que le mdp
       usr[usr.length]={nom: n,mot_de_passe:pwd};
       res.render('register.ejs', {nom: n});

  }
};
server.post('/', function (req, res) {

    console.log("je suis ici2");
    
    var name=req.body.nom;
    console.log(name);
    console.log(req.body);
    var pwd = req.body.mot_de_passe;
    console.log(pwd);
    if ((name === '')|| (pwd === '')){
        console.log("je suis ici3");
        res.render('form2.ejs',{titre:'Veuillez remplir tous les champs',nom:name,mot_de_passe: pwd});
       
    }else {
        do_treatemnt(user,name,pwd,res);
    }
     
             
});



// start the server
server.listen(8080);
console.log('Server started! At http://localhost:8080');