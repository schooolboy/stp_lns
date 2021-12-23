function refresh () {
	var xhttp = new XMLHttpRequest(); // создание обьекта запроса
	var xhttp2 = new XMLHttpRequest(); // создание обьекта запроса

	var elem1 = document.getElementById('e1');
	var elem2 = document.getElementById('e2');
	
	xhttp.onreadystatechange=function(){ // активируется при получении ответа сервера
		if (xhttp.readyState==4 && xhttp.status==200){
			elem1.innerHTML = xhttp.responseText;
		}
	}
	xhttp2.onreadystatechange=function(){ // активируется при получении ответа сервера
		if (xhttp2.readyState==4 && xhttp2.status==200){
			elem2.innerHTML = xhttp2.responseText;
		}
	}
	// задаем адрес подключения
	xhttp.open('GET', 'turnir_table_a.html', true); // отправкаа ajax запроса
	xhttp.send(null); // отослать запроса
	xhttp2.open('GET', 'turnir_table_a.html', true); // отправкаа ajax запроса
	xhttp2.send(null); // отослать запроса
}