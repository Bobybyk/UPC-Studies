const fs = require('fs');
fs.readFile('test.txt', 'utf8', function(err, data) {
    console.log("reading file, please wait");
    const content = data;
    console.log(content);
  });
  /**
   * fs.readFile('test.txt',(err,data) =>{
    if(err) console.log(err);
    console.log(data.toString('utf-8'));   //UTF8.stringify(data)); ?
});

   */