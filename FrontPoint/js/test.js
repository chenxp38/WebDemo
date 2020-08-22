
// //import是ES6的功能，Node.js尚未完全支持它，您应该使用require
// const express = require('express')
// //var express = express('./express.js')
// var app = express()
// //设置跨域请求
// app.all('*', function (req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
//     res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//     res.header("X-Powered-By", ' 3.2.1')
//     res.header("Content-Type", "application/json;charset=utf-8");
//     next();
//   });
/**
 * window.onload = function() {}是页面的加载函数
 * 
*/
window.onload = function() {
    var head1 = document.getElementById('head1')
    var content1 = document.getElementById('content1')
    var title = document.getElementById('title')
    head1.innerText = "text1"
    content1.innerText = "text2"
    // title.innerText = "text_title"
    // alert("js已生效")


function createXHR() {
    if (typeof XMLHttpRequest != 'undefined') {
        return new XMLHttpRequest();
    } else if (typeof ActiveXObject != 'undefined') {
        var version = [
            'MSXML2.XMLHttp.6.0',
            'MSXML2.XMLHttp.3.0',
            'MSXML2.XMLHttp'
        ];
        for (var i = 0; version.length; i++) {
            try {
                return new ActiveXObject(version[i]);
            } catch (e) {
                throw new Error();
            }
        }
    } else{
        throw new Error('系统或浏览器不支持XHR对象');
    }
  }
  
//Get
document.getElementById('button1').addEventListener('click', loadText)

function loadText() {
    var xhr = new createXHR();
    xhr.onreadystatechange = function () {
        if(xhr.readyState == 4){
            if(xhr.status >= 200 && xhr.status < 304 || xhr.status == 304){
                console.log(JSON.parse(xhr.responseText));
            }
        }
    }
    xhr.open('get', 'http://localhost:8080/value/string');
    xhr.send(null);
}

// 有参1
document.getElementById('button2').addEventListener('click', loadSum)
function loadSum() {
    var params = {
        "integer1": 4,
        "integer2": 5,
    }
    $.ajax({
        type : 'POST',
        url : 'http://localhost:8080/value/sum',
        contentType : "application/x-www-form-urlencoded",
        data : params,
        dataType : 'JSON',
        success : function(data, status) {
            var rstate = data.result;
            alert(data.data)
            if (rstate == "0") {
                if (rstate == "0") {
                    alert('接口调用成功！');
                } else {
                    alert('接口调用失败！');
                }
            }
        },
        error : function(data, status, e) {
            alert('接口调用错误！');
        }
        });
}

// 有参2
document.getElementById('button3').addEventListener('click', loadUser)
function loadUser() {
    var params = {
        "uid":"16340000",
        "opid":"openid",
        "name":"xiaoming",
        "password": "password",
        "balance":0,
        "sex": "man",
        "phone": "12315"
    }
    $.ajax({
        type : 'POST',
        url : 'http://localhost:8080/value/object',
        contentType : "application/json",
        data : JSON.stringify(params),
        dataType : 'JSON',
        success : function(data, status) {
            var rstate = data.result;
            alert(data.data.uid)
            if (rstate == "0") {
                if (rstate == "0") {
                    alert('接口调用成功！');
                } else {
                    alert('接口调用失败！');
                }
            }
        },
        error : function(data, status, e) {
            alert('接口调用错误！');
        }
        });
}

}