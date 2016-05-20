
$.ajax({
	type:"post",
	url:baseUrlPath+'/portal/common/weatherQuery',
	dataType:"json",
	async:false,
	success:function(weathers){
		var ss=weathers;
		document.writeln('<ul class="weather">');
		for(var i=0;i<ss.length;i++){
		var localWeather = ss[i];
		var url=baseUrlPath+'/portal/weather/weatherPage?id='+localWeather.id;
		document.writeln('       <li>');
		document.writeln('           <span>');
		document.writeln('           <p><a href="'+url+'" target=_blank><img src="'+baseUrlPath+'/res/repository/portal/images/'+localWeather.weatherId+'" alt=""/></a></p>');
		document.writeln('           <p class="white">'+localWeather.week+'</p>');
		document.writeln('           </span>');
		if(i!=2)
		document.writeln('           <span class="sx1"> </span>');
		document.writeln('       </li>');
		};
		
		document.writeln('</ul>');
	}

});