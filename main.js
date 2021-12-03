const http = require('http');
var fs = require('fs');
var mustache = require('mustache');
const port = 3000;
var val = -1;

http.createServer(function(req, res){
	if(req.url == '/turnir_table_r.html') {
		res.setHeader("Content-Type", "text/html");
		var data = fs.readFileSync(__dirname + '\\turnir_table.html', 'utf8');
		// считал содержимое файла 1.txt в num
		var num = Number(fs.readFileSync(__dirname + '\\1.txt', 'utf8'));
		if (val == 0) {
			num = -1;
			val++;
		}
		num = num * (-1);
		// поменял значение в файле
		fs.writeFileSync(__dirname + '\\1.txt', String(num));
		if (num == 1) {
			var view = {
				cls3: "st3",
				cls4: "st4"
			}
		} else {
			var view = {
				cls3:  "",
				cls4:  ""
			}
		}
		res.writeHead(301, { "Location": "http://localhost:3000/turnir_table.html" });
		res.end();
	} else if (req.url == '/turnir_table.html') {
		res.setHeader("Content-Type", "text/html");
		var data = fs.readFileSync(__dirname + '\\turnir_table.html', 'utf8');
		// считал содержимое файла 1.txt в num
		var num = Number(fs.readFileSync(__dirname + '\\1.txt', 'utf8'));
		if (num == 1) {
			var view = {
				cls3: "st3",
				cls4: "st4"
			}
		} else {
			var view = {
				cls3:  "",
				cls4:  ""
			}
		}
		res.writeHead(200);
		res.end(mustache.render(data, view));
	} 
	else {
		console.log(req.url);
		fs.readFile(__dirname + req.url, function (err,data) {
			if (err) {
				res.writeHead(404);
				res.end(JSON.stringify(err));
				return;
			}
			res.writeHead(200);
			res.end(data);
		});
	}
}).listen(port);
