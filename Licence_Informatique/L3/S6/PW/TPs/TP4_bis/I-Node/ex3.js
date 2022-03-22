const fs = require('fs');
/*fs.readFileSync('test.txt', 'utf8', function(err, data) {
    console.log("Be patient I am working on it, please wait");
    const content = data;
    console.log(content);
  });*/
    console.log('please wait');
    try{
      let data=fs.readFileSync('test.txt','utf-8');
      console.log(data);
    }
    catch(err){
      console.log("erreur");
    }