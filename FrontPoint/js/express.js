var express = require('express');
var bodyParser = require('body-parser');
module.exports = function(){
    console.log('init express...');
    var app = express();
    app.use(bodyParser.json());
    app.use(function(req, res, next){
        res.status(404);
        try {
            return res.json('Not found');
        } catch(e) {
            console.error('404 set header after sent');
        }
    });
    return app;
};